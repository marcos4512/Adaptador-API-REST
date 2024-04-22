/**
 * 
 */
package com.prueba.adapter.exception;

/**
 * @author Marcos Macías
 *
 */
public class BadRequestException extends Exception {

	private static final long serialVersionUID = 1394570399937355860L;

	public BadRequestException(String message) {
		super(message);
	}
}
