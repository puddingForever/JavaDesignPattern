package creational.factorymethod;

import creational.factorymethod.developer.Developer;
import creational.factorymethod.developer.JavaDeveloper;

public class JavaDevCreator extends DevCreator {

	@Override
	public Developer createDeveloper() {
		// TODO Auto-generated method stub
		return new JavaDeveloper();
	}
	

}
