package creational.builder.store;

public class GroceryStoreDTO implements StoreDTO{

	private String name;
	private String city;
	private String owner;
	
	public GroceryStoreDTO(String name,String city,String owner) {
		this.name = name;
		this.city = city;
		this.owner = owner;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getCity() {
		return this.city;
	}

	@Override
	public String getOwner() {
		return this.owner;
	}

	@Override
	public String toString() {
		return "GroceryStoreDTO [name=" + name + ", city=" + city + ", owner=" + owner + "]";
	}

	
	
}
