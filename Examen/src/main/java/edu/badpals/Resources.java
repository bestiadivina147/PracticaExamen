package edu.badpals;

import java.util.Optional;

import edu.badpals.domain.MagicalItem;
import edu.badpals.servicice.ServiceItem;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
@Transactional
public class Resources {

    @Inject
    ServiceItem service;

    @GET
    @Path("/itemcrudos")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String wellcome() {
        return "CRUD de Items!";
    }

    @GET
    @Path("/item/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get_item(@PathParam("name") String name){
        Optional<MagicalItem> item = service.cargaItem(name);
        return item.isPresent()?
                                Response.status(Response.Status.OK).entity(item).build():
                                Response.status(Response.Status.NOT_FOUND).build();
        
    }
     @POST
    @Path("/item")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    // curl -d '{"name": "Aged Brie", "quality": "50", "type": "MagicalItem"}' 
    // -H "Content-Type: application/json" -X POST http://localhost:8080/item -v
    public Response post(@Valid MagicalItem item) {
        Optional<MagicalItem> itemPersisted = service.creaItem(item);
        return itemPersisted.isPresent()?
            Response.status(Response.Status.CREATED).entity(itemPersisted.get()).build():
            Response.status(Response.Status.NOT_FOUND).build();
    }
}
