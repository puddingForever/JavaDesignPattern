package behavioral.chainRes;

//abstract handler
public interface LeaveApprover {

	void processLeaveApplication(LeaveApplication application);
	
	String getApproverRole();
	
}
