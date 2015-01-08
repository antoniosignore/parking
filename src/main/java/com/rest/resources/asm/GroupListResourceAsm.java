package com.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.parking.core.services.util.AccountGroupList;
import com.rest.mvc.GroupController;
import com.rest.resources.GroupListResource;


public class GroupListResourceAsm extends ResourceAssemblerSupport<AccountGroupList, GroupListResource> {

    public GroupListResourceAsm() {
        super(GroupController.class, GroupListResource.class);
    }

    @Override
    public GroupListResource toResource(AccountGroupList accountGroupList) {
        GroupListResource res = new GroupListResource();
        res.setGroups(new GroupResourceAsm().toResources(accountGroupList.getAccountGroups()));
        return res;
    }
}
