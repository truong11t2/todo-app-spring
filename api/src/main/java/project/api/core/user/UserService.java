package project.api.core.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

public interface UserService {

  Mono<User> createUser(User body);

  /**
   * Sample usage: "curl $HOST:$PORT/user/userName".
   *
   * @param userName User name of the user
   * @return the user information
   */
  @GetMapping(
    value = "/user/{userName}",
    produces = "application/json")
  Mono<User> getUser(@PathVariable String userName);

  @GetMapping(
    value = "/user/{findUser}",
    produces = "application/json"
  )
  public Mono<Boolean> findUserName(@PathVariable String userName);

  @GetMapping(
    value = "/user/{findEmail}",
    produces = "application/json"
  )
  public Mono<Boolean> findEmail(@PathVariable String email);

  Mono<Void> deleteUser(String userName);
}
