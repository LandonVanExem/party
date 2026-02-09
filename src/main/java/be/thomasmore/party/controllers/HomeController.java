package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

    @GetMapping({"/","/home"})
    public String home(Model model){
        int myCalculatedValue = 34 * 62;
        model.addAttribute("myCalculatedValue", myCalculatedValue);
        return "home";
    }


    @GetMapping("/about")
    public String about(Model model){
        String myName = "Landon Van Exem";
        String myStreet ="Reeweide 6";
        String myCity = "Schilde";
        model.addAttribute("myName", myName);
        model.addAttribute("myStreet", myStreet);
        model.addAttribute("myCity", myCity);
        return "about";
    }

    @GetMapping("/pay")
    public String pay(Model model){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = myDateObj.format(myFormatObj);
        model.addAttribute("currentDate", formattedDate);
        String formattedDate2 = myDateObj.plusDays(30).format(myFormatObj);
        model.addAttribute("payDate", formattedDate2);
        return "pay";
    }
  }
