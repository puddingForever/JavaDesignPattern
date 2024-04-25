package behavioral.strategy;

import java.util.Collection;

//Strategy
public interface OrderPrinter {
	
	//it will print orders depending of algorithm 
	void print(Collection<Order> orders);
	
}
