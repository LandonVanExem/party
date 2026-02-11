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

    public String text(Client client) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH");
        String formattedDate = myDateObj.format(myFormatObj);
        int tijd = Integer.parseInt(formattedDate);
        String text;
        if (tijd < 6) {
            text = "Goedennacht ";
        } else if (tijd < 12) {
            text = "Goedemorgen ";
        } else if (tijd < 17) {
            text = "Goedemiddag ";
        } else if (tijd < 22) {
            text = "Goedenavond ";
        } else {
            text = "Goedennacht ";
        }
        int orders = client.getNrOfOrders();
        if (orders <= 0) {
            text += client.getName() + ", en welkom!";
        } else if (orders < 10) {
            text += client.getName();
        } else if (orders < 50) {
            text += "beste " + client.getName();
        }
        if (orders >= 50) {
            text += "allerliefste " + client.getName();
        }
        if (orders >= 80) {
            text += ", jij bent een topper!";
        }
        return text;
    }

    @GetMapping("/clientgreeting")
    public String clientGreeting(Model model) {
        Optional<Client> clientFromDb = clientRepository.findById(1);
        Client client = clientFromDb.get();
        String text = text(client);
        model.addAttribute("text", text);
        return "clientgreeting";
    }

    private void calculateDiscount(Client client) {
        double totalAmount = client.getTotalAmount();
        if (totalAmount < 50) {
            client.setDiscountTaken(0);
        } else {
            client.setDiscountTaken(0.5 * totalAmount);
        }
    }

    private String clientDetails(int id) {
        Optional<Client> clientFromDb = clientRepository.findById(id);
        Client client = clientFromDb.get();
        calculateDiscount(client);
        String r = client.getName()+", je discount is "+client.getDiscountTaken();
        return r;
    }

    @GetMapping("/clientdetails")
    public String clientdetails(Model model){
        String text = clientDetails(1);
        model.addAttribute("text", text);
        return "clientdetails";
    }
}
