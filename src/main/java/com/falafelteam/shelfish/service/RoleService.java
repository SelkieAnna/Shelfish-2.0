package com.falafelteam.shelfish.service;

import com.falafelteam.shelfish.model.users.Role;
import com.falafelteam.shelfish.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void add(String name, int priority) {
        if (!getAllRoles().contains(name)) {
            roleRepository.save(new Role(name, priority));
        }
    }

    public List<String> getAllRoles() {
        List<String> result = new LinkedList<>();
        roleRepository.findAll().forEach(role -> result.add(role.getName()));
        return result;
    }

    public Role getByName(String name) {
        return roleRepository.findByName(name);
    }
}
