package creational.simplefactory.stream;

public class Client {
	
	public static void main(String[] args) {
		Music music = AppleMusicFactory.playMusic("Korean");
		System.out.println(music);
	}

}
