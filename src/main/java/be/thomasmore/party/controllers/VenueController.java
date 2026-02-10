package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VenueController {

    @GetMapping("/venuedetails")
    public String Venue(Model model){
        Venue venue1= new Venue();
        venue1.setVenueName("the Club");
        venue1.setLinkMoreInfo("https://theclubmechelen.be/");
        model.addAttribute("venue1",venue1);
        return "venuedetails";
    }
}
