package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Client;
import be.thomasmore.party.repositories.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clientgreeting")
    public String clientGreeting(Model model){
        Optional<Client> clientFromDb = clientRepository.findById(1);
        if (clientFromDb.isPresent()){
            model.addAttribute("client",clientFromDb.get());
        }
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH");
        String formattedDate = myDateObj.format(myFormatObj);
        int tijd = Integer.parseInt(formattedDate);
        String text;
        if (tijd<6 ){
            text="Goedennacht ";
        } else if (tijd<12) {
            text="Goedemorgen ";
        } else if (tijd<17) {
            text="Goedemiddag ";
        } else if (tijd<22) {
            text="Goedenavond ";
        }else {
            text="Goedennacht ";
        }
        int orders =clientFromDb.get().getNrOfOrders();
        if (orders<=0){
            text+=clientFromDb.get().getName()+", en welkom!";
        } else if (orders<10) {
            text+=clientFromDb.get().getName();
        } else if (orders<50) {
            text+="beste "+clientFromDb.get().getName();
        }
        if (orders>=50){
            text+="allerliefste "+clientFromDb.get().getName();
        }
        if (orders>=80){
            text+=", jij bent een topper!";
        }
        model.addAttribute("text", text);
        return "clientgreeting";
    }
}
