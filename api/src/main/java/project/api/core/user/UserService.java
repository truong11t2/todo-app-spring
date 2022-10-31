package project.api.core.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import reactor.core.publisher.Mono;

public interface UserService {

  Mono<User> createUser(User body);

  /**
   * Sample usage: "curl $HOST:$PORT/user?userName=abc".
   *
   * @param userName User name of the user
   * @return the user information
   */
  @GetMapping(
    value = "/user",
    produces = "application/json")
  Mono<User> getUser(@RequestParam(value = "userName", required = true) String userName);

  @GetMapping(
    value = "/user/findUser",
    produces = "application/json"
  )
  Mono<Boolean> findUserName(@RequestParam(value = "findUser", required = true) String userName);

  @GetMapping(
    value = "/user/findEmail",
    produces = "application/json"
  )
  Mono<Boolean> findEmail(@RequestParam(value = "findEmail", required = true) String email);

  Mono<Void> deleteUser(String userName);
}
