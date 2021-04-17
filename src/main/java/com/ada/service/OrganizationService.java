package com.ada.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.model.Organization;
import com.ada.repository.OrganizationRepo;

@Service
public class OrganizationService {

	@Autowired
	OrganizationRepo orgRepo;

	public Iterable<Organization> findAll() {
		return this.orgRepo.findAll();
	}

	public Optional<Organization> findById(Long id) {
		return this.orgRepo.findById(id);
	}

	public Organization save(Organization org) {
		return this.orgRepo.save(org);
	}

	public void deleteById(Long id) {
		Optional<Organization> entity = orgRepo.findById(id);
		orgRepo.delete(entity.get());
	}

}
