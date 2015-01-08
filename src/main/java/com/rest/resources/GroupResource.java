package com.rest.resources;

import com.parking.core.models.entities.Group;
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

    public Group toGroup() {
        Group group = new Group();
        group.setGroupName(groupName);
        group.setGroupDesc(groupDescription);
        return group;
    }
}
