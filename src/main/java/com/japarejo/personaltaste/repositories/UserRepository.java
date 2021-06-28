package com.japarejo.personaltaste.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.japarejo.personaltaste.model.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer>{

}
