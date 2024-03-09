package com.tanmoy.springboottest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "User", uniqueConstraints = @UniqueConstraint(columnNames = { "username" }))
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String username;
	private String password;
	private boolean isActive;
	private String firstName;
	private String lastName;

}