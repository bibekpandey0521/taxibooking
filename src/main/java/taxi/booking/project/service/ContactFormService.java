package taxi.booking.project.service;

import java.util.List;

import taxi.booking.project.model.ContactForm;

public interface ContactFormService {
	
	public ContactForm saveContactFormService(ContactForm contactForm);
	public List<ContactForm> readAllContactsService();
	public void deleteContactService(int id);
	
}
