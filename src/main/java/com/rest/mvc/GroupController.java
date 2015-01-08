package com.rest.mvc;

import com.parking.core.models.entities.AccountGroup;
import com.rest.resources.asm.GroupListResourceAsm;
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
import com.rest.resources.GroupListResource;
import com.rest.resources.GroupResource;
import com.rest.resources.asm.GroupResourceAsm;

@Controller
@RequestMapping("/rest/groups")
public class GroupController {
    private AccountGroupService accountGroupService;

    @Autowired
    public GroupController(AccountGroupService accountGroupService) {
        this.accountGroupService = accountGroupService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<GroupListResource> findAllStocks() {
        AccountGroupList accountGroupList = accountGroupService.findAllGroups();
        GroupListResource groupListResource = new GroupListResourceAsm().toResource(accountGroupList);
        return new ResponseEntity<GroupListResource>(groupListResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{groupId}",
            method = RequestMethod.GET)
    public ResponseEntity<GroupResource> getStock(@PathVariable Long groupId) {
        AccountGroup accountGroup = accountGroupService.findGroup(groupId);
        if (accountGroup != null) {
            GroupResource res = new GroupResourceAsm().toResource(accountGroup);
            return new ResponseEntity<GroupResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<GroupResource>(HttpStatus.NOT_FOUND);
        }
    }


}
