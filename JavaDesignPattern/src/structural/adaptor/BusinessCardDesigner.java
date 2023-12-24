package structural.adaptor;

/**
 * Customer 인터페이스가 필요한 클라이언트 코드 
 * 
 */
public class BusinessCardDesigner {
	public String designCard(Customer customer) {
		String card = "";
		card += customer.getName();
		card += "\n" + customer.getDesignation();
		card += "\n" + customer.getAddress();
		return card;
	}
}
