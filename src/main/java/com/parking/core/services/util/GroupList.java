package com.parking.core.services.util;


import com.parking.core.models.entities.parking.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupList {

    private List<Group> groups = new ArrayList<Group>();

    public GroupList(List<Group> resultList) {
        this.groups = resultList;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
