package com.japarejo.personaltaste.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.japarejo.personaltaste.model.entities.Artwork;
import com.japarejo.personaltaste.model.entities.ArtworkType;

@Repository
public interface ArtworkRepository extends CrudRepository<Artwork, Integer>{

	@Query("SELECT DISTINCT c FROM ArtworkType c") 
	public Set<ArtworkType> findAllArtworkTypes();
	
	public List<Artwork> findAll();
		
	public Artwork findByName(String name);

	@Query("SELECT c FROM ArtworkType c WHERE c.name=:name")
	public ArtworkType findArtworkTypeByName(@Param("name") String name);
}
