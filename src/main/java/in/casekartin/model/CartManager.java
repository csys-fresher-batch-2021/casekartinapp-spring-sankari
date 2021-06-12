package in.casekartin.model;
import java.io.Serializable;

import lombok.Data;
@Data
public class CartManager implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -151346536930669831L;
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getMobileBrand() {
		return mobileBrand;
	}
	public void setMobileBrand(String mobileBrand) {
		this.mobileBrand = mobileBrand;
	}
	public String getMobileModel() {
		return mobileModel;
	}
	public void setMobileModel(String mobileModel) {
		this.mobileModel = mobileModel;
	}
	public int getNoOfCases() {
		return noOfCases;
	}
	public void setNoOfCases(int noOfCases) {
		this.noOfCases = noOfCases;
	}
	public String getFriendsName() {
		return friendsName;
	}
	public void setFriendsName(String friendsName) {
		this.friendsName = friendsName;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	String caseName;
	String mobileBrand;
	String mobileModel;
	Float price;
	String friendsName;
	int noOfCases;
	int orderId;

	

}
