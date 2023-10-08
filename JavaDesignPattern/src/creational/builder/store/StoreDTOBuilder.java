package creational.builder.store;

public interface StoreDTOBuilder {

	StoreDTOBuilder withName(String name);
	
	StoreDTOBuilder withCity(String city);
	
	StoreDTOBuilder withOwner(String owner);
	
	//최종 결합해주는 메소드 
	StoreDTO build();
	
	//이미 만들어진 객체를 부르는 메소드 
	StoreDTO getStoreDTO();
	
}
