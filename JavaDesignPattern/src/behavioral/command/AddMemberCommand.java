package behavioral.command;

//concrete command
public class AddMemberCommand implements Command{
	
	private String emailAddress;
	
	private String listName;
	
	private EWSService receiver;
	
	public AddMemberCommand(String email, String listName, EWSService service) {
		this.emailAddress = emailAddress;
		this.listName = listName;
		this.receiver = service;
	}
	
	@Override
	public void execute() {
		receiver.addMember(listName, emailAddress);
	}
		

}
