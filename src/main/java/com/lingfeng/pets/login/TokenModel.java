package com.lingfeng.pets.login;

import lombok.Data;

@Data
public class TokenModel {
	
	private String userId;
	
	private String token;

	public TokenModel(String userId, String token) {
		this.userId = userId;
		this.token = token;
	}
	
}
