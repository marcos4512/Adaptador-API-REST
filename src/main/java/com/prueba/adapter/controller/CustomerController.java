/**
 * 
 */
package com.prueba.adapter.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.adapter.client.CustomerSoapClient;
import com.prueba.adapter.dto.CustomerResponseEntity;
import com.prueba.adapter.wsdl.CustomerResponse;

/**
 * @author Marcos Macías
 *
 */
@RestController
@RequestMapping("/adapter-api")
public class CustomerController {
	
	private static final Logger LOG = LogManager.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerSoapClient customerClient;

	/**
	 * Controlador que recibe parámetros de entrada por URL
	 * y retorna un usuario basado en la busqueda obtenida
	 * realizada a un servicio SOAP
	 * @param type Tipo de documento ('P'- Pasaporte | 'C' - Cédula de ciudadanía)
	 * @param id Número de documento
	 * @return Información obtenida del usuario
	 * @throws com.prueba.adapter.exception.BadRequestException Manejando peticiones incorrectas
	 */
	@CrossOrigin(origins = "http://localhost:4200") //Configuración provisional para prueba en servidor local con Angular
	@GetMapping("/cliente/{type}/{id}")
	public ResponseEntity<?> getCustomerByTypeAndId(@PathVariable String type, @PathVariable String id) throws com.prueba.adapter.exception.BadRequestException   {
		LOG.info("Petición de consulta cliente recibida");
		CustomerResponse customerResponse = customerClient.getCustomerResponse(type, id);
		LOG.trace("Se consumió el servicio SOAP");
		CustomerResponseEntity customerResponseDto = new CustomerResponseEntity();
		
		customerResponseDto.setFirstName(customerResponse.getFirstName());
		customerResponseDto.setMiddleName(customerResponse.getMiddleName());
		customerResponseDto.setLastName(customerResponse.getLastName());
		customerResponseDto.setSecondLastName(customerResponse.getSecondLastName());
		customerResponseDto.setPhone(customerResponse.getPhone());
		customerResponseDto.setAddress(customerResponse.getAddress());
		customerResponseDto.setCity(customerResponse.getCity());
		
		LOG.info("Enviando respuesta al cliente...");
		return new ResponseEntity<>(customerResponseDto, HttpStatus.OK);
	}
}
