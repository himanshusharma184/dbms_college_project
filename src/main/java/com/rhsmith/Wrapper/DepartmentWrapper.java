package com.rhsmith.Wrapper;

import java.io.Serializable;

public class DepartmentWrapper implements Serializable{
	
    /**
	 * @author himanshusharma
	 */
	private static final long serialVersionUID = 6587691694485254831L;
	private String departmentID;
    private String departmentName;
    private String departmentAddress;
    private String country;
    private String departmentPhone;
    private String departmentSupervisor;
	public String getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentAddress() {
		return departmentAddress;
	}
	public void setDepartmentAddress(String departmentAddress) {
		this.departmentAddress = departmentAddress;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDepartmentPhone() {
		return departmentPhone;
	}
	public void setDepartmentPhone(String departmentPhone) {
		this.departmentPhone = departmentPhone;
	}
	public String getDepartmentSupervisor() {
		return departmentSupervisor;
	}
	public void setDepartmentSupervisor(String departmentSupervisor) {
		this.departmentSupervisor = departmentSupervisor;
	}


}
