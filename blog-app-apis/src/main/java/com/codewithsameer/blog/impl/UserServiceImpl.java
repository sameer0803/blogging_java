package com.codewithsameer.blog.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithsameer.blog.entities.UserEntity;
import com.codewithsameer.blog.exceptions.ResourceNotFoundException;
import com.codewithsameer.blog.payloads.UserDto;
import com.codewithsameer.blog.repositories.UserRepo;
import com.codewithsameer.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {

		System.out.println("User DTO jo passs ho rha-->"+userDto.toString());
		
		UserEntity user = this.dtoToUser(userDto);

		System.out.println("User Entity after conversion -->"+this.userToDto(user).toString());
		
		UserEntity savedUser = this.userRepo.save(user);
		
		System.out.println("After save -->"+savedUser.toString());
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		UserEntity user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		UserEntity updatedUser = this.userRepo.save(user);
		UserDto userDtol = this.userToDto(updatedUser);
		return userDtol;

	}

	@Override
	public UserDto getUserById(Integer userId) {

		System.out.println("Reaching here getUserById");
		UserEntity user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
		System.out.println("User Entity -->"+user.toString());
		System.out.println("User DTOs -->"+this.userToDto(user).toString());
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserEntity> usersList = this.userRepo.findAll();
		List<UserDto> userDtoList = usersList.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtoList;
	}

	@Override
	public void deleteUser(Integer userId) {

		this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
		;
	}

	private UserEntity dtoToUser(UserDto userDto) {
		UserEntity userEntity = this.modelMapper.map(userDto, UserEntity.class);
		return userEntity;
	}

	public UserDto userToDto(UserEntity userEntity) {
		UserDto userDto = this.modelMapper.map(userEntity, UserDto.class);
		return userDto;
	}

}
