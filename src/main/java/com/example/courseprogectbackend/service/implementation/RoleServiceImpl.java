package com.example.courseprogectbackend.service.implementation;

import com.example.courseprogectbackend.model.Role;
import com.example.courseprogectbackend.repository.RoleRepository;
import com.example.courseprogectbackend.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
