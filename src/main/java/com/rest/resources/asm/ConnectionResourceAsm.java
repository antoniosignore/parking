package com.rest.resources.asm;

import com.parking.core.models.entities.Connection;
import com.rest.mvc.ConnectionController;
import com.rest.resources.ConnectionResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ConnectionResourceAsm extends ResourceAssemblerSupport<Connection, ConnectionResource> {
    
    public ConnectionResourceAsm() {
        super(ConnectionController.class, ConnectionResource.class);
    }

    @Override
    public ConnectionResource toResource(Connection connection) {
        ConnectionResource resource = new ConnectionResource();

        resource.setConfirmed(connection.getConfirmed());

        resource.add(linkTo(ConnectionController.class).slash(connection.getId()).withSelfRel());

        resource.setRid(connection.getId());

//        if (connection.getAccounts() != null)  {
//            resource.add(linkTo(AccountController.class).slash(connection.get().getId()).withRel("owner"));
//        }

        return resource;
    }
}
