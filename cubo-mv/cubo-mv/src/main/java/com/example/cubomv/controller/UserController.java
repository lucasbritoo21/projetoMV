package com.example.cubomv.controller;


import com.example.cubomv.model.User;

import com.example.cubomv.repository.UserRepository;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController

@RequestMapping("/api/v1")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  /**
   * Get all users list.
   *
   * @return the list
   */
  @GetMapping("/users")
  @CrossOrigin(origins = "http://localhost:3000")
 
  public List<User> getAllUsers() {
	  System.out.println(String.format(
			    "RESPONSEEEEE %d", userRepository.getAllUsersCustom()));
    return userRepository.getAllUsersCustom();
  }

  /**
   * Gets users by id.
   *
   * @param userId the user id
   * @return the users by id
   * @throws ResourceNotFoundException the resource not found exception
   */
  @CrossOrigin(origins="*")
  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long userId)
      throws ResourceNotFoundException {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
    return ResponseEntity.ok().body(user);
  }

  /**
   * Create user user.
   *
   * @param user the user
   * @return the user
   */
  
  @CrossOrigin(origins="*")
  @PostMapping("/users")
  public User createUser(@Valid @RequestBody User user) {
	  
    return userRepository.insertNewUser(user.getId(), user.getFirstName(), user.getLastName(), user.getCpf());
    
  }

  /**
   * Update user response entity.
   *
   * @param userId the user id
   * @param userDetails the user details
   * @return the response entity
   * @throws ResourceNotFoundException the resource not found exception
   */
  @CrossOrigin(origins="*")
  @PutMapping("/users/{id}")
  public ResponseEntity<User> updateUser(
      @PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
      throws ResourceNotFoundException {

//    User user =
//        userRepository
//            .findById(userId)
//            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
//
////    user.setEmail(userDetails.getEmail());
//    user.setLastName(userDetails.getLastName());
//    user.setFirstName(userDetails.getFirstName());
//    user.setUpdatedAt(new Date());
    final User updatedUser = userRepository.updateUserCustom(userId, userDetails.getFirstName(), userDetails.getLastName(), userDetails.getCpf());
    return ResponseEntity.ok(updatedUser);
  }

  /**
   * Delete user map.
   *
   * @param userId the user id
   * @return the map
   * @throws Exception the exception
   */
  
  @DeleteMapping("/user/{id}")
  @CrossOrigin(origins = "http://localhost:3000")
  public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
//    User user =
//        userRepository
//            .findById(userId)
//            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

    userRepository.deleteUserCustom(userId);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
}


