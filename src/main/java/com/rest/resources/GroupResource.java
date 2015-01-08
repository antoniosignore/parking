package com.rest.resources;

import com.parking.core.models.entities.AccountGroup;
import org.springframework.hateoas.ResourceSupport;

public class GroupResource extends ResourceSupport {

    private String groupName;

    private String groupDescription;

    private Long rid;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public AccountGroup toGroup() {
        AccountGroup accountGroup = new AccountGroup();
        accountGroup.setGroupName(groupName);
        accountGroup.setGroupDesc(groupDescription);
        return accountGroup;
    }
}
