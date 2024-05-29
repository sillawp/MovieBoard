package com.wp.movie;

public class DBConnectionInfo {

	private String driverName;
	private String url;
	private String username;
	private String password;
	
	public DBConnectionInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "DBConnectionInfo [driverName=" + driverName + ", url=" + url + ", username=" + username + ", password="
				+ password + "]";
	}	
	
}
