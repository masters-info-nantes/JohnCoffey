package fr.alma.mw1516.services;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by julien on 04/12/15.
 */
@Path("backend")
public class BackEnd {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("bonjour")
    public Response getBonjour(){
        return Response
                .status(401)
                .type(MediaType.APPLICATION_JSON)
                .entity("erreur auth")
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("callback/{token}")
    public Response getCallback(@PathParam("token")String tok){
        return postToken(tok);
    }

    @POST
    @Path("token/{token}")
    public Response postToken(@PathParam("token")String tok){
        return Response
                .status(200)
                .build();
    }


}
