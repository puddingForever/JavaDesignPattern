# SimpleFactory Pattern 

## 한마디 정리 
+ 객체 생성 로직을 추상화(클라이언트 객체는 알수 없음)하고 간단한 방식을 이용해 객체를 생성함
+ 하나의 클래스에서 다양한 객체를 생성할 수 있음 . 코드 중복을 방지하고 유지보수성을 향상시킴
+ 객체의 캡슐화 

## UML 
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/0a282609-ebbb-46b2-b704-a8f62ce09e8e)

1. Product : 인터페이스 혹은 추상 클래스 . 사용하기 위해서는 ProductA나 ProductB의 실제 객체가 필요함
2. SimpleFactory : 인스턴스를 만들어주는 객체. 조건에 따른 인스턴스를 만들어주는 static 메소드가 들어있음 <br> 인자값에 따라 어떤 객체를(ProductA or ProductB) 인스턴스화할지 결정함

## Simple Factory 구현방법 
> Simple Factory의 메소드를 사용하기위한 하나의 객체를 만든다 <br>
+ 원하는 인스턴스를 얻기 위한 파라메터가 있는 static 메소드를 만든다 <br>
  &nbsp;
  + 파라메터가 있어야하는 이유? -> 어떤 인스턴스로 만들지 알아야하기 때문에 <br>
  + 메소드를 static으로 설정하는 이유? -> 간편한 접근 , 인스턴스의 의존성 최소화 (클라이언트 객체가 팩토리 인스턴스에 의존하지 않음 ) <br>

## 예시 
+ Music 이라는 추상클래스 ( abstract이라 스스로 인스턴스화 되지 않기 때문에 자식클래스 필요)
```java

public abstract class Music {
	
	private Long id;
	
	private String trend;
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTrend() {
		return this.trend;
	}
	
	public void setTrend(String trend) {
		this.trend = trend;
	}
}

```

+ Music 추상클래스를 상속받는 KpopMusic과 PopMusic
  > KpopMusic
```java
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

```

> PopMusic
```java

public class PopMusic extends Music{

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
```

+ 심플팩토리 패턴을 사용해보자 <br>
&nbsp;
+ switch문을 이용하여 mood의 값에 따라 KpopMusic이 생성될 수도 있고 , PopMusic이 생성될 수 있음
  
  ```java

  public class AppleMusicFactory{
      public static Music playMusic(String mood){
        switch(mood){
          case:"korean" :
             return new KpopMusic();
          case : "English" :
              return new PopMusic();
          default :
            throw new IllegalArgumentsException("음악이 없습니다");
        }
      }
  }

  ```
+ **객체의 캡슐화** : 하나의 캡슐안(AppleMusicFactor)에 연관되있는 객체들을 만들어 외부로 부터 보호함.<br>
(클라언트 코드는 AppleMusicFactory클래스의 playMusic 메소드를 사용하여 Music객체를 생성하고, 내부적으로 어떤 종류의 객체가 생성되는지 알필요가 없음. 이로서 객체 생성 로직이 숨겨져 있고, 클라이언트 코드는 단순히 팩토리 메소드만을 호출하여 원하는 Music 객체를 얻을 수 있음) <br>

> 클라이언트 객체 <br>
+ 객체 생성 로직을 알필요 없이 Korean이라는 조건만을 주니 KpopMusic() 인스턴스가 생성됨  <br>
```java
public class Client {
	public static void main(String[] args) {
		Music music = AppleMusicFactory.playMusic("Korean");
		System.out.println(music);
	}
}

```
### SimpleFactory 고려사항 
+ SimpleFactory는 다른 메소드와 섞어 쓰지 않는 것이 좋음. 하나의 SimpleFactory객체 안에 하나의 static메소드를 사용해서 인스턴스를 생성하도록 하는게 일반적 ( 만약 AppleMusicFactory안에 simpleFactory이외의 메소드를 왕창 집어넣으면 , 객체의 재활용성이 저하 ) <br>
+ Simple Factory랑 Factory Method랑은 다른 것!


### 최종 한마디 
+ Simple Factory는 단순한 조건만을 가지고 인스턴스를 생성해주는 패턴이다. 하지만 인스턴스를 생성하기위한 조건이 복잡한 경우라면 Factory Method를 사용해야함 ~
