package sample_airbnb.projects.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import sample_airbnb.projects.model.Listings;

public interface ListingsRepository extends MongoRepository<Listings, String>{

    Page<Listings> findByPropertyType(String propertyType, Pageable pageable);
    
    Page<Listings> findByRoomType(String roomType, Pageable pageable);
    
}
