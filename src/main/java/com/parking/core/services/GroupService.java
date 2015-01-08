package com.parking.core.services;

import com.parking.core.models.entities.Group;
import com.parking.core.services.util.GroupList;

public interface GroupService {

    public GroupList findAllGroups();

    public GroupList findAllGroupsByAccount(Long accountId);

    public Group findGroup(Long id);

}
