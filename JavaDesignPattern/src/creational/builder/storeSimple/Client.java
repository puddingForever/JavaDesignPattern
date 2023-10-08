package creational.builder.storeSimple;

import creational.builder.store.ClothingStore;
import creational.builder.storeSimple.StoreDTO.StoreDTOBuilder;

public class Client { 
	
	public static void main(String[] args) {
		ClothingStore store = createStore();
		StoreDTO dto = directBuild(StoreDTO.getBuilder(),store);
		System.out.println(dto);
	}
	
	private static StoreDTO directBuild(StoreDTOBuilder builder,ClothingStore store) {
		
		return builder.withCity(store.getCity()).build();
	}
	
	public static ClothingStore createStore() {
		ClothingStore store = new ClothingStore();
		store.setName("H&M");
		store.setCity("Seoul");
		return store;
		
	}

}
