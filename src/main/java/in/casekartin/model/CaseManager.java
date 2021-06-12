package in.casekartin.model;

import java.io.Serializable;

//create a service class
public class CaseManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6324952043091827871L;

	public String getCaseType() {
		return caseType;
	}

	public Float getCost() {
		return cost;
	}

	private String caseType;
	private Float cost;
	private String status;

	public String getStatus() {
		return status;
	}

	// Create constructor for service class
	public CaseManager(String caseType, Float cost,String status) {
		super();
		this.caseType = caseType;
		this.cost = cost;
		this.status=status;
	}

}
