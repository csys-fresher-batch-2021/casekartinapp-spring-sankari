package in.casekartin.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class CaseManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6324952043091827871L;

	private String caseType;
	private Float cost;
	private String status;

}
