package com.mx.rest.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.mx.rest.PersonaDao;
import com.mx.rest.dto.Persona;

@Path("/persona")
public class PersonaResource {

	private PersonaDao personaDao = new PersonaDao();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscaPersonas(@QueryParam("nombre") String nombre) {
		List<Persona> personas;
		Response response;
		GenericEntity<List<Persona>> entity;
		if (nombre != null) {
			if (!nombre.isEmpty()) {
				personas = personaDao.getPersonas(nombre);
				entity = new GenericEntity<List<Persona>>(personas) {
				};
				return response = Response.ok(entity).build();
			}
		}
		personas = personaDao.getPersonas();
		entity = new GenericEntity<List<Persona>>(personas) {
		};
		response = Response.status(Status.OK).entity(entity).build();
		return response;
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response buscaPersonaXMl(@QueryParam("nombre") String nombre) {
		List<Persona> personas;
		Response response;
		GenericEntity<List<Persona>> entity;
		if (nombre != null) {
			if (!nombre.isEmpty()) {
				personas = personaDao.getPersonas(nombre);
				entity = new GenericEntity<List<Persona>>(personas) {
				};
				return response = Response.ok(entity).build();
			}
		}
		personas = personaDao.getPersonas();
		entity = new GenericEntity<List<Persona>>(personas) {
		};
		response = Response.status(Status.OK).entity(entity).build();
		return response;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{param}")
	public Response buscaPersona(@PathParam("param") int id) {
	
		Response response;
		Persona p = personaDao.getPersona(id);
		GenericEntity<Persona> entity = new GenericEntity<Persona>(p){};
		if (p.getId() == null) {
			response = Response.noContent().build();
			throw new WebApplicationException(response);
		}
		return response = Response.ok().entity(entity).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response agregarPersona(Persona p, @Context UriInfo info) {
		personaDao.addPersona(p);
		URI uri = info.getAbsolutePathBuilder().path(p.getId().toString()).build();
		System.out.println(uri.toString());
		return Response.created(uri).build();
	}

	@DELETE
	@Path("/{idPersona}")
	public void borraPersona(@PathParam("{idPersona}") int id) {
		personaDao.deletePersona(id);
	}
}
