package taxi.booking.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping({"", "home", "welcome", "index"})
    public String welcomeView() {
        return "index";  // resolves to templates/index.html
    }

    @GetMapping("about")
    public String aboutView() {
        return "about";  // resolves to templates/about.html
    }

    @GetMapping("cars")
    public String carsView() {
        return "cars";  // resolves to templates/cars.html
    }

    @GetMapping("services")
    public String servicesView() {
        return "services";  // resolves to templates/services.html
    }

    @GetMapping("contacts")
    public String contactsView() {
        return "contacts";  // resolves to templates/contacts.html
    }
}
