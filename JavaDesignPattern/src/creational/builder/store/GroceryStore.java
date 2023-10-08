package creational.builder.store;

//entity class for groceryStore
public class GroceryStore {
	
	private String name;
	private String owner;
	private String city;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "GroceryStore [name=" + name + ", owner=" + owner + ", city=" + city + "]";
	}
	
	

}
