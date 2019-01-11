package com.practise.rest.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practise.rest.exceptions.UserServiceException;
import com.practise.rest.model.UpdateUserDetails;
import com.practise.rest.model.UserDetails;
import com.practise.rest.userservice.UserService;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
	
	private Map<String,UserDetails> usersMap = null;
	@Autowired
	UserService userService;

	@GetMapping
	// http://localhost:8080/users?page=80&limit=10, page and limit are query
	// parameters
	/*
	 * //If we don't specify parameters for RequestParam annotation, query param of
	 * URI will be matched with method argument name //If you want to avoid it,
	 * specify value as param to RequestParam annotation //To make a Query parameter
	 * not required, use required parameter. //Using required parameter as false for
	 * primitive datatypes for method arguments will not work as primitives cannot
	 * be converted to null
	 */
	public String getUser(@RequestParam(value = "page", defaultValue = "10") int page,
			@RequestParam(value = "limit", defaultValue = "100") int limit,
			@RequestParam(value = "sort", required = false) String sortOrder) {
		return "get users is called with page =" + page + " with a limit of " + limit + " Sorted by " + sortOrder;
	}

	// ResponseEntity is required to return status code and response body as well as
	// headers

	@GetMapping(path = "/{userId}")
	public ResponseEntity<UserDetails> getUserById(@PathVariable String userId) {
		
		if(true)
			throw new UserServiceException("This is a user defined exception");
		if(usersMap != null && usersMap.containsKey(userId)) {
			return new ResponseEntity<UserDetails>(usersMap.get(userId), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<UserDetails>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping
	public ResponseEntity<Resource<UserDetails>> createUser(@Valid @RequestBody UserDetails userDetails) {
		if(usersMap == null) {
			usersMap = new HashMap<String,UserDetails>();
		}
		String userId = UUID.randomUUID().toString();
		userDetails.setUserId(userId);
		usersMap.put(userId, userDetails);
		ControllerLinkBuilder linkTo = linkTo(methodOn(UserController.class).deleteUser(userId));
		Resource<UserDetails> resource = new Resource<UserDetails>(userDetails);
		resource.add(linkTo.withRel("deleteUser"));
		return new ResponseEntity<Resource<UserDetails>>(resource,HttpStatus.CREATED);
	}

	@PutMapping(path = "/{userId}")
	public ResponseEntity<UserDetails> updateUser(@PathVariable String userId,@Valid @RequestBody UpdateUserDetails updateUserDetails) {
		if(usersMap != null && usersMap.containsKey(userId)) {
			UserDetails userDetails = usersMap.get(userId);
			userDetails.setFirstName(updateUserDetails.getFirstName());
			userDetails.setLastName(updateUserDetails.getLastName());
			usersMap.put(userId, userDetails);
			return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<UserDetails>(HttpStatus.NOT_MODIFIED);
		}
	
	}

	@DeleteMapping(path="/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		usersMap.remove(userId);
		return ResponseEntity.noContent().build();
	}

}
