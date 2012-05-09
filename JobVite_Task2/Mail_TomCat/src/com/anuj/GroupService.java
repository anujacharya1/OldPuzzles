package com.anuj;

import javax.ws.rs.GET;

import javax.ws.rs.Path;

import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;

import org.json.JSONArray;

/**
 * JAX-RS service definition
 * 
 * @author Anuj Acharya
 * 
 */
@Path("/group")
public interface GroupService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JSONArray getAllGroups();

}
