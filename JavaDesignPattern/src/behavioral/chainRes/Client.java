package behavioral.chainRes;

import java.time.LocalDate;

import behavioral.chainRes.LeaveApplication.Type;

public class Client {

	public static void main(String[] args) {
	   LeaveApplication application = LeaveApplication.getBuilder().withType(Type.Sick)
			   						  .from(LocalDate.now()).to(LocalDate.of(2024,6,5))
			   						  .build();
	   
	   LeaveApprover approver = createChain();
	   approver.processLeaveApplication(application);
	   System.out.println(application);
	}
	
	private static LeaveApprover createChain() {
		Director director = new Director(null);
		Manager manager = new Manager(director);
		ProjectLead lead = new ProjectLead(manager);
		
		return lead;
	}

	
}
