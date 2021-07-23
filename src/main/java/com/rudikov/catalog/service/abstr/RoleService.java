package com.rudikov.catalog.service.abstr;


import com.rudikov.catalog.model.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role findRoleById(Integer id);

    Role save(Role role);
}
