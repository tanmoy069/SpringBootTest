package com.tanmoy.springboottest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "UserEventLogs")
@Data
public class UserEventLogs implements Serializable {

	@Serial
	private static final long serialVersionUID = -1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 1000)
	private String log;

}