package com.spring.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.user.exception.UserNotFoundException;

@RestController
public class UserResource {

	@Autowired
	UserDaoService userService;

	@GetMapping("/users")
	public List<User> retireveUsers() {

		return userService.findAll();
	}

	@GetMapping("/users/{id}")
	public User retriveUser(@PathVariable int id) {

		User user = userService.findOne(id);

		if (user == null)
			throw new UserNotFoundException("id-" + id);

		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

		User saveUser = userService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userService.deleteById(id);

		if (user == null)
			throw new UserNotFoundException("id-" + id);
	}

}
