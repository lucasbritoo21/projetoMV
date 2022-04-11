package com.example.cubomv.controller;

import com.example.cubomv.model.Food;
import com.example.cubomv.model.User;
import com.example.cubomv.repository.FoodRepository;
import com.example.cubomv.repository.UserRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type User controller.
 *
 * @author Givantha Kalansuriya
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class FoodController {

	@Autowired
	private FoodRepository foodRepository;

	/**
	 * Get all users list.
	 *
	 * @return the list
	 */
	@GetMapping("/foods")
	@CrossOrigin(origins = "http://localhost:3000")

	public List<Food> getAllUsers() {
		return foodRepository.findAll();
	}

	/**
	 * Gets users by id.
	 *
	 * @param userId the user id
	 * @return the users by id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping("/foods/{id}")
	@CrossOrigin(origins = "http://localhost:3000")

	public ResponseEntity<Food> getUsersById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		Food user = foodRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
		return ResponseEntity.ok().body(user);
	}

	/**
	 * Create user user.
	 *
	 * @param user the user
	 * @return the user
	 */
	@PostMapping("/foods")
	@CrossOrigin(origins = "http://localhost:3000")
	public Food createUser(@Valid @RequestBody Food user) {
		return foodRepository.save(user);
	}

	/**
	 * Update user response entity.
	 *
	 * @param userId      the user id
	 * @param userDetails the user details
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@PutMapping("/foods/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Food> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody Food userDetails) throws ResourceNotFoundException {

		Food user = foodRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

		user.setName(userDetails.getName());
		final Food updatedUser = foodRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	/**
	 * Delete user map.
	 *
	 * @param userId the user id
	 * @return the map
	 * @throws Exception the exception
	 */
	@DeleteMapping("/foods/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
		Food user = foodRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

		foodRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
