package com.user.responce;

import lombok.Data;

@Data
public class RegisterResponce {

	private String email;

	private String massege;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMassege() {
		return massege;
	}

	public void setMassege(String massege) {
		this.massege = massege;
	}

}
