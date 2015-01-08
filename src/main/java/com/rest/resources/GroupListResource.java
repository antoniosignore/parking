package com.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class GroupListResource extends ResourceSupport {

    private List<GroupResource> groups = new ArrayList<GroupResource>();

    public List<GroupResource> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupResource> groups) {
        this.groups = groups;
    }
}
