package sample_airbnb.projects.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "listingsAndReviews")
public class Listings {

    @Id
    @Field("_id")
    private String id;
    
    @Field("listing_url")
    private String listingUrl;
    
    private String name;
    private String summary;
    private String space;
    private String description;
    
    @Field("neighborhood_overview")
    private String neighborhoodOverview;
    
    private String notes;
    private String transit;
    private String access;
    private String interaction;
    
    @Field("house_rules")
    private String houseRules;
    
    @Field("property_type")
    private String propertyType;
    
    @Field("room_type")
    private String roomType;
    
    @Field("bed_type")
    private String bedType;
    
    @Field("minimum_nights")
    private String minimumNights;
    
    @Field("maximum_nights")
    private String maximumNights;
    
    @Field("cancellation_policy")
    private String cancellationPolicy;
    
    @Field("last_scraped")
    private Date lastScraped;
    
    @Field("calendar_last_scraped")
    private Date calendarLastScraped;
    
    @Field("first_review")
    private Date firstReview;
    
    @Field("last_review")
    private Date lastReview;
    
    private Integer accommodates;
    private Integer bedrooms;
    private Integer beds;
    
    @Field("number_of_reviews")
    private Integer numberOfReviews;
    
    private BigDecimal bathrooms;
    private List<String> amenities;
    private BigDecimal price;
    
    @Field("weekly_price")
    private BigDecimal weeklyPrice;
    
    @Field("monthly_price")
    private BigDecimal monthlyPrice;
    
    @Field("cleaning_fee")
    private BigDecimal cleaningFee;
    
    @Field("extra_people")
    private BigDecimal extraPeople;
    
    @Field("guests_included")
    private BigDecimal guestsIncluded;
    
    private Images images;
    private Host host;
    private Address address;
    private Availability availability;
    
    @Field("review_scores")
    private ReviewScores reviewScores;
    
    private List<Review> reviews;
    
    @Data
    public static class Images {
        @Field("thumbnail_url")
        private String thumbnailUrl;
        
        @Field("medium_url")
        private String mediumUrl;
        
        @Field("picture_url")
        private String pictureUrl;
        
        @Field("xl_picture_url")
        private String xlPictureUrl;
    }
    
    @Data
    public static class Host {
        @Field("host_id")
        private String hostId;
        
        @Field("host_url")
        private String hostUrl;
        
        @Field("host_name")
        private String hostName;
        
        @Field("host_location")
        private String hostLocation;
        
        @Field("host_about")
        private String hostAbout;
        
        @Field("host_thumbnail_url")
        private String hostThumbnailUrl;
        
        @Field("host_picture_url")
        private String hostPictureUrl;
        
        @Field("host_neighbourhood")
        private String hostNeighbourhood;
        
        @Field("host_is_superhost")
        private Boolean hostIsSuperhost;
        
        @Field("host_has_profile_pic")
        private Boolean hostHasProfilePic;
        
        @Field("host_identity_verified")
        private Boolean hostIdentityVerified;
        
        @Field("host_listings_count")
        private Integer hostListingsCount;
        
        @Field("host_total_listings_count")
        private Integer hostTotalListingsCount;
        
        @Field("host_verifications")
        private List<String> hostVerifications;
    }
    
    @Data
    public static class Address {
        private String street;
        private String suburb;
        
        @Field("government_area")
        private String governmentArea;
        
        private String market;
        private String country;
        
        @Field("country_code")
        private String countryCode;
        
        private Location location;
    }
    
    @Data
    public static class Location {
        private String type;
        private List<Double> coordinates;
        
        @Field("is_location_exact")
        private Boolean isLocationExact;
    }
    
    @Data
    public static class Availability {
        @Field("availability_30")
        private Integer availability30;
        
        @Field("availability_60")
        private Integer availability60;
        
        @Field("availability_90")
        private Integer availability90;
        
        @Field("availability_365")
        private Integer availability365;
    }
    
    @Data
    public static class ReviewScores {
        @Field("review_scores_accuracy")
        private Integer reviewScoresAccuracy;
        
        @Field("review_scores_cleanliness")
        private Integer reviewScoresCleanliness;
        
        @Field("review_scores_checkin")
        private Integer reviewScoresCheckin;
        
        @Field("review_scores_communication")
        private Integer reviewScoresCommunication;
        
        @Field("review_scores_location")
        private Integer reviewScoresLocation;
        
        @Field("review_scores_value")
        private Integer reviewScoresValue;
        
        @Field("review_scores_rating")
        private Integer reviewScoresRating;
    }
    
    @Data
    public static class Review {
        @Id
        @Field("_id")
        private String id;
        
        private Date date;
        
        @Field("listing_id")
        private String listingId;
        
        @Field("reviewer_id")
        private String reviewerId;
        
        @Field("reviewer_name")
        private String reviewerName;
        
        private String comments;
    }
}

