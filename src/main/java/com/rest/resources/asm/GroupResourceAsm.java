package com.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.parking.core.models.entities.dtmc.Stock;
import com.rest.mvc.AccountController;
import com.rest.mvc.GroupController;
import com.rest.resources.GroupResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class GroupResourceAsm extends ResourceAssemblerSupport<Stock, GroupResource> {
    public GroupResourceAsm() {
        super(GroupController.class, GroupResource.class);
    }

    @Override
    public GroupResource toResource(Stock stock) {
        GroupResource resource = new GroupResource();
        resource.setGroupName(stock.getName());
        resource.add(linkTo(GroupController.class).slash(stock.getId()).withSelfRel());
        resource.setRid(stock.getId());
        if (stock.getOwner() != null)
            resource.add(linkTo(AccountController.class).slash(stock.getOwner().getId()).withRel("owner"));
        return resource;
    }
}
