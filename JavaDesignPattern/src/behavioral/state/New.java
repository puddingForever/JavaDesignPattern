package behavioral.state;

//state value 
public class New implements OrderState{

	@Override
	public double handleCancellation() {
		System.out.println("It's a new order . no processing done");
		return 0;
	}

}
