/**
 * 
 */
package com.prueba.adapter.util;
import com.prueba.adapter.exception.BadRequestException;
import com.prueba.adapter.wsdl.CustomerRequest;

/**
 * @author Marcos Macías
 *
 */
public class Validator {
	private Validator() {}

	public static void validateRequestData(CustomerRequest request) throws BadRequestException {

		if (!isAValidType(request.getDocumentType()))
			throw new BadRequestException(
					"El valor ingresado para tipo de documento no está permitido. Únicamente se aceptan valores 'P' (Pasaporte) y 'C' (Cédula)");
		if (!isANumber(request.getDocumentNumber()))
			throw new BadRequestException(
					"El número de documento proporcionado no cumple con un formato numérico aceptado.");
		if (!isValidNumberLongitude(request.getDocumentNumber()))
			throw new BadRequestException("El número de documento proporcionado no cumple con la longitud esperada.");
	}

	private static boolean isValidNumberLongitude(String number) {
		if (number == null)
			return false;
		return (number.length() >= 8 && number.length() <= 11);
	}

	private static boolean isANumber(String number) {
		if (number == null)
			return false;
		try {
			Double.parseDouble(number);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static boolean isAValidType(String documentType) {
		if (documentType == null)
			return false;
		return (documentType.equals("C") || documentType.equals("P"));
	}

}
