package com.falafelteam.shelfish.repository;

import com.falafelteam.shelfish.model.users.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{
}
