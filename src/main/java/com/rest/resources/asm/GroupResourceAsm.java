package com.rest.resources.asm;

import com.parking.core.models.entities.Group;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.rest.mvc.GroupController;
import com.rest.resources.GroupResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class GroupResourceAsm extends ResourceAssemblerSupport<Group, GroupResource> {
    public GroupResourceAsm() {
        super(GroupController.class, GroupResource.class);
    }

    @Override
    public GroupResource toResource(Group group) {
        GroupResource resource = new GroupResource();

        resource.setGroupName(group.getGroupName());
        resource.setGroupDescription(group.getGroupDesc());

        resource.add(linkTo(GroupController.class).slash(group.getId()).withSelfRel());
        resource.add(linkTo(GroupController.class).slash(group.getId()).slash("account-entries").withRel("entries"));

        resource.setRid(group.getId());

//        if (group.getAccounts() != null)  {
//            resource.add(linkTo(AccountController.class).slash(group.get().getId()).withRel("owner"));
//        }
        return resource;
    }
}
