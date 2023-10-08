package creational.builder.store;

//concrete builder for GroceryStore
public class GroceryStoreDTOBuilder implements StoreDTOBuilder{

	private String name;
	private String city;
	private String owner;
	
	private GroceryStoreDTO dto;
	
	@Override
	public StoreDTOBuilder withName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public StoreDTOBuilder withCity(String city) {
		this.city = city;
		return this;
	}

	@Override
	public StoreDTOBuilder withOwner(String owner) {
		this.owner = owner;
		return this;
	}

	//빌드하기
	@Override
	public StoreDTO build() {
		this.dto = new GroceryStoreDTO(name, city, owner);
		return dto;
	}

	//이미 만들어진 객체 리턴하기 
	@Override
	public StoreDTO getStoreDTO() {
		return dto;
	}

}
