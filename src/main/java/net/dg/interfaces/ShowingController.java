package net.dg.interfaces;

import net.dg.domain.movie.Showing;
import net.dg.domain.movie.ShowingRepository;
import net.dg.domain.movie.ShowingSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowingController {

    @Autowired
    private ShowingRepository showingRepository;

    @RequestMapping("/showings/movie/{movie}")
    public List<Showing> list(@PathVariable Long movie) {
        return showingRepository.findAll(ShowingSpec.movie(movie));
    }
}
