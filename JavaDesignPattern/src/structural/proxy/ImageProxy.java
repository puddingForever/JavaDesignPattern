package structural.proxy;


//프록시 클래스 
public class ImageProxy implements Image {

	//대상객체의 참조값을 가지고 있음 
	private BitmapImage image;
	
	private String name;
	
	private Point2D location;
	
	public ImageProxy(String name) {
		this.name = name;
	}
	
	
	@Override
	public void setLocation(Point2D point2d) {
		if(image != null) { // 이미지가 있다면 대상객체에 전달 
			image.setLocation(point2d); 
		}else { //이미지가 없다면 프록시의 필드에 저장 
			location = point2d;
		}
	}

	@Override
	public Point2D getLocation() {
		
		if(image != null) {//이미지가 있다면 대상객체의 location값 전달
			return image.getLocation();
		}
		
		//이미지가 없다면 프록시의 필드값 전달 
		return location;
	}

	//사진인화 메소드 
	//프록시 객체는 할 수 없음.오로지 대상객체만 사용가능 
	@Override
	public void render() {
		if(image == null) {
			image = new BitmapImage(name);
			if(location != null) {
				image.setLocation(location);
			}
		}
		image.render();
	}
	
}
