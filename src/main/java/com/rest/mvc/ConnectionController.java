package com.rest.mvc;

import com.parking.core.models.entities.Connection;
import com.parking.core.models.entities.Blog;
import com.parking.core.services.ConnectionService;
import com.parking.core.services.exceptions.ConnectionDoesNotExistException;
import com.parking.core.services.exceptions.ConnectionExistsException;
import com.parking.core.services.exceptions.BlogExistsException;
import com.parking.core.services.util.ConnectionList;
import com.parking.core.services.util.BlogList;
import com.rest.exceptions.ConflictException;
import com.rest.exceptions.ForbiddenException;
import com.rest.exceptions.NotFoundException;
import com.rest.resources.ConnectionListResource;
import com.rest.resources.ConnectionResource;
import com.rest.resources.BlogListResource;
import com.rest.resources.BlogResource;
import com.rest.resources.asm.ConnectionListResourceAsm;
import com.rest.resources.asm.ConnectionResourceAsm;
import com.rest.resources.asm.BlogListResourceAsm;
import com.rest.resources.asm.BlogResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

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
    public ResponseEntity<ConnectionListResource> findAllConnections(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "password", required = false) String password) {
        ConnectionList list = null;
        if (name == null) {
            list = connectionService.findAllConnections();
        } else {
            Connection connection = connectionService.findByConnectionName(name);
            list = new ConnectionList(new ArrayList<Connection>());
            if (connection != null) {
                if (password != null) {
                    if (connection.getPassword().equals(password)) {
                        list = new ConnectionList(Arrays.asList(connection));
                    }
                } else {
                    list = new ConnectionList(Arrays.asList(connection));
                }
            }
        }
        ConnectionListResource res = new ConnectionListResourceAsm().toResource(list);
        return new ResponseEntity<ConnectionListResource>(res, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<ConnectionResource> createConnection(
            @RequestBody ConnectionResource sentConnection
    ) {
        try {
            Connection createdConnection = connectionService.createConnection(sentConnection.toConnection());
            ConnectionResource res = new ConnectionResourceAsm().toResource(createdConnection);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<ConnectionResource>(res, headers, HttpStatus.CREATED);
        } catch (ConnectionExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @RequestMapping(value = "/{connectionId}",
            method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<ConnectionResource> getConnection(
            @PathVariable Long connectionId
    ) {
        Connection connection = connectionService.findConnection(connectionId);
        if (connection != null) {
            ConnectionResource res = new ConnectionResourceAsm().toResource(connection);
            return new ResponseEntity<ConnectionResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<ConnectionResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{connectionId}/blogs",
            method = RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<BlogResource> createBlog(
            @PathVariable Long connectionId,
            @RequestBody BlogResource res) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails details = (UserDetails) principal;
            Connection loggedIn = connectionService.findByConnectionName(details.getUsername());
            if (loggedIn.getId() == connectionId) {
                try {
                    Blog createdBlog = connectionService.createBlog(connectionId, res.toBlog());
                    BlogResource createdBlogRes = new BlogResourceAsm().toResource(createdBlog);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(URI.create(createdBlogRes.getLink("self").getHref()));
                    return new ResponseEntity<BlogResource>(createdBlogRes, headers, HttpStatus.CREATED);
                } catch (ConnectionDoesNotExistException exception) {
                    throw new NotFoundException(exception);
                } catch (BlogExistsException exception) {
                    throw new ConflictException(exception);
                }
            } else {
                throw new ForbiddenException();
            }
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(value = "/{connectionId}/blogs",
            method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<BlogListResource> findAllBlogs(
            @PathVariable Long connectionId) {
        try {
            BlogList blogList = connectionService.findBlogsByConnection(connectionId);
            BlogListResource blogListRes = new BlogListResourceAsm().toResource(blogList);
            return new ResponseEntity<BlogListResource>(blogListRes, HttpStatus.OK);
        } catch (ConnectionDoesNotExistException exception) {
            throw new NotFoundException(exception);
        }
    }


}
