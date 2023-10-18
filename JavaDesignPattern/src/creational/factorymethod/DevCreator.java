package creational.factorymethod;

import creational.factorymethod.developer.Developer;

public abstract class DevCreator {
	
	public Developer getDeveloper() {
		Developer dev = createDeveloper();
		dev.improveSkill();
		dev.isOOP();
		
		return dev;
	}
	
	//factory method 
	protected abstract Developer createDeveloper();
}
