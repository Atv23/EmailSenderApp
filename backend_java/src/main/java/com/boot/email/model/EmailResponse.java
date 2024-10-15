package com.boot.email.model;

public class EmailResponse {
	private String status;

	public EmailResponse(String status) {
		super();
		this.status = status;
	}

	public EmailResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "EmailResponse [status=" + status + "]";
	}
	
	
}
