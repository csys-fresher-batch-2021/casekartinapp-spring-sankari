package in.casekartin.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserDTO {

	@NotNull
	private String name;

	@NotNull
	private String userName;

	@NotNull
	private String password;

	@NotNull
	private String mobileNum;

	@NotNull
	private String email;

	@NotNull
	private String address;

	private String role;
}
