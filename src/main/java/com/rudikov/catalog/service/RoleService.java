package com.rudikov.catalog.service;


import com.rudikov.catalog.model.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role findRoleById(Integer id);

    void save(Role role);
}
