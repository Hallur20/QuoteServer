/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quoteserver.service;

import Entities.Cookie;
import Facade.Factory;
import Filehandler.FileUpload;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author hvn15
 */
@Path("cookie")
public class CookieResource {

    @Context
    private UriInfo context;
    @PersistenceContext(unitName = "pu")
    private EntityManager em;
    Factory f = new Factory();
    private Gson gson = new Gson();

    public CookieResource() {
        f = new Factory();
        f.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));
        gson = new Gson();
    }
    /**
     * Creates a new instance of CookieqResource
     * @return 
     */

    
    @GET
    @Path("cookieClick")
    @Produces(MediaType.APPLICATION_JSON)
    public String edit() {
        List<Cookie> c = new ArrayList();
        c.add(new Cookie(f.addToCookie()));
        
        return gson.toJson(c);
    }
}
