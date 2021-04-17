package com.ada.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ada.model.ERoles;
import com.ada.model.Role;

public interface RoleRepo extends CrudRepository<Role, Integer> {

	Optional<Role> findByName(ERoles name);

}
