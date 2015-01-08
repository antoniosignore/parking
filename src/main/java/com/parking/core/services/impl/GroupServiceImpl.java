package com.parking.core.services.impl;

import com.parking.core.models.entities.Group;
import com.parking.core.repositories.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.parking.core.services.GroupService;
import com.parking.core.services.util.GroupList;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepo groupRepo;

    @Override
    public GroupList findAllGroups() {
        return new GroupList(groupRepo.findAllGroups());
    }

    @Override
    public GroupList findAllGroupsByAccount(Long accountId) {
        return new GroupList(groupRepo.findGroupsByAccount(accountId));
    }

    @Override
    public Group findGroup(Long id) {
        return groupRepo.findGroup(id);
    }
}
