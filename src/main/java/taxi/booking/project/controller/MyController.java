package taxi.booking.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import taxi.booking.project.model.ContactForm;

@Controller
public class MyController {

    @GetMapping({"/", "home", "welcome", "index"})
    public String welcomeView(HttpServletRequest req, Model m) {
    	String requestURI = req.getRequestURI();
    	System.out.println(requestURI);
    	m.addAttribute("mycurrentPage",requestURI);
        return "index";  // resolves to templates/index.html
    }

    @GetMapping("about")
    public String aboutView(HttpServletRequest req,Model m ) {
    	String requestURI = req.getRequestURI();
    	m.addAttribute("mycurrentPage",requestURI);
        return "about";  // resolves to templates/about.html
    }

    @GetMapping("cars")
    public String carsView(HttpServletRequest req,Model m ) {
    	String requestURI = req.getRequestURI();
    	m.addAttribute("mycurrentPage",requestURI);
        return "cars";  // resolves to templates/cars.html
    }

    @GetMapping("services")
    public String servicesView(HttpServletRequest req,Model m) {
    	String requestURI = req.getRequestURI();
    	m.addAttribute("mycurrentPage",requestURI);
        return "services";  // resolves to templates/services.html
    }

    @GetMapping("contacts")
    public String contactsView(HttpServletRequest req,Model m) {
    	String requestURI = req.getRequestURI();
    	m.addAttribute("mycurrentPage",requestURI);
    	m.addAttribute("contactForm",new ContactForm());
        return "contacts";  // resolves to templates/contacts.html
    }
    
    @PostMapping("contactform")
    public String contactForm(@Valid @ModelAttribute ContactForm contactForm,
    		BindingResult bindingResult,Model m) {
    	if(bindingResult.hasErrors()) {
    		m.addAttribute("bindingResult",bindingResult);
    		return "contacts";
    	}
    	
    	System.out.println(contactForm);
    	return "redirect:/contacts";
    }
}
