package sample_airbnb.projects.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sample_airbnb.projects.model.Listings;
import sample_airbnb.projects.repositories.ListingsRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/listings")
public class ListingsController {

    @Autowired
    private ListingsRepository listingsRepository;

    // Get all listings with pagination
    @GetMapping
    public ResponseEntity<Page<Listings>> getAllListings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        
        Sort sort = sortDirection.equalsIgnoreCase("desc") 
                ? Sort.by(sortBy).descending() 
                : Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Listings> listings = listingsRepository.findAll(pageable);
        
        return ResponseEntity.ok(listings);
    }

    // Get listing by ID
    @GetMapping("/{id}")
    public ResponseEntity<Listings> getListingById(@PathVariable String id) {
        Optional<Listings> listing = listingsRepository.findById(id);
        return listing.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new listing
    @PostMapping
    public ResponseEntity<Listings> createListing(@RequestBody Listings listing) {
        Listings savedListing = listingsRepository.save(listing);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedListing);
    }

    // Update existing listing
    @PutMapping("/{id}")
    public ResponseEntity<Listings> updateListing(
            @PathVariable String id, 
            @RequestBody Listings listingDetails) {
        
        Optional<Listings> existingListing = listingsRepository.findById(id);
        
        if (existingListing.isPresent()) {
            listingDetails.setId(id);
            Listings updatedListing = listingsRepository.save(listingDetails);
            return ResponseEntity.ok(updatedListing);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Partial update (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<Listings> partialUpdateListing(
            @PathVariable String id, 
            @RequestBody Listings listingDetails) {
        
        Optional<Listings> existingListing = listingsRepository.findById(id);
        
        if (existingListing.isPresent()) {
            Listings listing = existingListing.get();
            // Update only non-null fields
            if (listingDetails.getName() != null) listing.setName(listingDetails.getName());
            if (listingDetails.getSummary() != null) listing.setSummary(listingDetails.getSummary());
            if (listingDetails.getPrice() != null) listing.setPrice(listingDetails.getPrice());
            if (listingDetails.getPropertyType() != null) listing.setPropertyType(listingDetails.getPropertyType());
            // Add more fields as needed
            
            Listings updatedListing = listingsRepository.save(listing);
            return ResponseEntity.ok(updatedListing);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete listing
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable String id) {
        if (listingsRepository.existsById(id)) {
            listingsRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get listings by property type with pagination
    @GetMapping("/property-type/{propertyType}")
    public ResponseEntity<Page<Listings>> getListingsByPropertyType(
            @PathVariable String propertyType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Listings> listings = listingsRepository.findByPropertyType(propertyType, pageable);
        return ResponseEntity.ok(listings);
    }

    // Get listings by room type with pagination
    @GetMapping("/room-type/{roomType}")
    public ResponseEntity<Page<Listings>> getListingsByRoomType(
            @PathVariable String roomType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Listings> listings = listingsRepository.findByRoomType(roomType, pageable);
        return ResponseEntity.ok(listings);
    }
}
