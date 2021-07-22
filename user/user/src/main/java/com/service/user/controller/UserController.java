package com.service.user.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.user.model.CreateRequestUserModel;
import com.service.user.model.CreateUserResponseModel;
import com.service.user.service.UsersService;
import com.service.user.shared.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UsersService usersService;
	
	@Autowired
	private Environment evn;
	
	@GetMapping("/status/check")
	public String status()
	{
		return "Working on port " + evn.getProperty("local.server.port") ;
	}
	
	@PostMapping(path = "/createuser"
			)
	public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateRequestUserModel userDetails)
	{
		ModelMapper modelMapper = new ModelMapper(); 
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		
		UserDto createdUser = usersService.createUser(userDto);
		
		CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
}
