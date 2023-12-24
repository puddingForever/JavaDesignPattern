package structural.adaptor;

/**
 * object adapter
 */
public class EmployeeObjectAdapter implements Customer {

	private Employee adoptee;
	
	public EmployeeObjectAdapter(Employee adoptee) {
		this.adoptee = adoptee;
	}
	
	@Override
	public String getName() {
		return adoptee.getFullName();
	}

	@Override
	public String getDesignation() {
		return adoptee.getJobTitle();
	}

	@Override
	public String getAddress() {
		return adoptee.getOfficeLocation();
	}
		
}
