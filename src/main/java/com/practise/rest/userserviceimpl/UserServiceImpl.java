package com.practise.rest.userserviceimpl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.practise.rest.model.UserDetails;
import com.practise.rest.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private Map<String,UserDetails> usersMap = null;
	
	@Override
	public UserDetails createUser(UserDetails userDetails) {
		if(usersMap == null) {
			usersMap = new HashMap<String,UserDetails>();
		}
		String userId = UUID.randomUUID().toString();
		userDetails.setUserId(userId);
		usersMap.put(userId, userDetails);
		return userDetails;
	}
	
}
