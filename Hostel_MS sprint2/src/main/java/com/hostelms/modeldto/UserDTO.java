package com.hostelms.modeldto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.hostelms.model.Room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private int userId;
	
	@NotNull
	@Pattern(regexp = "[a-zA-Z]{5,20}", message = "\nUserName should NOT EXCEED LIMIT.")
	private String userName;

	@NotNull(message = "First Name not  null!!!")
	private String firstName;
	@NotNull(message = "Last Name not  null!!!")
	private String lastName;

	
	@NotNull
	@Pattern(regexp = "[0-9]{9,15}", message = "\nContact No should Atleast have 10 digits.")
	private String userContact;

	@NotNull
	@Pattern(regexp = "[a-zA-Z0-9_@#]{8,30}", message = "\nPassword can be AlphaNumeric and Atleast have 8 Characters.\n(Use atleast 1 Upper Case, 1 Lower Case, 1 Number)")
	private String userPassword;
	@Email
	private String userEmailAddress;
	private String userRole;
	private int userRent;
	private Room userRoom;
	
}
