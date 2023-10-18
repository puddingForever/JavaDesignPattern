package creational.factorymethod;

import creational.factorymethod.developer.Developer;
import creational.factorymethod.developer.PhpDeveloer;

public class PhpDevCreator extends DevCreator{

	@Override
	public Developer createDeveloper() {
		return new PhpDeveloer();
	}
	
	

}
