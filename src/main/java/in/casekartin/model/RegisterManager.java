package in.casekartin.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table(value = "userdetails")
public class RegisterManager implements Serializable {
	public RegisterManager(String name, String userName, String password, String mobileNum, String email,
			String address) {
		super();
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.mobileNum = mobileNum;
		this.email = email;
		this.address = address;
	}

	public RegisterManager() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1463832976906555813L;

	private int id;

	private String name;

	@Column("user_name")
	private String userName;

	@Column("password_hash")
	private String password;

	@Column("mobile_number")
	private String mobileNum;

	@Column("created_date")
	private LocalDate createdDate = LocalDate.now();
	@Column("modified_date")
	private LocalDateTime modifiedDate = LocalDateTime.now();

	private String email;
	private String address;
	private String role;

}
