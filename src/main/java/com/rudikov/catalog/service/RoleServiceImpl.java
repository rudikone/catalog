package com.rudikov.catalog.service;

import com.rudikov.catalog.model.entity.Role;
import com.rudikov.catalog.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        roleRepo.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role findRoleById(Integer id) {
        return roleRepo.findById(id).get();
    }

    @Override
    public void save(Role role) {
        roleRepo.save(role);
    }
}
