package in.casekartin.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
@Data
public class RegisterManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1463832976906555813L;

	private int id;
	private String name;	
	private String userName;
	private String password;
	private String mobileNum;
	private LocalDate createdDate;
	private LocalDateTime modifiedDate;
	private String email;
	private String address;
	private String role;
	

}
