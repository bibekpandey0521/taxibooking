package taxi.booking.project.service;

public interface AdminCredentialsService {
	public String checkAdminCredentials(String oldusername,String oldpassword);

	public String updateAdminCredentials(String newusername,String newpassword,String oldpassword);
}
