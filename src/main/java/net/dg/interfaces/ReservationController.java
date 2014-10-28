package net.dg.interfaces;

import lombok.Data;
import net.dg.domain.movie.MovieRepository;
import net.dg.domain.movie.Reservation;
import net.dg.service.ShowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReservationController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowingService showingService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String reservation(Model model) {

        model.addAttribute("movies", movieRepository.findAll());

        return "reservation";
    }


    @RequestMapping(value="/reservation", method = RequestMethod.POST)
    public String register(@ModelAttribute ReservationForm form, Model model){
        Reservation reservation = showingService.reserveShowing(form.getShowingId(), form.getCustomerId(), form.getAudienceCount());
        model.addAttribute("reservation", reservation);
        return "complete";
    }

    @Data
    public static class ReservationForm {
        private Long showingId;
        private Long customerId;
        private int audienceCount;
    }

}
