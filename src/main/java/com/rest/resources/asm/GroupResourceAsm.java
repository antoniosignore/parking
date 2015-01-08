package com.rest.resources.asm;

import com.parking.core.models.entities.AccountGroup;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.rest.mvc.GroupController;
import com.rest.resources.GroupResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class GroupResourceAsm extends ResourceAssemblerSupport<AccountGroup, GroupResource> {
    public GroupResourceAsm() {
        super(GroupController.class, GroupResource.class);
    }

    @Override
    public GroupResource toResource(AccountGroup accountGroup) {
        GroupResource resource = new GroupResource();

        resource.setGroupName(accountGroup.getGroupName());
        resource.setGroupDescription(accountGroup.getGroupDesc());

        resource.add(linkTo(GroupController.class).slash(accountGroup.getId()).withSelfRel());
        resource.add(linkTo(GroupController.class).slash(accountGroup.getId()).slash("account-entries").withRel("entries"));

        resource.setRid(accountGroup.getId());

//        if (group.getAccounts() != null)  {
//            resource.add(linkTo(AccountController.class).slash(group.get().getId()).withRel("owner"));
//        }
        return resource;
    }
}
