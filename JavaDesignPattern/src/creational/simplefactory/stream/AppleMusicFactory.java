package creational.simplefactory.stream;

public class AppleMusicFactory {
	
	public static Music playMusic(String mood) {
		
		switch(mood) {
		case "English" :
			return new PopMusic();
		case "Korean" : 
			return new KpopMusic();
		default : 
			throw new IllegalArgumentException("음악이 없습니다");
		}
		
	}

}
