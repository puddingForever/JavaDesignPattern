package creational.factorymethod.developer;

public abstract class Developer {

	public abstract String language();

	public String improveSkill() {
		return "열공";
	}	
	public boolean isOOP() {
		return true;
	}
}
