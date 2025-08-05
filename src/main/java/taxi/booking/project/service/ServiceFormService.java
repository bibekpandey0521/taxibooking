package taxi.booking.project.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import taxi.booking.project.model.ServiceForm;

public interface ServiceFormService {

	public ServiceForm addService(ServiceForm serviceForm,MultipartFile multipartFile) throws Exception;
	
	public List<ServiceForm> readAllServices();
}
