package com.logoocc.domain;

import java.io.Serializable;

public class JwtUser implements Serializable {

	private static final long serialVersionUID = -6016464164486803678L;
	private String uuid;
	private String username;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "JwtUser [uuid=" + uuid + ", username=" + username + "]";
	}

}