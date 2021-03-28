package com.sbm.ratingdataservice.resources;

import com.sbm.ratingdataservice.models.Rating;
import com.sbm.ratingdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {
    // http://localhost:8083/ratingsdata/foo

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId,4);
    }

    @RequestMapping("user/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("1234",4),
                new Rating("5678",3)
                );
        return new UserRating(ratings);
    }
}
