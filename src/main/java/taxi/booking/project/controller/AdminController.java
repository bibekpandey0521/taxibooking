package taxi.booking.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import taxi.booking.project.model.BookingForm;
import taxi.booking.project.model.ContactForm;
import taxi.booking.project.model.ServiceForm;
import taxi.booking.project.service.AdminCredentialsService;
import taxi.booking.project.service.BookingFormService;
import taxi.booking.project.service.ContactFormService;
import taxi.booking.project.service.ServiceFormService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	private ContactFormService contactFormService;
	private AdminCredentialsService adminCredentialsService;
	private BookingFormService bookingFormService;
	private ServiceFormService serviceFormService;
	
	
	@Autowired
	public void setServiceFormService(ServiceFormService serviceFormService) {
		this.serviceFormService = serviceFormService;
	}

	@Autowired
	public void setAdminCredentialsService(AdminCredentialsService adminCredentialsService) {
		this.adminCredentialsService = adminCredentialsService;
	}

	@Autowired
	public void setBookingFormService(BookingFormService bookingFormService) {
		this.bookingFormService = bookingFormService;
	}

	@Autowired
	public void setContactFormService(ContactFormService contactFormService) {
		this.contactFormService = contactFormService;
	}

	@GetMapping("dashboard")
	public String adminDashboard() {
		return "admin/dashboard";
	}
	
	@GetMapping("readAllContacts")
	public String readAllContacts(Model model ) {
		model.addAttribute("allcontacts",contactFormService.readAllContactsService()
		);
		return "admin/readallcontacts";
	}
	@GetMapping("readAllBookings")
	public String readAllBookings(Model model) {
		List<BookingForm> allBookingsService = bookingFormService.readAllBookingsService(); 
		System.out.println(allBookingsService);
		
		
		model.addAttribute("allBookings",allBookingsService);
		return "admin/readallbookings";
	}
	@GetMapping("deleteContact/{id}")
	public String deleteContact(@PathVariable int id,RedirectAttributes redirectAttributes) 
	{
		contactFormService.deleteContactService(id);
		redirectAttributes.addFlashAttribute("message","Contact DELETED SUCCESSFULLY");
		return "redirect:/admin/readAllContacts";
	}
	
	@GetMapping("deleteBooking/{id}")
	public String deleteBooking(@PathVariable int id,RedirectAttributes redirectAttributes) 
	{
		bookingFormService.deleteBookingService(id);
		redirectAttributes.addFlashAttribute("message","Booking DELETED SUCCESSFULLY");
		return "redirect:/admin/readAllBookings";
	}
	
	@GetMapping("changeCredentials")
	public String changeCredentialsView(){
		return "admin/changecredentials";
	}
	
	@PostMapping("changeCredentials")
	public String changeCredentials(
	        @RequestParam("oldusername") String oldUsername,
	        @RequestParam("oldpassword") String oldPassword,
	        @RequestParam("newusername") String newUsername,
	        @RequestParam("newpassword") String newPassword,
	        RedirectAttributes redirectAttributes
	) {
	    String result = adminCredentialsService.checkAdminCredentials(oldUsername, oldPassword);

	    if ("SUCCESS".equals(result)) {
	        result = adminCredentialsService.updateAdminCredentials(newUsername, newPassword, oldUsername);
	        redirectAttributes.addFlashAttribute("message", result); // e.g., "Credentials updated successfully"
	    } else {
	        redirectAttributes.addFlashAttribute("message", "Wrong old credentials"); // force error message
	    }

	    return "redirect:/admin/dashboard";
	}
	@GetMapping("addService")
	public String addServiceView() 
	{
		return "admin/addservice";
	}
	
	@InitBinder
	public void stopBinding(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("image");
	}
	
	@PostMapping("addService")
	public String addService(@ModelAttribute ServiceForm serviceForm,
			@RequestParam("image") MultipartFile multipartFile,RedirectAttributes redirectAttributes ) {
		
		String originalFilename = multipartFile.getOriginalFilename();
		serviceForm.setImage(originalFilename);
		
		try {
			ServiceForm service =serviceFormService.addService(serviceForm, multipartFile);
			if(service!=null) {
				redirectAttributes.addFlashAttribute("msg","Service added successfully");
			}else {
				redirectAttributes.addFlashAttribute("msg","Something went wrong");
			}
		}catch(Exception e) {
			redirectAttributes.addFlashAttribute("msg","Something went worng");
		}
		
		return "redirect:/admin/addService";
		
	}
	
}

