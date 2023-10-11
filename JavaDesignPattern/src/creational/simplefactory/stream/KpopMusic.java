package creational.simplefactory.stream;

public class KpopMusic extends Music {
	
	private String country;
	private String topStar;
	
	public String getCountry() {
		return this.country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getTopStar() {
		return this.topStar;
	}
	
	public void setTopStar(String topStar) {
		this.topStar = topStar;
	}
}
