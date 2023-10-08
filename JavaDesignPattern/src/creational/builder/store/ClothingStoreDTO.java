package creational.builder.store;

public class ClothingStoreDTO implements StoreDTO{

	private String name;
	private String city;
	private String owner;
	
	public ClothingStoreDTO(String name,String city,String owner) {
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
		return "ClothingStoreDTO [name=" + name + ", city=" + city + ", owner=" + owner + "]";
	}

}
