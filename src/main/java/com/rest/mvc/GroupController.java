package com.rest.mvc;

import com.parking.core.models.entities.Group;
import com.rest.resources.asm.GroupListResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.parking.core.services.GroupService;
import com.parking.core.services.util.GroupList;
import com.rest.resources.GroupListResource;
import com.rest.resources.GroupResource;
import com.rest.resources.asm.GroupResourceAsm;

@Controller
@RequestMapping("/rest/groups")
public class GroupController {
    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<GroupListResource> findAllStocks() {
        GroupList groupList = groupService.findAllGroups();
        GroupListResource groupListResource = new GroupListResourceAsm().toResource(groupList);
        return new ResponseEntity<GroupListResource>(groupListResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{groupId}",
            method = RequestMethod.GET)
    public ResponseEntity<GroupResource> getStock(@PathVariable Long groupId) {
        Group group = groupService.findGroup(groupId);
        if (group != null) {
            GroupResource res = new GroupResourceAsm().toResource(group);
            return new ResponseEntity<GroupResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<GroupResource>(HttpStatus.NOT_FOUND);
        }
    }


}
