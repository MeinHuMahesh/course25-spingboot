package com.example.demo.DTO;

import lombok.Data;

@Data
public class Errors {
	private String timestamp;
	private String error;
	private int status;
	private String message;
}
