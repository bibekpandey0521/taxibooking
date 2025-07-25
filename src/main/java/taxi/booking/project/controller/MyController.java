package taxi.booking.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import taxi.booking.project.model.BookingForm;
import taxi.booking.project.model.ContactForm;
import taxi.booking.project.service.BookingFormService;
import taxi.booking.project.service.ContactFormService;
import taxi.booking.project.service.ContactFormServiceImpl;

@Controller

public class MyController {
	private ContactFormService contactFormService;
	private BookingFormService bookingFormService;
	
	@Autowired
	public void setBookingFormService(BookingFormService bookingFormService) {
		this.bookingFormService = bookingFormService;
	}

	@Autowired
	 public void setContactFormService(ContactFormService contactFormService) {
		this.contactFormService = contactFormService;
	}

	@GetMapping({"/", "home", "welcome", "index"})
    public String welcomeView(HttpServletRequest req, Model m) {
    	String requestURI = req.getRequestURI();
    	System.out.println(requestURI);
    	m.addAttribute("mycurrentPage",requestURI);
    	m.addAttribute("bookingForm",new BookingForm());
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
    @GetMapping("/login")
    public String adminLoginView(HttpServletRequest request,Model model) 
    {
    	ServletContext servletContext = request.getServletContext();  
    	Object attribute = servletContext.getAttribute("logout");
    	if(attribute instanceof Boolean) 
    	{
    		model.addAttribute("logout",attribute);
    		servletContext.removeAttribute("logout");
    	}
    	return "adminlogin";
    }
    @PostMapping("contactform")
    public String contactForm(@Valid @ModelAttribute ContactForm contactForm,
    		BindingResult bindingResult,Model m , RedirectAttributes redirectAttributes) {
    	if(bindingResult.hasErrors()) {
    		m.addAttribute("bindingResult",bindingResult);
    		return "contacts";
    	}
    	
    	 ContactForm saveContactFormService = contactFormService.saveContactFormService(contactForm);
    	//System.out.println(contactForm);
    	if(saveContactFormService!=null) {
    		redirectAttributes.addFlashAttribute("message","Message Sent Successfully");
    	}else {
    		redirectAttributes.addFlashAttribute("message","Something Went Wrong");
    	}
    	return "redirect:/contacts";
    }
    
    @PostMapping("bookingform")
    public String bookingForm(@Valid @ModelAttribute BookingForm bookingForm,
    		BindingResult bindingResult,Model m , RedirectAttributes redirectAttributes) {
    	if(bindingResult.hasErrors()) {
    		m.addAttribute("bindingResult",bindingResult);
    		return "index";
    	}else if(bookingForm.getAdult()+bookingForm.getChildren()>4){
    		m.addAttribute("message","The total no of adult and children cannot exceed 4");
    		return "index";
    	}
   
    	//Service
    	BookingForm saveBookingFormService = bookingFormService.saveBookingFormService(bookingForm);
        if(saveBookingFormService!=null) {
        	redirectAttributes.addFlashAttribute("message","Message Sent Successfully");
        }else {
        	redirectAttributes.addFlashAttribute("message","Something went Wrong");
        }
    	return "redirect:/index";
    }
}
