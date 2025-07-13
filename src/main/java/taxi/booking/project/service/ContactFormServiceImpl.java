package taxi.booking.project.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taxi.booking.project.dao.ContactFormCrud;
import taxi.booking.project.model.ContactForm;

@Service
public class ContactFormServiceImpl implements ContactFormService{

	private ContactFormCrud contactFormCrud;
	
	@Autowired
	public void setContactFormCrud(ContactFormCrud contactFormCrud) {
		this.contactFormCrud = contactFormCrud;
	}


	@Override
	public ContactForm saveContactFormService(ContactForm contactForm) {
		return contactFormCrud.save(contactForm);
	}

}
