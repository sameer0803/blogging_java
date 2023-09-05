package com.codewithsameer.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {

	private int id;
	@NotEmpty
	@Size(min = 3 ,max = 40,message ="Username must be of 3 characters and 40 characters")
	private String name;
	
	@Email   
	private String email;
	
	@NotEmpty
	@Size(min = 6 ,max = 20,message ="Password must be of atleast 6 characters and 20 characters")
	private String password;
	@NotNull

	@Size(min = 3 ,max = 100,message ="About must be of atleast 3 characters and 100 characters")
	private String about;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public UserDto(@NotNull String name, @Email String email, @NotNull String password, @NotNull String about) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
				+ about + "]";
	}
	public UserDto() {
		super();
	}
	
	

}
