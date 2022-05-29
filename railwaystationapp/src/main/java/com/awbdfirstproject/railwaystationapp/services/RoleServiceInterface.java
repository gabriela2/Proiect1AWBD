package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Role;

public interface RoleServiceInterface {
    Role findByName(String name);
}
