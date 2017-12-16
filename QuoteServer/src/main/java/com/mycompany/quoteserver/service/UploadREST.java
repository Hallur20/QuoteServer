/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quoteserver.service;

import Facade.Factory;
import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import Filehandler.FileUpload;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 * REST Web Service
 *
 * @author hvn15
 */
@Path("upload")
public class UploadREST {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;
    Factory f = new Factory();
    private Gson gson = new Gson();
    private FileUpload fileUpload;

    public UploadREST() {
        f = new Factory();
        f.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));
        gson = new Gson();
        this.fileUpload = new FileUpload();
    }

    @POST
    @Path("file")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String bla(@DefaultValue("")
            @FormDataParam("file") InputStream file,
            @FormDataParam("file") FormDataContentDisposition fileDisposition) throws IOException {
        String fileName = fileDisposition.getFileName();
        fileUpload.saveFile(file, fileName);
        return gson.toJson("ok");
    }
    
    @GET
    @Path("url")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUrl(){
        String url =  "images/" + fileUpload.getName();
        return gson.toJson(url);
    }
}
