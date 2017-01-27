package com.panda.prueba1.controller;

import com.panda.prueba1.Persona;
import com.panda.prueba1.service.facade.PersonaFacade;
import com.panda.prueba1.controller.util.HeaderUtil;
import com.panda.prueba1.security.Secured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * REST controller for managing Persona.
 */
@Api(value = "/api/persona", description = "Persona Controller")
@Path("/api/persona")
@Secured
public class PersonaController {

    private final Logger log = LoggerFactory.getLogger(PersonaController.class);

    @Inject
    private PersonaFacade personaFacade;

    /**
     * POST : Create a new persona.
     *
     * @param persona the persona to create
     * @return the Response with status 201 (Created) and with body the new
     * persona, or with status 400 (Bad Request) if the persona has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new persona", notes = "Create a new persona")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createPersona(Persona persona) throws URISyntaxException {
        log.debug("REST request to save Persona : {}", persona);
        personaFacade.create(persona);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/persona/" + persona.getId_persona())),
                "persona", persona.getId_persona().toString())
                .entity(persona).build();
    }

    /**
     * PUT : Updates an existing persona.
     *
     * @param persona the persona to update
     * @return the Response with status 200 (OK) and with body the updated
     * persona, or with status 400 (Bad Request) if the persona is not valid, or
     * with status 500 (Internal Server Error) if the persona couldn't be
     * updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update persona", notes = "Updates an existing persona")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updatePersona(Persona persona) throws URISyntaxException {
        log.debug("REST request to update Persona : {}", persona);
        personaFacade.edit(persona);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "persona", persona.getId_persona().toString())
                .entity(persona).build();
    }

    /**
     * GET : get all the personas.
     *
     * @return the Response with status 200 (OK) and the list of personas in
     * body
     *
     */
    @Timed
    @ApiOperation(value = "get all the personas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public List<Persona> getAllPersonas() {
        log.debug("REST request to get all Personas");
        List<Persona> personas = personaFacade.findAll();
        return personas;
    }

    /**
     * GET /:id_persona : get the "id_persona" persona.
     *
     * @param id_persona the id_persona of the persona to retrieve
     * @return the Response with status 200 (OK) and with body the persona, or
     * with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the persona")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id_persona}")
    @GET
    public Response getPersona(@PathParam("id_persona") Long id_persona) {
        log.debug("REST request to get Persona : {}", id_persona);
        Persona persona = personaFacade.find(id_persona);
        return Optional.ofNullable(persona)
                .map(result -> Response.status(Response.Status.OK).entity(persona).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id_persona : remove the "id_persona" persona.
     *
     * @param id_persona the id_persona of the persona to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the persona")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id_persona}")
    @DELETE
    public Response removePersona(@PathParam("id_persona") Long id_persona) {
        log.debug("REST request to delete Persona : {}", id_persona);
        personaFacade.remove(personaFacade.find(id_persona));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "persona", id_persona.toString()).build();
    }

}
