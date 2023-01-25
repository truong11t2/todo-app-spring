package project.main.services;

import java.util.logging.Level;

import static reactor.core.publisher.Flux.empty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

//import com.fasterxml.jackson.databind.ObjectMapper;

import project.api.core.event.Event;
import project.api.core.event.Event.Type;
import project.api.core.todo.Todo;
import project.api.core.todo.TodoService;
import project.api.core.user.User;
import project.api.core.user.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Component
public class MainIntegration implements UserService, TodoService {

    private static final Logger LOG = LoggerFactory.getLogger(MainIntegration.class);

    private final String todoServiceUrl;
    private final String userServiceUrl;

    private final WebClient webClient;
    //private final ObjectMapper mapper;
    private final StreamBridge streamBridge;
    private final Scheduler publishEventScheduler;

    public MainIntegration(
        @Qualifier("publishEventScheduler") Scheduler publishEventScheduler,

        WebClient.Builder webClient,
        //ObjectMapper mapper,
        StreamBridge streamBridge,

        @Value("${app.todo-service.host}") String todoServiceHost,
        @Value("${app.todo-service.port}") int todoServicePort,

        @Value("${app.user-service.host}") String userServiceHost,
        @Value("${app.user-service.port}") int userServicePort
    ) {
        this.publishEventScheduler = publishEventScheduler;
        this.webClient = webClient.build();
        //this.mapper = mapper;
        this.streamBridge = streamBridge;

        todoServiceUrl = "http://" + todoServiceHost + ":" + todoServicePort + "/todo";
        userServiceUrl = "http://" + userServiceHost + ":" + userServicePort + "/user";
    }

    @Override
    public Mono<Todo> createTodo(Todo body) {
        return Mono.fromCallable(() -> {
            sendMessageTodo("todo-out-0", new Event<Event.Type, Integer, Todo>(Event.Type.CREATE, body.getTodoId(), body));
            return body;
        }).subscribeOn(publishEventScheduler);
    }

    private void sendMessageTodo(String bindingName, Event<Type, Integer, Todo> event) {
        LOG.debug("Sending a {} message to {}", bindingName, event.getEventType());
        Message<Event<Type, Integer, Todo>> message = MessageBuilder.withPayload(event)
            .setHeader("partitionKey", event.getKey())
            .build();
        streamBridge.send(bindingName, message);
    }

    @Override
    public Flux<Todo> getTodos(String userName) {
        String url = todoServiceUrl + "?userName=" + userName;
        LOG.debug("Will call the getTodo API on URL: {}", url);
        return webClient.get().uri(url).retrieve().bodyToFlux(Todo.class).log(LOG.getName(), Level.FINE).onErrorResume(error -> empty());
    }

    @Override
    public Mono<Void> deleteTodo(int todoId) {
        return Mono.fromRunnable(() -> sendMessageInt("todo-out-0", new Event<Event.Type, Integer, String>(Event.Type.DELETE, todoId, null)))
            .subscribeOn(publishEventScheduler).then();
    }

    private void sendMessageInt(String bindingName, Event<Type, Integer, String> event) {
        LOG.debug("Sending a {} message to {}", bindingName, event.getEventType());
        Message<Event<Type, Integer, String>> message = MessageBuilder.withPayload(event)
            .setHeader("partitionKey", event.getKey())
            .build();
        streamBridge.send(bindingName, message);
    }

    @Override
    public Mono<Void> deleteTodos(String userName) {
        return Mono.fromRunnable(() -> sendMessage("todo-out-0", new Event<>(Event.Type.DELETE, userName, null)))
            .subscribeOn(publishEventScheduler).then();
    }

    @Override
    public Mono<User> createUser(User body) {
        return Mono.fromCallable(() -> {
            sendMessageUser("user-out-0", new Event<Event.Type, String, User >(Event.Type.CREATE, body.getUserName(), body));
            return body;
        }).subscribeOn(publishEventScheduler);
    }

    private void sendMessageUser(String bindingName, Event<Type, String, User> event) {
        LOG.debug("Sending a {} message to {}", bindingName, event.getEventType());
        Message<Event<Type, String, User>> message = MessageBuilder.withPayload(event)
            .setHeader("partitionKey", event.getKey())
            .build();
        streamBridge.send(bindingName, message);
    }

    @Override
    public Mono<User> getUser(String userName) {
        String url = userServiceUrl + "?userName=" + userName;
        LOG.debug("Will call the getUser API on URL: {}", url);

        return webClient.get().uri(url).retrieve().bodyToMono(User.class).log(LOG.getName(), Level.FINE)
        .onErrorMap(WebClientResponseException.class, ex -> handleException(ex));
    }

    @Override
    public Mono<Boolean> findUserName(String userName) {
        String url = userServiceUrl + "/findUser/?findUser=" + userName;
        LOG.debug("Will call the findUser API on URL: {}", url);

        return webClient.get().uri(url).retrieve().bodyToMono(Boolean.class).log(LOG.getName(), Level.FINE)
        .onErrorMap(WebClientResponseException.class, ex -> handleException(ex));
    }

    @Override
    public Mono<Boolean> findEmail(String email) {
        String url = userServiceUrl + "/findEmail/?findEmail=" + email;
        LOG.debug("Will call the findEmail API on URL: {}", url);

        return webClient.get().uri(url).retrieve().bodyToMono(Boolean.class).log(LOG.getName(), Level.FINE)
        .onErrorMap(WebClientResponseException.class, ex -> handleException(ex));
    }
    

    @Override
    public Mono<Void> deleteUser(String userName) {
        return Mono.fromRunnable(() -> sendMessage("user-out-0", new Event<>(Event.Type.DELETE, userName, null)))
        .subscribeOn(publishEventScheduler).then();
    }

    private void sendMessage(String bindingName, Event<Event.Type, String, String> event) {
        LOG.debug("Sending a {} message to {}", bindingName, event.getEventType());
        Message<Event<Type, String, String>> message = MessageBuilder.withPayload(event)
            .setHeader("partitionKey", event.getKey())
            .build();
        streamBridge.send(bindingName, message);
    }

    private Throwable handleException(Throwable ex) {
        if(!(ex instanceof WebClientResponseException)) {
            LOG.warn("Got an unexpected error: {}, will rethrow it", ex.toString());
            return ex;
        }
        WebClientResponseException wcre = (WebClientResponseException)ex;

        switch (wcre.getStatusCode()) {

        case NOT_FOUND:
            //Todo: uncomment below
            //return new NotFoundException(getErrorMessage(wcre));
            return new Throwable("Not found username");

        case UNPROCESSABLE_ENTITY :
            //Todo: uncomment below
            //return new InvalidInputException(getErrorMessage(wcre));
            return new Throwable("Cannot process");

        default:
            LOG.warn("Got an unexpected HTTP error: {}, will rethrow it", wcre.getStatusCode());
            LOG.warn("Error body: {}", wcre.getResponseBodyAsString());
            return ex;
        }
    }


  //private String getErrorMessage(WebClientResponseException ex) {
    //Todo: uncomment below
    // try {
    //   return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
    // } catch (IOException ioex) {
    //   return ex.getMessage();
    // }
    //return "Something wrong";
  //}

}
