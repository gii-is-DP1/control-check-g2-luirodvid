package org.springframework.samples.petclinic.feeding;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FeedingRepository extends CrudRepository<Feeding,Integer>{
    
    @Query("Select p FROM FeedingType p")
    List<FeedingType> findAllFeedingTypes();
    Optional<Feeding> findById(int id);
    List<Feeding> findAll();
    
    @Query("Select p FROM FeedingType p WHERE p.name =?1")
    FeedingType getFeedingType(String name);


}
