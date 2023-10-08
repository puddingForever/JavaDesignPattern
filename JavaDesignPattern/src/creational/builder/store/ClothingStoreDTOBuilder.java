package creational.builder.store;


//concrete Builder for ClothingStore
public class ClothingStoreDTOBuilder implements StoreDTOBuilder{

	private String name;
	private String city;
	private String owner;
	
	private ClothingStoreDTO dto;
	
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

	//최종객체 반환 
	@Override
	public StoreDTO build() {
		this.dto = new ClothingStoreDTO(name,city,owner);
		return dto;
	}

	@Override
	public StoreDTO getStoreDTO() {
		return dto;
	}

}
