package structural.adaptor;

/**
 * 클라이언트 코드가 필요한 메소드들 
 */
public class Employee {

	private String fullName;
	
	private String jobTitle;
	
	private String officeLocation;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getOfficeLocation() {
		return officeLocation;
	}

	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}
	
}
