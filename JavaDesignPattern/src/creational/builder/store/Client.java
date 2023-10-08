package creational.builder.store;

public class Client {
	
	public static void main(String[] args) {
		
		ClothingStore clothingStore = createClothingStore();
		StoreDTOBuilder builder = new ClothingStoreDTOBuilder();
		StoreDTO dto = clothingStoreBuild(builder,clothingStore);
		
		System.out.println(dto);
		
		GroceryStore groceryStore = createGroceryStore();
		StoreDTOBuilder builder2 = new GroceryStoreDTOBuilder();
		StoreDTO dto2 = groceryStoreBuild(builder2,groceryStore);
		
		System.out.println(dto2);
		
	}

	//Director 
	private static StoreDTO clothingStoreBuild(StoreDTOBuilder builder,ClothingStore store) {
		return builder.withName(store.getName())
				.withCity(store.getCity())
				.build();
	}
	
	private static StoreDTO groceryStoreBuild(StoreDTOBuilder builder,GroceryStore store) {
		return builder.withOwner(store.getName())
				.build();
	}
	
	
	//ClothingStore생성 
	public static ClothingStore createClothingStore() {
		ClothingStore store = new ClothingStore();
		store.setCity("Seoul");
		store.setName("H&M");
		store.setOwner("Erling Persson");
		return store;
	}
	
	
	//GroceryStore생성
	public static GroceryStore createGroceryStore() {
		GroceryStore store = new GroceryStore();
		store.setCity("Pusan");
		store.setName("코스트코");
		store.setOwner("W. Craig Jelinek");
		return store;
	}
	
	
}
