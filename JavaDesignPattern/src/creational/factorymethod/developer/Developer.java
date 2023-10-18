package creational.factorymethod.developer;

public abstract class Developer {

	public abstract String language();
	
	
	//디폴트 메소드 
	public String improveSkill() {
		return "열공";
	}
	
	public boolean isOOP() {
		return true;
	}
}
