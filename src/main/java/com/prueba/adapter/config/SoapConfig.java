/**
 * 
 */
package com.prueba.adapter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.prueba.adapter.client.CustomerSoapClient;

/**
 * @author Marcos Macías
 *
 */
@Configuration
public class SoapConfig {
	
	@Value("${wsdl_api_url}")
	private String wsdlApiUrl;

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller =  new Jaxb2Marshaller();
		marshaller.setContextPath("com.prueba.adapter.wsdl");
		return marshaller;
	}
	
	@Bean
	public CustomerSoapClient getCustomerSoapClient(Jaxb2Marshaller marshaller) {
		CustomerSoapClient customerSoapClient =  new CustomerSoapClient();
		customerSoapClient.setDefaultUri(wsdlApiUrl);
		customerSoapClient.setMarshaller(marshaller);
		customerSoapClient.setUnmarshaller(marshaller);
		return customerSoapClient;
	}
}
