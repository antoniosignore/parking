package com.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.parking.core.services.util.GroupList;
import com.rest.mvc.GroupController;
import com.rest.resources.GroupListResource;


public class GroupListResourceAsm extends ResourceAssemblerSupport<GroupList, GroupListResource> {

    public GroupListResourceAsm() {
        super(GroupController.class, GroupListResource.class);
    }

    @Override
    public GroupListResource toResource(GroupList groupList) {
        GroupListResource res = new GroupListResource();
        res.setGroups(new GroupResourceAsm().toResources(groupList.getAccountGroups()));
        return res;
    }
}
