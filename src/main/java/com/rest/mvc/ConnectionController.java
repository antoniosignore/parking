package com.rest.mvc;

import com.parking.core.models.entities.Connection;
import com.parking.core.services.ConnectionService;
import com.parking.core.services.util.ConnectionList;
import com.rest.resources.ConnectionListResource;
import com.rest.resources.ConnectionListResource;
import com.rest.resources.ConnectionResource;
import com.rest.resources.asm.ConnectionListResourceAsm;
import com.rest.resources.asm.ConnectionResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/rest/connections")
public class ConnectionController {
    private ConnectionService connectionService;

    @Autowired
    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<ConnectionListResource> findAllConnections() {
        ConnectionList connectionList = connectionService.findAllConnections();
        ConnectionListResource connectionListResource = new ConnectionListResourceAsm().toResource(connectionList);
        return new ResponseEntity<ConnectionListResource>(connectionListResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{connectionId}",
            method = RequestMethod.GET)
    public ResponseEntity<ConnectionResource> getConnection(@PathVariable Long connectionId) {
        Connection connection = connectionService.findConnection(connectionId);
        if (connection != null) {
            ConnectionResource res = new ConnectionResourceAsm().toResource(connection);
            return new ResponseEntity<ConnectionResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<ConnectionResource>(HttpStatus.NOT_FOUND);
        }
    }


}
