package creational.builder.storeSimple;


//결과물 클래스
public class StoreDTO {

	private String name;
	
	private String city;
	
	private String owner;

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	private void setCity(String city) {
		this.city = city;
	}

	public String getOwner() {
		return owner;
	}

	private void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "StoreDTO [name=" + name + ", city=" + city + ", owner=" + owner + "]";
	}
	
	//builder instance 가져오기 
	public static StoreDTOBuilder getBuilder() {
		return new StoreDTOBuilder();
	}
	
	//Builder inner class
	public static class StoreDTOBuilder{
		
		private String name;
		
		private String city;
		
		private String owner;
		
		private StoreDTO storeDTO;
		
		
		public StoreDTOBuilder withName(String name) {
			this.name = name;
			return this;
		}
		
		public StoreDTOBuilder withCity(String city) {
			this.city = city;
			return this;
		}
		
		public StoreDTOBuilder withOwner(String owner) {
			this.owner = owner;
			return this;
		}
		
		//빌더 메소드 (구성요소 조립)
		public StoreDTO build() {
			this.storeDTO = new StoreDTO();
			storeDTO.setName(name);
			storeDTO.setCity(city);
			storeDTO.setOwner(owner);
			return this.storeDTO;
			
		}
		
		
		
	}
	
	
}
