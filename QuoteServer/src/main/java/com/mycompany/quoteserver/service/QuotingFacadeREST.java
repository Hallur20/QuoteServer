/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quoteserver.service;

import Facade.Factory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import Filehandler.FileUpload;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author hvn15
 */
@Stateless
@Path("quotes")
public class QuotingFacadeREST {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;
    Factory f = new Factory();
    private Gson gson = new Gson();
    private FileUpload fileUpload;

    public QuotingFacadeREST() {
        f = new Factory();
        f.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));
        gson = new Gson();
        this.fileUpload = new FileUpload();
    }

    @PUT
    @Path("edit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void edit(String content) {
        String quote = null;
        JsonObject body = new JsonParser().parse(content).getAsJsonObject();
        if (body.has("newQuote")) {
            quote = body.get("newQuote").getAsString();
        }
        if (quote != null) {
            f.edit(quote);
        }
    }

    @GET
    @Path("get")
    @Produces({MediaType.APPLICATION_JSON})
    public String findAll() {
        return gson.toJson(f.getAll());
    }

    @PUT
    @Path("password")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String pw(String content) {
        JsonObject body = new JsonParser().parse(content).getAsJsonObject();
        String bool = "false";
        if (body.has("password")) {
            if (body.get("password").getAsString().equals("r@mmst3in1")) {
                bool = "true";
            }
        }
        return gson.toJson(bool);
    }
}
