package com.rest.mvc;

import com.parking.core.models.entities.*;
import com.parking.core.services.*;
import com.parking.core.services.exceptions.AccountDoesNotExistException;
import com.parking.core.services.exceptions.AccountExistsException;
import com.parking.core.services.exceptions.BlogExistsException;
import com.parking.core.services.util.*;
import com.rest.exceptions.ConflictException;
import com.rest.exceptions.ForbiddenException;
import com.rest.exceptions.NotFoundException;
import com.rest.resources.*;
import com.rest.resources.asm.*;
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
@RequestMapping("/rest/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<AccountListResource> findAllAccounts(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "password", required = false) String password) {
        AccountList list = null;
        if (name == null) {
            list = accountService.findAllAccounts();
        } else {
            Account account = accountService.findByAccountName(name);
            list = new AccountList(new ArrayList<Account>());
            if (account != null) {
                if (password != null) {
                    if (account.getPassword().equals(password)) {
                        list = new AccountList(Arrays.asList(account));
                    }
                } else {
                    list = new AccountList(Arrays.asList(account));
                }
            }
        }
        AccountListResource res = new AccountListResourceAsm().toResource(list);
        return new ResponseEntity<AccountListResource>(res, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<AccountResource> createAccount(
            @RequestBody AccountResource sentAccount) {
        try {
            Account createdAccount = accountService.createAccount(sentAccount.toAccount());
            AccountResource res = new AccountResourceAsm().toResource(createdAccount);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<AccountResource>(res, headers, HttpStatus.CREATED);
        } catch (AccountExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<AccountResource> getAccount(
            @PathVariable Long accountId) {
        Account account = accountService.findAccount(accountId);
        if (account != null) {
            AccountResource res = new AccountResourceAsm().toResource(account);
            return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<BlogResource> createBlog(
            @PathVariable Long accountId,
            @RequestBody BlogResource res) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails details = (UserDetails) principal;
            Account loggedIn = accountService.findByAccountName(details.getUsername());
            if (loggedIn.getId() == accountId) {
                try {
                    Blog createdBlog = accountService.createBlog(accountId, res.toBlog());
                    BlogResource createdBlogRes = new BlogResourceAsm().toResource(createdBlog);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(URI.create(createdBlogRes.getLink("self").getHref()));
                    return new ResponseEntity<BlogResource>(createdBlogRes, headers, HttpStatus.CREATED);
                } catch (AccountDoesNotExistException exception) {
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

    @RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<BlogListResource> findAllBlogs(
            @PathVariable Long accountId) {
        try {
            BlogList blogList = accountService.findBlogsByAccount(accountId);
            BlogListResource blogListRes = new BlogListResourceAsm().toResource(blogList);
            return new ResponseEntity<BlogListResource>(blogListRes, HttpStatus.OK);
        } catch (AccountDoesNotExistException exception) {
            throw new NotFoundException(exception);
        }
    }

    @RequestMapping(value = "/{accountId}/accountGroups", method = RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<AccountGroupResource> createAccountGroup(
            @PathVariable Long accountId,
            @RequestBody AccountGroupResource res) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails details = (UserDetails) principal;
            Account loggedIn = accountService.findByAccountName(details.getUsername());
            if (loggedIn.getId() == accountId) {
                try {
                    AccountGroup createdAccountGroup = accountService.createAccountGroup(accountId, res.toAccountGroup());
                    AccountGroupResource accountGroupResource = new AccountGroupResourceAsm().toResource(createdAccountGroup);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(URI.create(accountGroupResource.getLink("self").getHref()));
                    return new ResponseEntity<AccountGroupResource>(accountGroupResource, headers, HttpStatus.CREATED);
                } catch (AccountDoesNotExistException exception) {
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

    @RequestMapping(value = "/{accountId}/accountGroups", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<AccountGroupListResource> findAllAccountGroups(
            @PathVariable Long accountId) {
        try {
            AccountGroupList blogList = accountService.findAccountGroupsByAccount(accountId);
            AccountGroupListResource blogListRes = new AccountGroupListResourceAsm().toResource(blogList);
            return new ResponseEntity<AccountGroupListResource>(blogListRes, HttpStatus.OK);
        } catch (AccountDoesNotExistException exception) {
            throw new NotFoundException(exception);
        }
    }

    @RequestMapping(value = "/{accountId}/parkings", method = RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<ParkingResource> createParking(
            @PathVariable Long accountId,
            @RequestBody ParkingResource res) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails details = (UserDetails) principal;
            Account loggedIn = accountService.findByAccountName(details.getUsername());
            if (loggedIn.getId() == accountId) {
                try {
                    Parking createdAccountGroup = accountService.createParking(accountId, res.toParking());
                    ParkingResource parkingResource = new ParkingResourceAsm().toResource(createdAccountGroup);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(URI.create(parkingResource.getLink("self").getHref()));
                    return new ResponseEntity<ParkingResource>(parkingResource, headers, HttpStatus.CREATED);
                } catch (AccountDoesNotExistException exception) {
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

    @RequestMapping(value = "/{accountId}/parkings", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<ParkingListResource> findAllParkings(
            @PathVariable Long accountId) {
        try {
            ParkingList blogList = accountService.findParkingsByAccount(accountId);
            ParkingListResource blogListRes = new ParkingListResourceAsm().toResource(blogList);
            return new ResponseEntity<ParkingListResource>(blogListRes, HttpStatus.OK);
        } catch (AccountDoesNotExistException exception) {
            throw new NotFoundException(exception);
        }
    }

    @RequestMapping(value = "/{accountId}/connections", method = RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<ConnectionResource> createConnection(
            @PathVariable Long accountId,
            @PathVariable Long receiverId,
            @RequestBody ConnectionResource res) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails details = (UserDetails) principal;
            Account loggedIn = accountService.findByAccountName(details.getUsername());
            if (loggedIn.getId() == accountId) {
                try {
                    Connection createdAccountGroup = accountService.createConnection(accountId, receiverId,res.toConnection());
                    ConnectionResource parkingResource = new ConnectionResourceAsm().toResource(createdAccountGroup);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(URI.create(parkingResource.getLink("self").getHref()));
                    return new ResponseEntity<ConnectionResource>(parkingResource, headers, HttpStatus.CREATED);
                } catch (AccountDoesNotExistException exception) {
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

    @RequestMapping(value = "/{accountId}/connections", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<ConnectionListResource> findAllConnections(
            @PathVariable Long accountId) {
        try {
            ConnectionList blogList = accountService.findConnectionsByAccount(accountId);
            ConnectionListResource blogListRes = new ConnectionListResourceAsm().toResource(blogList);
            return new ResponseEntity<ConnectionListResource>(blogListRes, HttpStatus.OK);
        } catch (AccountDoesNotExistException exception) {
            throw new NotFoundException(exception);
        }
    }

    @RequestMapping(value = "/{accountId}/vehicles", method = RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<VehicleResource> createVehicle(
            @PathVariable Long accountId,
            @RequestBody VehicleResource res) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails details = (UserDetails) principal;
            Account loggedIn = accountService.findByAccountName(details.getUsername());
            if (loggedIn.getId() == accountId) {
                try {
                    Vehicle createdAccountGroup = accountService.createVehicle(accountId, res.toVehicle());
                    VehicleResource parkingResource = new VehicleResourceAsm().toResource(createdAccountGroup);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(URI.create(parkingResource.getLink("self").getHref()));
                    return new ResponseEntity<VehicleResource>(parkingResource, headers, HttpStatus.CREATED);
                } catch (AccountDoesNotExistException exception) {
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

    @RequestMapping(value = "/{accountId}/vehicles", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<VehicleListResource> findAllVehicles(
            @PathVariable Long accountId) {
        try {
            VehicleList blogList = accountService.findVehiclesByAccount(accountId);
            VehicleListResource blogListRes = new VehicleListResourceAsm().toResource(blogList);
            return new ResponseEntity<VehicleListResource>(blogListRes, HttpStatus.OK);
        } catch (AccountDoesNotExistException exception) {
            throw new NotFoundException(exception);
        }
    }

}
