package com.ada.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ada.model.Agent;

@Repository
public interface AgentRepo extends CrudRepository<Agent, Long> {

}
