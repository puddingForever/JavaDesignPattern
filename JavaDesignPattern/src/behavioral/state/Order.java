package behavioral.state;

//Context class
public class Order {

	private OrderState currentState;
	
	public Order() {
		currentState = new New();
	}
	
	public double cancel() {
		double charges = currentState.handleCancellation();
		currentState = new Cancelled();
		return charges;
	}
   
	//change transition from one state to other state 
	public void paymentSuccessful() {
		currentState = new Paid();
	}
	
	public void dispatched() {
		currentState = new InTransit();
	}
	
	public void delivered() {
		currentState = new Delivered();
	}
	
}
