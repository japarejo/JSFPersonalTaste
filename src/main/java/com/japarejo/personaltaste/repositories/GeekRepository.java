package com.japarejo.personaltaste.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.japarejo.personaltaste.model.entities.Geek;

@Repository
public interface GeekRepository extends CrudRepository<Geek,Integer>{
	@Query("SELECT g FROM Geek g WHERE g.user.username=:username")
	public Geek findByUsername(@Param("username")String username);	
	
	public List<Geek> findAll();
}
