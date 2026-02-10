package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class VenueController {
    private final VenueRepository venuerepository;

    public VenueController(VenueRepository venuerepository) {
        this.venuerepository = venuerepository;
    }

    @GetMapping("/venuedetails")
    public String Venue(Model model) {
//        Venue venue = new Venue();
//        venue.setVenueName("the Club");
//        venue.setLinkMoreInfo("https://theclubmechelen.be/");
//        model.addAttribute("venue", venue);

        Optional<Venue> venueFromDb = venuerepository.findById(1);
        if (venueFromDb.isPresent()){
            model.addAttribute("venue",venueFromDb.get());
        }

        return "venuedetails";
    }
}
