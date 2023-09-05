package com.codewithsameer.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithsameer.blog.payloads.APIResponse;
import com.codewithsameer.blog.payloads.UserDto;
import com.codewithsameer.blog.services.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")

public class UserController {
	
	@Autowired
	private UserService userService;
	
	  @GetMapping("/version")
	  public ResponseEntity version() {
		  String metaData = System.getenv("MTA_METADATA");
		 System.out.print("MTA_METADATA"+ metaData);
		 
	    if (metaData != null && metaData.length() > 0) {
	      return ResponseEntity.ok().body(metaData);
	    } else {
	      StringBuilder builder = new StringBuilder();
	      builder.append("API is UP");
	      return ResponseEntity.ok().body(builder.toString());
	    }
	  }
	
	 @PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)	{
		 
	UserDto createUserDto = this.userService.createUser(userDto);
	return new ResponseEntity<>(createUserDto, HttpStatus.OK); 
}
	 
	 @PutMapping("/{userId}")

	 public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid){ 
	 UserDto updatedUser = this.userService.updateUser(userDto, uid);
	 return ResponseEntity.ok(updatedUser) ;
}
	 
	 
	 @DeleteMapping("/{userId}")
	 public ResponseEntity<APIResponse> deleteUser(@PathVariable("userId") Integer uid) {
		  this.userService.deleteUser(uid);
		  return new ResponseEntity<APIResponse>(new APIResponse("User Deleted Successfully",true), HttpStatus.OK);
		 }
		 
	 @GetMapping("/getAll")
	 	 public ResponseEntity< List<UserDto>> getAllUsers(){ 
		 List<UserDto> ListUser = this.userService.getAllUsers();
		 return ResponseEntity.ok(ListUser) ;	 
	 	}
	 
	 @GetMapping("/get/{userId}")
 	 public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer uid){ 
	 UserDto  user= this.userService.getUserById(uid);

	 return ResponseEntity.ok(user) ;
 
 	}
}