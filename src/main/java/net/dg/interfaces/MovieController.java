package net.dg.interfaces;

import net.dg.domain.movie.Movie;
import net.dg.domain.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @RequestMapping(value="/movies")
    public List<Movie> list() {
        return movieRepository.findAll();
    }
    
}
