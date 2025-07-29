package taxi.booking.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import taxi.booking.project.model.ContactForm;
import taxi.booking.project.service.AdminCredentialsService;
import taxi.booking.project.service.ContactFormService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	private ContactFormService contactFormService;
	private AdminCredentialsService adminCredentialsService;
	
	@Autowired
	public void setAdminCredentialsService(AdminCredentialsService adminCredentialsService) {
		this.adminCredentialsService = adminCredentialsService;
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
	
	@GetMapping("deleteContact/{id}")
	public String deleteContact(@PathVariable int id,RedirectAttributes redirectAttributes) 
	{
		contactFormService.deleteContactService(id);
		redirectAttributes.addFlashAttribute("message","Contact DELETED SUCCESSFULLY");
		return "redirect:/admin/readAllContacts";
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

}
