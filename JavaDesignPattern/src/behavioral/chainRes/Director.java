package behavioral.chainRes;

//A concrete handler
public class Director extends Employee {

	public Director(LeaveApprover nextApprover) {
		super("Director", nextApprover);
	}
	
	@Override
	protected boolean processRequest(LeaveApplication application) {
		if(application.getType() == application.getType().PTO) {
			application.approve(getApproverRole());
			return true;
		}
		return false;
	}
	
}
