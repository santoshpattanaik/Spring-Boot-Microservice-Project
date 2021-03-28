package com.sbm.moviecatalogservice.resources;

import com.sbm.moviecatalogservice.models.CatalogItem;
import com.sbm.moviecatalogservice.models.Movie;
import com.sbm.moviecatalogservice.models.Rating;
import com.sbm.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;

    public MovieCatalogResource(RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
    }

    // http://localhost:8081/catalog/1
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
/*
        List<Rating> ratings = Arrays.asList(
                new Rating("1234",4),
                new Rating("5678",3)
                );*/
        UserRating ratings = webClientBuilder.build()
                .get()
                .uri("http://rating-data-service/ratingsdata/user/" + userId)
                .retrieve()
                .bodyToMono(UserRating.class)
                .block();

        return ratings.getUserRating().stream().map(rating -> {
            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://movie-info-service/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class).block();
            return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
        }).collect(Collectors.toList());

        /*
            return ratings.stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(),movie.getDescription(),rating.getRating());
            }).collect(Collectors.toList());
        */

        /*
            return Collections.singletonList(
            new CatalogItem("Transformer","Test",4)
            );
        */
    }
}
