package com.rest.resources.asm;

import com.parking.core.models.entities.AccountGroup;
import com.rest.mvc.AccountController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.rest.mvc.AccountGroupController;
import com.rest.resources.AccountGroupResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class AccountGroupResourceAsm extends ResourceAssemblerSupport<AccountGroup, AccountGroupResource> {
    public AccountGroupResourceAsm() {
        super(AccountGroupController.class, AccountGroupResource.class);
    }

    @Override
    public AccountGroupResource toResource(AccountGroup accountGroup) {
        AccountGroupResource resource = new AccountGroupResource();

        resource.setGroupName(accountGroup.getGroupName());
        resource.setGroupDescription(accountGroup.getGroupDesc());

        resource.add(linkTo(AccountGroupController.class).slash(accountGroup.getId()).withSelfRel());
        resource.add(linkTo(AccountGroupController.class).slash(accountGroup.getId()).slash("account-entries").withRel("entries"));

        resource.setRid(accountGroup.getId());

//        if (accountGroup.getAccounts() != null)  {
//            resource.add(linkTo(AccountController.class).slash(group.get().getId()).withRel("owner"));
//        }
        return resource;
    }
}
