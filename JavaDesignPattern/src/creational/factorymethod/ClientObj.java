package creational.factorymethod;

import creational.factorymethod.developer.Developer;

public class ClientObj {

	public static void main(String[] args) {
		
		printDev(new JavaDevCreator());
		printDev(new PhpDevCreator());
		
	}
	
	public static void printDev(DevCreator creator) {
		
		Developer dev = creator.getDeveloper();
		System.out.println(dev);
	}
}
