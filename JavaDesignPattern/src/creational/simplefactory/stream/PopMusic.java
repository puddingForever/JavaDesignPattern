package creational.simplefactory.stream;

public class PopMusic extends Music{

	private String country;
	private String topStar;
	
	private String getCountry() {
		return this.country;
	}
	
	private void setCountry(String country) {
		this.country = country;
	}
	
	private String getTopStar() {
		return this.topStar;
	}
	
	private void setTopStar(String topStar) {
		this.topStar = topStar;
	}
}
