/**
 * 
 */
package com.prueba.adapter.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.prueba.adapter.exception.BadRequestException;
import com.prueba.adapter.util.Validator;
import com.prueba.adapter.wsdl.CustomerRequest;
import com.prueba.adapter.wsdl.CustomerResponse;

/**
 * @author Marcos Macías
 *
 */
public class CustomerSoapClient extends WebServiceGatewaySupport{

	private static final Logger LOG = LogManager.getLogger(CustomerSoapClient.class);
	
	@Value("${wsdl_api_url}")
	private String wsdlApiUrl;
	
	public CustomerResponse getCustomerResponse(String documentType, String documentNumber) throws BadRequestException {
		LOG.trace("Petición recibida. Tipo documento: {} - Número documento: {}", documentType, documentNumber);
		CustomerRequest customerRequest = new CustomerRequest();
		customerRequest.setDocumentType(documentType);
		customerRequest.setDocumentNumber(documentNumber);
		
		Validator.validateRequestData(customerRequest);
		LOG.info("Validación exitosa de la solicitud");
		
		LOG.trace("Consumiendo servicio SOAP...");
		SoapActionCallback soapActionCallback = new SoapActionCallback("http://example.com/customerService/customerRequest");	
		return (CustomerResponse) getWebServiceTemplate().marshalSendAndReceive(wsdlApiUrl, customerRequest, soapActionCallback);
	}
}
