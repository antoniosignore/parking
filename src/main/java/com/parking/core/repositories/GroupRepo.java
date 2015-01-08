package com.parking.core.repositories;


import com.parking.core.models.entities.Group;

import java.util.List;

public interface GroupRepo {

    public Group createGroup(Group data);

    public List<Group> findAllGroups();

    public Group findGroup(Long id);

    public Group findGroupByTitle(String title);

    public List<Group> findGroupsByAccount(Long accountId);
}
