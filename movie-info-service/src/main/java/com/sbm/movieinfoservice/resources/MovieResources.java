package com.sbm.movieinfoservice.resources;

import com.sbm.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResources {

    // http://localhost:8082/movies/2

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId){
        return new Movie(movieId,"Action Thriller","Avenger");
    }
}
