package structural.adaptor;

/**
 *클래스 어댑터 (class Adapter)
 */
public class EmployeeClassAdapter extends Employee implements Customer{

	
	@Override
	public String getName() {
		return this.getFullName();
	}

	@Override
	public String getDesignation() {
		return this.getJobTitle();
	}

	@Override
	public String getAddress() {
		return this.getOfficeLocation();
	}
	
}
