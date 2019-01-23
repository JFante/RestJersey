package com.mx.rest.util;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Client {

	public static void main(String[] args) {

		javax.ws.rs.client.Client cliente = ClientBuilder.newClient();
		WebTarget base = cliente.target("http://localhost:8080/RestJerseyAPI/restJersey/");
		Response response =base.path("persona").request(MediaType.APPLICATION_XML).get();
		String json = response.readEntity(String.class);
		System.out.println("header"+ response.getHeaders());
		System.out.println("Datos" + json);

	}
}
