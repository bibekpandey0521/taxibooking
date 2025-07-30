package taxi.booking.project.service;

import java.util.List;

import jakarta.validation.Valid;
import taxi.booking.project.model.BookingForm;
import taxi.booking.project.model.ContactForm;

public interface BookingFormService {
	
	public BookingForm saveBookingFormService(BookingForm bookingForm);
	
	public List<BookingForm> readAllBookingsService();
	public void deleteBookingService(int id);
}
