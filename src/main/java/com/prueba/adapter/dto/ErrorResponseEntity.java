/**
 * 
 */
package com.prueba.adapter.dto;

import java.io.Serializable;

/**
 * @author Marcos Macías
 *
 */
public class ErrorResponseEntity implements Serializable{
	
	private static final long serialVersionUID = -1857557482895577989L;
	private String timeStamp;
	private int status;
	private String error;
	private String message;
	
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
