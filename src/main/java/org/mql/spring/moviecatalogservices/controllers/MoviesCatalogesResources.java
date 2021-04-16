package org.mql.spring.moviecatalogservices.controllers;

import org.mql.spring.moviecatalogservices.Entities.CatalogItem;
import org.mql.spring.moviecatalogservices.Entities.Movie;
import org.mql.spring.moviecatalogservices.Entities.Rating;
import org.mql.spring.moviecatalogservices.Entities.UserRating;
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
public class MoviesCatalogesResources {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
    {

        UserRating userRating = restTemplate.getForObject("http://rating-data-service/ratings/users/" + userId, UserRating.class);

        return userRating.getUserRating().stream().map(rating ->{

            Movie movie = restTemplate.getForObject("http://movie-info-service:8082/movies/"+rating.getMovieId(),Movie.class);
//                    Movie movie = webClientBuilder.build()
//                            .get()
//                            .uri("http://localhost:8082/movies/" + rating.getMovieId())
//                            .retrieve()
//                            .bodyToMono(Movie.class)
//                            .block();
                    return new CatalogItem(movie.getName(),"machine learning movie",rating.getRating());
    }
        ).collect(Collectors.toList());

//        return Collections.singletonList(
//                new CatalogItem("Matrix","machine learning movie",5)
//        );
    }
}
