package com.ada.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ada.model.Organization;

@Repository
public interface OrganizationRepo extends CrudRepository<Organization, Long> {

}
