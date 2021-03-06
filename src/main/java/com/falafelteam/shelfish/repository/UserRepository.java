package com.falafelteam.shelfish.repository;

import com.falafelteam.shelfish.model.users.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findById(int id);

    User findByName(String name);

    User findByLogin(String login);
}
