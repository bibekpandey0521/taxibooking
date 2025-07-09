package taxi.booking.project.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ContactForm {
	
	private int id;
	@NotEmpty(message = "Name cannot be empty")
	@Size(min = 2,max=30,message = "Invalid Name Size")
	private String name;
	
	@NotEmpty(message = "Email cannot be empty")
	@Size(min = 5,max=50,message = "Invalid Email Size")
	private String email;
	
	@NotNull(message = "Phone No cant be Empty")
	@Min(value = 1000000000, message = "Phone No must be atleast 10 digits")
	@Max(value = 9999999999L, message = "Phone No must be atleast 10 digits")
	private Long phone;
	
	@NotEmpty(message = "Messaage cannot be empty")
	@Size(min = 3,max=500,message = "Invalid Message Size")
	private String message;
	
}
