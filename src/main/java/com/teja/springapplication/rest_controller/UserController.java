package com.teja.springapplication.rest_controller;

import com.teja.springapplication.entity.Cart;
import com.teja.springapplication.entity.User;
import com.teja.springapplication.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
  @Autowired
  private UserService userService;

  @ResponseBody
  @PostMapping("/createUser")
  public Object createUser(@RequestBody User user) {
    Cart cart = new Cart();
    user.setCartId(cart);
    cart.setUserId(user);
    User newuser = userService.createUserService(user);
    return newuser;
  }

  /**
   * @return
   */
  @GetMapping("/getAllUsers")
  public Object getAllUsers() {
    return userService.getAllUsersService();
  }

  @GetMapping("/getUser/{userId}")
  public Object getUserById(@PathVariable String userId) {
    User user = (User) userService.getUserByIdService(userId);
    return user;
  }

  @GetMapping("/deleteUser/{userId}")
  public void deleteUserById(@PathVariable String userId) {
    userService.deleteUserService(userId);
  }

  @PostMapping("/updateUser")
  public Object updateUser(@RequestBody User user) {
    return userService.updateUserService(user);
  }
}
