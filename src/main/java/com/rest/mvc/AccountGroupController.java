package com.rest.mvc;

import com.parking.core.models.entities.AccountGroup;
import com.rest.resources.asm.AccountGroupListResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.parking.core.services.AccountGroupService;
import com.parking.core.services.util.AccountGroupList;
import com.rest.resources.AccountGroupListResource;
import com.rest.resources.AccountGroupResource;
import com.rest.resources.asm.AccountGroupResourceAsm;

@Controller
@RequestMapping("/rest/groups")
public class AccountGroupController {
    private AccountGroupService accountGroupService;

    @Autowired
    public AccountGroupController(AccountGroupService accountGroupService) {
        this.accountGroupService = accountGroupService;
    }

    @RequestMapping(method = RequestMethod.GET) @PreAuthorize("permitAll")
    public ResponseEntity<AccountGroupListResource> findAllGroups() {
        AccountGroupList accountGroupList = accountGroupService.findAllGroups();
        AccountGroupListResource accountGroupListResource = new AccountGroupListResourceAsm().toResource(accountGroupList);
        return new ResponseEntity<AccountGroupListResource>(accountGroupListResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
    public ResponseEntity<AccountGroupResource> getGroup(@PathVariable Long groupId) {
        AccountGroup accountGroup = accountGroupService.findGroup(groupId);
        if (accountGroup != null) {
            AccountGroupResource res = new AccountGroupResourceAsm().toResource(accountGroup);
            return new ResponseEntity<AccountGroupResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AccountGroupResource>(HttpStatus.NOT_FOUND);
        }
    }


}
