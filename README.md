# JavaDesignPattern

This is a repository for practicing design patterns that web developers should be familiar with. <br> 

## 생성패턴(Creational Design Patterns) 
> 생성패턴(Creational Design Pattern)이란 객체의 생성과 관련된 패턴으로, **객체의 생성절차를 추상화(flexibility and reuse of existing code)** 하는 패턴이다.
- [Builder Pattern](#BuilderPattern) 
- [Simple Factory Pattern](#SimpleFactoryPattern) 
- [Factory Pattern](#FactoryPattern)
- [Prototype Pattern](#PrototypePattern)
- [ObjectPool Pattern](#ObjectPool)
- [Singleton Pattern](#Singleton)

## 구조패턴(Structural Design Patterns) 
> 구조패턴(Structural Design Pattern)이란  클래스나 객체를 조합해 더 큰 구조를 만드는 패턴이다. 예를 들어 서로 다른 인터페이스를 지닌 2개의 객체를 묶어 단일 인터페이스를 제공하거나 객체들을 서로 묶어 새로운 기능을 제공하는 패턴이다.
- [Adapter Pattern](#AdapterPattern)
- [Bridge Pattern](#BridgePattern)
- [Decorator Pattern](#DecoratorPattern) 
- [Composite Pattern](#CompositePattern)
- [Facade Pattern](#FacadePattern)
- [Flyweight Pattern](#FlyweightPattern)
- [Proxy Pattern](#ProxyPattern) 

## 행위패턴(Behavioral Design Patterns) 
> 행위패턴(Behavioral Design Pattern)이란 클래스나 객체들이 서로 상호작용하는 방법이나 책임 분배 방법을 정의하는 패턴이다.
- [Chain of Responsibility Pattern](#ChainOfResponsibility)
- [Command Pattern](#CommandPattern)
- [Interpreter Pattern](#Interpreter)
- [Mediator Pattern](#Mediator)
- [Iterator Pattern](#Iterator)
- [Memento Pattern](#Memento)
- [Observer Pattern](#Observer)
- [State Pattern](#State)
- [Strategy Pattern](#Strategy)
- [Template Method Pattern](#TemplateMethod)
- [VIsitor Pattern](#Visitor)

<hr>

# 생성패턴

# BuilderPattern


### 한마디 정리 
- 객체 생성시 프로세스를 여러개로 나누어 객체 구성요소간의 느슨한 결합(loose coupling)을 촉진함
  
### 빌더패턴을 공부하기 전에 ..  

> 아래의 Book클라스는 파라메터를 받는 생성자 하나만 있음 <br>
> Book클래스를 이용해 인스턴스를 생성할 때의 불편한 점은 무엇이 있을까요

```java
class Book{
	public Book(double price,int weight,String author,String clientName){
		//초기화
	}
}
```

> 답변
1. Book클라스는 인스턴스를 생성할때 반드시 생성자에 넣어줄 인자값을 다 셋팅해주어야함 <br>
왜냐하면 파라메터가 있는 생성자를 만든다면 기본생성자는 자동으로 생성되지 않기 때문임 <br>
new Book(),new Book("hi")로 인스턴스를 생성하는 것은 불가하며 반드시 new Book(double타입 인자값,int타입 인자값,String타입 인자값,String타입 인자값)으로만 인스턴스를 생성해야하는 불편함이 있음

3. Book클라스의 생성자를 제대로 확인하지 않는다면, **author와 clientName의 인자값을 서로 다르게 셋팅** 해줄 수 있는 위험이 있음. <br>
자바에서는 생성자의 이름이 아닌 타입을 검사하기 떄문에 String author와 String clientName의 값이 뒤바뀌어도 컴파일러 에러가 발생하지 않음. <br>


> 두번째 예시 <br>
> 아래의 Me라는 객체의 인스턴스를 생성하기 위해서는 먼저 Nation이라는 객체와 Bank라는 객체를 생성한 후 , Bank라는 객체를 감싸고 있는 collection 객체도 생성해야만 함. <br>
> Me라는 객체를 생성하기 위해 구성요소와 그 값들을 모두 만든 후, 값까지 처음부터 다 셋팅해야하는 번거로움이 있음(null로 넣을 수도 있겠지만...일단 이건 생각하지 맙시디.. ^^) 

```java
	class Me{
		public Me(Nation nation,List<Bank> banks,String name){
			//초기화 
		}
	}
```

> 이 모든 과정이 불편하다고 느꼈다면 ..빌더패턴을 사용하면 간단하게 해결할 수 있음 .

### 빌더(Builder)란 도대체 무엇인가?
- 인스턴스 생성시 생성자의 형태에 따른 **생성순서의 규칙을 단순화** 해줌 
- 복잡한 객체를 생성하는 클래스를 분리하여 느슨한 결합(loose coupling)을 위해 추상개념을 사용함

#### 빌더패턴 구현순서  

- ![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/6f862b88-f30b-4304-9946-ba67a6bed525)

1.Product (결과물) 
- 내가 만들고자하는 최종 객체
 
2.Builder 
- Product(결과물)을 만들기위한 **구성요소들을 제공해주는 인터페이스**
- 인스턴스를 생성시 필요 값만 개별적으로 셋팅할 수 있도록 해줌!
- (위의 Book클래스를 보면 weight,price,author,clientName 모두 셋팅해줘야 객체가 생성되는데, 빌더를 이용하면 price만 넣어도 인스턴스가 생성됨)

3.ConcreteBuilder 
- Builder인터페이스의 구현클래스 
- 구성요소들을 이용해서 최종 결과물을 만드는 곳
- 객체를 생성하기 위한 절차를 관리(최종 객체를 만들기위해 필요한 정보를 추적하고 관리하는 역할을 함)

4.Director 
- builder를 이용하여 최종 객체를 생성하는 부분


### 빌더패턴을 이용하여 식료품점(GroceryStoreDTO)과 옷가게(ClothingStoreDTO)들을 만들어보자

- UML 

![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/47029eb1-2fd7-4ade-bb51-bebc6dc6631c)


- Product이 표시된 곳은 최종결과물이다. 나는 ClothingStoreDTO와 GroceryStoreDTO를 만들 것임
- loose coupling을 위해 최종DTO들은 인터페이스를 구현해서 만들도록 설계해봄 (abstract이라고 표시된 곳은 인터페이스를 사용예정)
- StoreDTOBuilder는 인터페이스로 되어있으며, 가게 구성에 필요한 부분들을 만들기 위해 간단한 메소드들이 작성되있음
- Client는 메인클라스 ! Director(최종객체를 생성하는 부분)의 역할을 한다. 

### 빌더패턴을 실제로 써보자
**이 예시는 살짝 복잡한데, 뒤에서 더 쉬운 방법으로 빌더패턴을 사용하는 것을 알려주겠음**

1. 먼저 내가 만들려고 하는 최종결과물 DTO를 만들어줌 
```java

public class ClothingStoreDTO implements StoreDTO{

	private String name;
	private String city;
	private String owner;
	
	public ClothingStoreDTO(String name,String city,String owner) {
		this.name = name;
		this.city = city;
		this.owner = owner;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getCity() {
		return this.city;
	}

	@Override
	public String getOwner() {
		return this.owner;
	}

	@Override
	public String toString() {
		return "ClothingStoreDTO [name=" + name + ", city=" + city + ", owner=" + owner + "]";
	}

}

```

- 옷가게DTO임. 가게이름(name),가게주인(owner),가게위치(city)가 들어있는 간단한 POJO(Plain Old Java Object)클래스
- 최종 DTO의 경우 setter를 빼고 불변 클래스로 만들어주어야함
- (빌더패턴을 사용하는 경우 코드의 안전성떄문에 객체는 불변으로 만들어주어야함
- 예를 들어 Store의 이름을 H&M으로 인스턴스를 생성했다면 Nike로 변경할수는 없음. 새로운 인스턴스를 생성해야함
- 이름이 H&M인 Store의 City를 서울로 설정하고 싶어도,추가가 아니라 새로운 인스턴스를 만들어서 생성하게 되는 것임 )  
   
2. 빌더 인터페이스를 만들어보자

```java

	public interface StoreDTOBuilder{
		StoreDTOBuilder withName(String name);

		StoreDTOBuilder withCity(String city);

		StoreDTOBuilder withOwner(String owner);

		//부품들을 조립해서 최종결과물 반환해주는 메소드
		StoreDTO build();

		//(선택사항)이미 만들어진 완성품을 부르는 메소드
		StoreDTO getStoreDTO();
	}
```

- 인터페이스에 정의된 메소드는 메소드체이닝을 위하여 자기자신을 리턴하도록 설계함
-  StoreDTO는 최종결과물(ClothingStoreDTO)를 구현해주는 인터페이스임 (loose coupling을 위해서 이렇게 씀)
  
3. Concrete Builder를 만들어보자(빌더인터페이스를 구현한 클래스)
```java
public class ClothingStoreDTOBuilder implements StoreDTOBuilder{

	private String name;
	private String city;
	private String owner;
	
	private ClothingStoreDTO dto;
	
	@Override
	public StoreDTOBuilder withName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public StoreDTOBuilder withCity(String city) {
		this.city = city;
		return this;
	}

	@Override
	public StoreDTOBuilder withOwner(String owner) {
		this.owner = owner;
		return this;
	}

	//부품들을 조립해서 최종결과물 반환하는 메소드
	@Override
	public StoreDTO build() {
		this.dto = new ClothingStoreDTO(name,city,owner);
		return dto;
	}

	//(선택사항)완성품을 반환하는 메소드
	@Override
	public StoreDTO getStoreDTO() {
		return dto;
	}

}
```
- 빌더패턴에서는 concrete builder부분이 가장 중요하다 생각함 . 왜냐하면 이 부분에서 객체 구성요소간의 loose coupling이 이루어지기 때문임
- 이렇게 필드명을 가지고 있으며
```java
        private String name;
	private String city;
	private String owner;
```
- 이렇게 필드값을 셋팅해주며
```java
	@Override
	public StoreDTOBuilder withName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public StoreDTOBuilder withCity(String city) {
		this.city = city;
		return this;
	}

	@Override
	public StoreDTOBuilder withOwner(String owner) {
		this.owner = owner;
		return this;
	}

```
- 최종적으로 부품을들 합쳐서 반환해주는 메소드도 있음
```java
	@Override
	public StoreDTO build() {
		this.dto = new ClothingStoreDTO(name,city,owner);
		return dto;
	}
```
- 또 하나로 눈여겨봐야 할 곳은 메소드 체인(method chain)임
- 필드값을 셋팅할때 자기 자신을 반환(return this)하기 떄문에 셋팅시 객체를 생성하지 않고 바로 메소드 체이닝을 이용해서 해당 객체의 다른 메소드를 부를 수 있음
  <br>
4. Director클래스를 만들자 (빌더 패턴을 부르는 메인클래스)

```java

public class Client {
	
	public static void main(String[] args) {
		
		ClothingStore clothingStore = createClothingStore(); // 객체생성 
		StoreDTOBuilder builder = new ClothingStoreDTOBuilder(); // 빌더패턴 부르기 
		StoreDTO dto = clothingStoreBuild(builder,clothingStore); // 빌더패턴과 엔티티를 넣어서 최종결과물 생성 
		
		System.out.println(dto);
		
	}

	//Director (빌더패턴을 불러서 객체를 생성해주는 곳) 
	private static StoreDTO clothingStoreBuild(StoreDTOBuilder builder,ClothingStore store) {
		return builder.withName(store.getName())
				.withCity(store.getCity())
				.build();
	}
	
	//ClothingStore 엔티티 만들어놓기 
	public static ClothingStore createClothingStore() {
		ClothingStore store = new ClothingStore();
		store.setCity("Seoul");
		store.setName("H&M");
		store.setOwner("Erling Persson");
		return store;
	}
	
}
```
- 먼저 ClothingStore 엔티티를 만들어줌. setter로 값을 다 셋팅해주긴 했는데 안해두 됨 ~ 직접 director에 값을 넣어서 셋팅도 할 수 있음
- clothingStoreBuild() 메소드가 빌더패턴을 부르는 애임. 전문용어로는 Director라고 부름. 메소드 체이닝으로 값 셋팅을 해줌.
- main메소드에서 빌더패턴을 사용하면 끝 !! 

### 빌더패턴을 더 쉽게 구현해보자 

- Builder클라스를 결과물 객체 내부의 static inner class로 만들어서 더 간단하게 사용하기도 함

```java
public class StoreDTO {

	private String name;
	
	private String city;
	
	private String owner;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	private void setCity(String city) {
		this.city = city;
	}

	private String getOwner() {
		return owner;
	}

	private void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "StoreDTO [name=" + name + ", city=" + city + ", owner=" + owner + "]";
	}
	
	//builder instance 가져오기 
	public static StoreDTOBuilder getBuilder() {
		return new StoreDTOBuilder();
	}
	
	//Builder inner class
	public static class StoreDTOBuilder{
		
		private String name;
		
		private String city;
		
		private String owner;
		
		private StoreDTO storeDTO;
		
		
		public StoreDTOBuilder withName(String name) {
			this.name = name;
			return this;
		}
		
		public StoreDTOBuilder withCity(String city) {
			this.city = city;
			return this;
		}
		
		public StoreDTOBuilder withOwner(String owner) {
			this.owner = owner;
			return this;
		}
		
		//빌더 메소드 (구성요소 조립)
		public StoreDTO build() {
			this.storeDTO = new StoreDTO();
			storeDTO.setName(name);
			storeDTO.setCity(city);
			storeDTO.setOwner(owner);
			return this.storeDTO;
			
		}	
	}
}

```

- setter를 private으로 줘서 외부에서 접근하지 못하고 오로지 객체 생성될때 빌더가 셋팅해주도록 해줌
- 처음 예시에서는 DTO의 생성자에서 값을 설정해줬는데, 빌더객체를 inner class로 사용하니, DTO가 불변임과 동시에 빌더패턴을 사용하면서 값셋팅이 가능해져 더욱 편리하다.



### 빌더패턴 구현시 고려사항
- Builder를 inner static class로 사용하면 더욱 쉽게 빌더패턴을 사용할 수 있음 <br>
- Director는 인스턴스를 생성하는 Client 클라스에서 바로 불러야함  <br>
- 만약 Builder를 이용해서 한가지의 타입의 객체만을 생성할 예정이라면, Builder를 인터페이스-구현클래스 형식으로 만들지 않아도 괜찮음  <br>
- 생성자의 인자값이 너무 많다면 빌더패턴을 사용하기 좋은 케이스임  <br>

### 빌더패턴을 사용하는 예시
- StringBuilder 객체도 빌더패턴을 구현하여 구성되어 있다고 함 <br>

```java

 	StringBuilder sb = new StringBuilder();
	sb.append("java").append("javascript");
	String str = sb.toString();
	System.out.println(str); // javajavscript

```
https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html  <br>

- (자기 자신을 리턴해서, 메소드 체인이 가능하게 되는 부분임 . 저렇게 append안에 인자값을 넣으면 build()가 되는 듯 함)  
```java
	public StringBuilder append(Object obj)
```
- (StringBuilder에 대해서는 String이 불변이라 메모리 할당을 줄이기 위해서만 사용하는 것으로 알고있었다.
- 하지만 이 모든 것이 가능하게 해준 것은 **빌더패턴**이였던 것이였다.. !)
- StringBuilder와 비슷한 예로 ByteBuffer,CharBuffer등이 있음 

- Calendar 클래스를 구성하는 java.util.Calendar.Builder 클래스도 빌더패턴으로 구성되있다.
- Calander 클래스를 사용하면 내가 원하는 Year,Month,Date 등등 아무 인자값이나 넣어서 값을 빼낼 수 있음
- 이것을 가능하게 해준 것은 빌더패턴 덕분이다.

```java
    public static class Builder{ //Calendar 클래스의 inner 클래스로 구성되있음 
	private static final int NFIEDS = FIELD_COUNT + 1;
	private static final int WEEK_YEAR = FIELD_COUNT;

	private long isntant;
	private int[] fields;

	....

	// Calendar 인스턴스의 구성품을 셋팅하기위한 메소드 
	public Builder setWeekDate(int weekYear,int weekOfYear,int dayOfWeek){
		allocateFields();
		inernalSet(WEEK_YEAR,weekYear);
		internalSet(WEEK_OF_YEAR,weekOfYear);
		internalSet(DAY_OF_WEEK, dayOfWeek);
		return this;
	}

	//최종 부품들을 조립해서 완성품을 전달하는 메소드 
	public Calender build(){
		if(locale == null){
			locale = locale.getDefault();
		}

		....
	}

   }

```


### 최종요약 

- 빌더패턴은 객체 생성시 여러 과정을 거쳐서 생성되도록 설계되있음  <br>
- 생성자의 인자값을 하나하나 분리하여 설정하기 때문에 인자값이 많은 생성자를 사용할 때 개발자는 더욱 편하게 사용할 수 있음 <br>

- ![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/6f862b88-f30b-4304-9946-ba67a6bed525)

- 빌더패턴은 크게 3개의 구성요소로 되어있음 <br>
- **Product** : 최종결과물, Builder에서 만들어질 예정 <br>
- **Builder** : 만드려는 객체의 구성요소를 읽어주는 메소드들이 있음 <br>
- 구성요소들을 조립하여  최종 결과품들을 조립해주는 build() 메소드가 있음 <br>
- Builder가 인터페이스나 추상클래스로 되어있다면 ConcreteBuilder로 구현해서 사용 <br>
- **Director** : Builder 클래스를 사용하는 부분. 인스턴스를 생성하는 클래스에서 바로 사용함 <br>



### 코드보기
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/creational/builder">Code</a>

<hr>


# SimpleFactoryPattern 

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
  + 파라메터가 있어야하는 이유? -> 조건에 따라 인스턴스가 다르게 생성해야하기 떄문임. 파라메터는 어떤 인스턴스를 만들것인지에 대한 조건값 <br>
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


### 코드보기
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/creational/simplefactory/stream">Code</a>

<hr>

# FactoryPattern

- 클라이언트 코드로부터 객체의 생성과정을 분리할 때 씀 
-  클라이언트 코드에 영향을 주지 않으면서 객체를 추가할 수 있음 
- 클라이언트 코드가 아닌, 서브클라스가 팩토리 메소드를 오버라이딩하여 어떤 객체를 인스턴스화시킬지 정해줌

### UML  <br>

![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/6a1bb238-9209-45c6-8c0b-ffc3a42cf243)

Product 
- 최종 만드려는 객체의 템플릿이 되어주는 추상객체

 ConcreteProduct
- Product를 구현하는 최종객체 

Creator 
- 팩토리 메소드(abstract factory method)가 선언되는 객체
- 팩토리 메소드 자체는 추상메소드이기 떄문에 오버라이딩해서 사용  
- 팩토리 메소드의 역할은 **인스턴스를 생성하는 단순한 애**!  

ConcreteCreator
- 추상 팩토리 메소드를 오버라이딩하여 product객체를 인스턴스화함 

### Factory Method 구현핵심

- Creator(팩토리 메소드가 선언되는 객체)를 만들자 !  
1. Creator는 구상 클래스(Concrete Class)로 만들어도 되고 , 추상으로 만들어도 됨 
2. 다시한번 강조하지만, 팩토리 메소드는 서브클래스에서 오버라이딩 되어 **인스턴스를 생산하는 인스턴스 공장메소드**라는 것을 알고있자 

### 예시 UML  <br>
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/fc79d9ca-a220-4b7b-8fe2-ced2fcebbbe2)

목표 결과물 : JavaDeveloper , PhpDeveloper ( Developer를 상속받음 )  <br>
상속관계로 만든 이유 ? : 팩토리 메소드를 사용해서 좀더 자유롭게 인스턴스를 생성하고 싶기 때문임 <br>
인스턴스를 만드는 객체 : PhpDevCreator , JavaDevCreator ( DevCreator를 상속받음)<br>
상속관계로 만든 이유 ? : 위의 답변과 마찬가지로 좀더 자유로운 인스턴스 생성을 위하여 ..  <br>

### 코드예시 

팩토리메소드가 있는 DevCreator 추상클래스 <br>
```java
public abstract class DevCreator {

	public Developer getDeveloper() {
		Developer dev = createDeveloper(); //여기서 팩토리 메소드를 부른다 ~ 
		dev.improveSkill();
		dev.isOOP();		
		return dev;
	}
	
	//팩토리 메소드
  // 리턴타입으로 Developer (JavaDeveloper , PhpDeveloper는 Developer를 상속받음)
  // 리턴타입을 추상으로 주었기 때문에 createDeveloper는 JavaDeveloper도 리턴할 수 있고 PhpDeveloper도 리턴가능 ! 
  // 추상메소드이기 상속받은 클래스에서 오버라이딩 필수 
	protected abstract Developer createDeveloper();
}

```

실제 인스턴스를 생성하는 JavaDevCreator  ! <br>
팩토리 메소드가 있는 DevCreator를 상속받음 
```java
public class JavaDevCreator extends DevCreator {

	@Override
	public Developer createDeveloper() {
		return new JavaDeveloper(); // 자바개발자 인스턴스 생성 
	}
}

```

클라이언트 코드 ~ <br>
JavaDveloper()라고 인스턴스를 바로 만들지않고 , new JavaDevCreator()를 통해 인스턴스를 가져왔음  <br>
즉, 클라이언트 코드는 인스턴스가 어떻게 만들어졌는지 알수 없다 <br>

```java
public class ClientObj {

	public static void main(String[] args) {	
		printDev(new JavaDevCreator()); 
		printDev(new PhpDevCreator());
	}
	
// DevCreator 안에 팩토리 메소드(인스턴스 공장) 가 있음 
// 실제 인스턴스 생성하는 애가 클라이언트코드가 아니라 외부에 있는 것임 
	public static void printDev(DevCreator creator) { 
		Developer dev = creator.getDeveloper();
		System.out.println(dev);
	}
}
```


## Simple Factory Method VS Factory Method <br>
- 둘다 클라이언트 코드로 부터 인스턴스 생성과정을 막기위한 방법
- Simple Factory는 구상객체(concrete class)에 인스턴스 생성 과정을 위임하는 것이고 (abstract) Factory Method는 여기에서 더 나가서 추상클래스에 <br>
인스턴스 생성과정을 위임하는 것임 !! 즉 인스턴스 생성에 있어 과정이 한번더 추가됨 <br>
- Simple Factory 예시 
```java

public class AppleMusicFactory{

  public Music createMusic(String mood){
      switch(mood) {
        case "English" :
                  return new Popsong();
        case "Korean" :
                 return new Kpop();
      }
  }
}
```

AppleMusicFactory는 구현이 필요없는 구상클래스(concreteClass)인데 여기서 바로 인스턴스를 생성함  <br>

- Factory Method 예시
```java
    public abastract class DevCreator{
      public Developer getDeveloper(){
          Developer dev = createDeveloper();
          return dev;
      }

      //팩토리 메소드
      public abstract Developer createDeveloper();
    } 
```

DevCreator는 추상클래스이기 때문에 상속을 통해 인스턴스가 생성될 수 있음 .<br>
인스턴스를 만드는 팩토리메소드는 추상클래스를 베이스로 인스턴스를 한단계 더 거쳐서 만듬 <br>


### 팩토리 메소드 사용예시 

- Collection의 Iterator() 메소드
```java

public abstract class AbstractCollection<E> extends Object implements Collection<E>{

  //팩토리 메소드
  public abstract Iterator<E> iterator();

}
```
- iterator()메소드는 iterator 인터페이스를 구현한 컬랙션 객체에서 사용
- 컬랙션 객체에서 반복자객체(iterator)를 생성하고 반환하는 역할을 함

```java
public class IteratorExample{
    public static void main(String[] args){

      //List객체
    List<String> myList = new ArrayList<>();
    myList.add("사과");
    myList.add("바나나");
    myList.add("체리);

    //iterator() 메소드 사용 (팩토리 메소드 )
    Iterator<String> iterator = myList.iterator();

      while(iterator.hasNext()){
          String element = iterator.next();
           System.out.println(element);
        }
    }
  }

```

-> myList라는 List 컬랙션 객체를 생성하고 , iterator()메소드를 사용하여 반복자객체(iterator)를 얻음 <br>
-> 반복자 객체는 컬랙션 내의 요소를 순환하는 역할을 하는 특별한 객체인데 , 팩토리메소드로만 객체가 생성되기 떄문에 
<br>
컬랙션 내부구조를 숨기고 , 클라이언트 코드는 쉽게 컬랙션에 저장된 요소를 순차적으로 접근할 수 있음  <br>

### 결론 
- 팩토리 메소드를 사용하는 이유는 인스턴스 생성과정을 클라이언트 코드로부터 숨기기 위함임<br>
클라이언트로부터 숨기려는 이유? <br>
최종결과물 객체를 상속관계로 만들어서 (예시 : Developer <- JavaDeveloper , PhpDeveloper) 추후에 객체를 추가하고 싶을 때 유리함 <br>
- 팩토리 메소드는 상속받는 서브클래스에서 오버라이딩되어 인스턴스를 생성함
- 구현 방법이 복잡함 + 유닛테스팅이 많이 필요함



### 코드보기 
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/creational/factorymethod">Code</a> 




<hr>


# PrototypePattern


### 프로토타입 패턴이란?
이미 존재하는 인스턴스를  프로토타입(Prototype : 표준 , 기준 , 템플릿)으로 삼고, <br>
해당 인스턴스를 복제하여 간단하게 인스턴스를 만들 수 있음 


### UML 

![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/f44772ae-2f2c-4859-8bb0-c3a2e98e085a)


**Prototype** :  **Clone() 메소드**가 들어있는 추상객체 <br>
**ConcretePrototypeA,B** : Prototype 추상객체를 상속받은 구현객체. Prototype의 clone() 메소드를 오버라이딩하여 자기자신을 복제할 수 있음 <br>
**Client** : 클라이언트 코드, clone()메소드를 활용하여 간단하게 인스턴스를 복제함  <br>


### 프로토타입 패턴 구현하는 법 <br>
**clone()메소드를 가지고 있는, 프로토타입 객체를 만든다** <br>

1. 프로토타입 객체는 Cloneable 인터페이스를 구현해야한다.<br>
> Cloneable 인터페이스란? <br>
Cloneable 인터페이스는 마커 인터페이스(Marker Interface)이다. 마커인터페이스(Maker Interface)는 내부에 어떠한 메소드도 없으나,<br> 
이 인터페이스를 구현하는 것만으로도 JVM에게 해당 객체는 복제가 가능하다고 알려준다<br>
Cloneable 인터페이스를 구현하지 않고 clone()메소드를 사용하려고하면  CloneNotSupportedException 예외가 발생됨<br>
2. Object객체의 clone()메소드를 오버라이딩하여 자기 자신을 복제할 수 있도록 설정한다 <br>
3. 오바라이딩된 clone() 메소드는 throw절에 CloneNotSupportedException을 선언.
> CloneNotSupportedException을 선언하는 이유? <br>
서브클라스 중 복제를 막아야하는 경우도 있기 때문이다. <br>


### 예시 UML
>미사일을 추상객체로 만들고, 아이언돔,생화학무기,초음속미사일을 구현객체로 만들어보았음 <br>

![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/59ea9306-7571-465d-b235-70afe8b9b6c8)

**Missiles** : 프로토타입으로 만들 추상객체, 내부에 clone()메소드가가 있어서 자기 자신을 복제할 수 있도록 함 <br>
**HyperSonicMissiles,IronDome** : Missiles을 구현하는 객체, 자기 자신을 복제할 수 있음 <br>
**BioWeapon** : Missiles을 구현하는 객체, 이 객체는 복제가 불가하도록 설정해봄 <br>


### 예시 코드

프로토타입 객체인 Missiles <br>
```java
  public abstract class Missiles implements Cloneable{ //cloneable 인터페이스 구현

      (코드생략)

      //자신을 복제하는 clone()메소드 
      @Override
      public Missiles clone() throws CloneNotSupportedExcetion{
          Missiles missile = (Missiles)super.clone(); //자기 자신을 복제
          missile.initialize(); //초기화 메소드 
          return missile;
      }    
  }
```

Missiles은 프로토타입으로 사용될 추상객체이다<br>
Cloneable 인터페이스(마커인터페이스)를 구현하여, JVM에게 복제가 가능한 객체라는 것을 알려주고 <br>
Object의 clone()메소드를 오버라이딩하여 자기자신을 복제하고 리턴한다 <br>
주의점은 복제가 불가한 객체도 있기때문에 CloneNotSupportedException을 예외처리해줌. <br>
<br>

복제가능한 IronDome 구현객체 
```java
  public class IronDome extends Missiles{
      private String state = "아이언돔 발사준비";
      public void attack(){
        this.state = "공격";
    }

    @Overrride
    protected void reset(){
      this.state = "아이언돔 재발사";
  }
    }

```

IronDome객체는 Missiles 프로토타입 객체를 구현하고 있음 <br>
clone()메소드가 이미 Missiles 객체에 재정의되어있기 떄문에 따로 설정하는 부분은 없음 <br>

<br>

복제가 불가한 BioWeapon 객체 <br>
```java
  public class BioWeapon extends Missiles{
      (코드생략)

      //clone()이 불가하도록 재정의
      @Override
      public Missiles clone() throws CloneNotSupportedException{
        throw new CloneNotSupportedException("생화학 무기는 복제가 불가합니다");
    }
  }
```

BioWeapon객체의 경우, 인스턴스 복제가 불가하도록 clone()메소드를 재정의 해줌 <br>
<br>

클라이언트 코드 <br>

```java

public class Client{
      public static void main(String[] args){
          //아이언돔 인스턴스 생성 
          IronDome d1 = new IronDome();
          d1.move(new Position(5),5);
          d1.attack();
          System.out.println(d1); // 아이언돔 공격


          //아이언돔 인스턴스 복제 (new키워드가 아닌 아닌 , clone()메소드로 인스턴스 생성) 
          IronDome d2 = (IronDome)d1.clone();
          d2.reset();
          System.out.println(d2); // 아이언돔 재발사

          //복제 불가한 생화학무기 인스턴스 생성
          BioWeapon b1 = new BioWeapon();
          b1.attack()
          System.out.println(b1) //생화학무기 공격

          //생화학무기 인스턴스 복제시 throw CloneNotSupportedException이 발생함
          BioWeapon b2 = (BioWeapon)b1.clone(); // "생화학 무기는 복제가 불가합니다"

      }
  
  }

```

### 프로토타입 패턴 사용시 고려사항 
1. Cloneable 인터페이스를 구현하여 복제가 가능한 객체라는 JVM에게 알려줌 
2. Object객체의 clone()메소드 오버라이딩시 접근제한자를 public으로 바꿔주기
3. clone() 메소드로 생성된 객체는 새로운 heap메모리 영역을 할당받음. 내부에 있는 내용만 복제된 것. 

### 프로토타입 패턴 사용예시 
Object 객체의 clone()은 프로토타입 패턴 그 자체임 <br>
Cloneable 인터페이스를 구현하여 JVM에게 해당객체는 복제가 가능하다는 것을 표시하고, Object 객체의 clone()메소드를 오버라이딩하여 사용

### 결론 
1. 상태의 변화가 많이 없는 대용량의 인스턴스를 만들고 싶을 때 사용하기
2. clone()메소드로 간단하게 인스턴스를 복제하여 생성이 가능 
3. 로직이 거의 없는 얕은 복사라서 Java개발자들에게 선호되는 패턴은 아니라고 한다.





### 코드보기 
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/creational/prototype/war">Code</a> 





<hr>

# ObjectPool

### 오브젝트 풀패턴이란 ?
**객체(인스턴스)를 미리 생성**해두고,**필요할 때마다 가져다가 쓰는 형태**이다. <br>
객체의 생성과 소멸에 의한 CPU의 컨텍스트 스위칭 오버해드를 줄이기 위해 생겨났음 <br>
스레드풀(thread pool) , 커넥션풀(connection pool) 등이 오브젝트 풀패턴으로 만들어져있음  

### UML 

![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/289e9577-068f-4bdd-9c81-72ddb4db55cb)

+ AbstractReusable : 기능을 정의해둔 추상객체. 구현을 통해 사용가능 
+ ConcreteReusable : 추상객체를 구현한 구상객체(Concrete Class) <br>
  ( 초기화 메소드를 넣어두고 객체를 재사용할 때마다 상태값을 초기화 시켜줌 )
+ ObjectPool : 오브젝트풀 객체 , 재사용하는 인스턴스를 부르는 곳 
+ Client : 오브젝트풀을 이용하여 재사용가능한 인스턴스를 가져옴 

### 오브젝트풀 객체 사용법
1. 인스턴스를 담아주는 오브젝트풀 객체를 만든다. <br>
  ( 인스턴스를 관리,생성,보관의 역할을 함 ) <br>
2. 스레드 안전(Thread safe: Thread가 자원에 접근할 때 다른 Thread의 접근을 막음)을 통해 구현되야함 <br>
  ( 큐(Queue) 자료구조를 이용하여 객체를 저장함. 선입선출(FIFO:First In First Out)을 통해 데이터의 순서를 지킴 ) <br>
3. 인스턴스의 상태값을 초기화 시켜주는 메소드를 정의하여 , 클라이언트코드가 인스턴스를 재사용할 때 새것처럼 보이게 해줘야함



### 예시UML 

![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/ac26a9f6-670e-45cc-a086-2c4a56ddc157)

1. Client : 클라이언트 코드, 인스턴스를 부르는 객체
2. ObjectPool : 오브젝트풀 객체 , 인스턴스를 전달받아 객체를 큐에 보관함 <br>
인스턴스를 클라이언트에게 주는 메소드와 다시 전달받아 큐에 저장하는 메소드가 정의되있음. <br>
큐의 역할은 선입선출(FIFO)방식으로 데이터에 빠르게 접근할 수 있다는 점에서 캐시메모리와 비슷한 동작을 하는 것임 <br>
3. Poolable : 객체 상태를 초기화 시켜주는 메소드가 들어있는 추상객체
4. Item : Poolable 추상객체를 상속받은 추상객체
5. Weapon : Item 추상객체를 구현한 구상객체

### 코드예시 

오브젝트풀 객체 
```java  
public class ObjectPool<T extends Poolable>{

    //인스턴스를 담는 큐 
    private BlockingQueue<T> availablePool;

    //Supplier : 인스턴스를 받는 파라메터 , count : 만드려는 인스턴스의 갯수
    public ObjectPool(Supplier<T> creator,int count){
      //큐를 구현하고 
      availablePool = new LinkedBlockingQueue<>();
      //count의 갯수만큼 큐에 인스턴스를 담음.
      for(int i=0;i<count; i++){
          availablePool.offer(creator.get());
      }
  }

  //클라이언트 코드가 큐에서 인스턴스를 가져가는 메소드
// 큐에 인스턴스가 있을 때만 반환 
  public T get(){
    try{
          return availablePool.take();
      }catch(InterruptedException ex){
          System.err.println("인스턴스가 없습니다");
      }
      return null;
  }

 //클라이언트가 큐에게 인스턴스를 돌려주는 메소드
  public void release(T obj){
      obj.reset(); //받아온 인스턴스 초기화
      try{
          availablePool.put(obj); // 큐에 인스턴스를 담음 
        }catch(InterruptedException e){
          //큐가 꽉차면 안받음
          System.err.println("자리없음");
        }
  }
}
```

클라이언트코드 
```java
public class Client{
  // Weapon 인스턴스를 5개 생성하여 오브젝트풀에 저장
  public static final ObjectPool<Weapon> weaponPool = new ObjectPool<Weapon>(() -> new Weapon("아이언돔"),5));

public static void main(String[] args){
      Weapon w1 = weaponPool.get(); // 저장된 첫번째 인스턴스를 꺼냄
      w1.setPower(100); // 상태값 설정
      Weapon w2 = weaponPool.get(); // 저장된 두번째 인스턴스를 꺼냄
      w2.setPower(70);

    w1.attack(); //첫번쨰 인스턴스로 공격
    w2.attack(); // 두번쨰 인스턴스로 공격

    weaponPool.release(w1); // 첫번쨰 인스턴스를 오브젝트풀에 다시 돌려줌
    weaponPool.release(w2); // 두번째 인스턴스를 오브젝트풀에 다시 돌려줌 

  }
}
```


### 오브젝트풀 패턴 사용시 고려사항 
1. 오브젝트풀 패턴은 주로 외부자원을 관리하고 재사용하는데 사용해야함 ( DB연결,스레드풀(Thread pool) 등) <br>
인스턴스를 미리 생성해둔 경우 메모리를 더 많이 사용할 수 있음 <br>
2. 오브젝트풀이 메모리를 많이 사용하게 되면 초기 실행속도가 느려짐 . <br>
따라서 동적으로 저장 인스턴스 수를 조절하는 알고리즘을 만들거나,설계시 시스템의 메모리 제한을 고려해야함  <br>



### BasicDataSource 오브젝트풀 사용예시 
```java

//커넥션풀(오브젝트풀) 객체. DB와 연결해주는 Connection 인스턴스들이 있음 
 BasicDataSource dataSource = new BasicDataSource();
//접근권한 설정 
   dataSource.setDriverClassName("드라이버 클라스경로");
   dataSource.setUrl("db서버경로");
   dataSource.setUsername("유저이름");
   dataSource.setPassword("비번");

 //커넥션풀에서 커넥션 인스턴스를 가져옴 
 Connection conn = dataSource.getConnection(); 
```


### 코드보기 
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/creational/objectpool/gameworld">Code</a> 




<hr>


# Singleton

## 싱글톤(Singleton) 패턴이란?
- 싱글톤 클래스는 오직 하나의 인스턴스를 가지며, 그 인스턴스에 전역적(global)으로 접근할 수 있는 단일 지점(single point)을 제공함
- "단일 지점(single point)"이라는 용어는 메서드나 필드 값을 통해 싱글톤 클래스에 접근할 수 있는 장소를 가리킴
- 싱글톤 패턴은 클래스를 딱 한 번만 인스턴스화하고 오직 하나의 인스턴스만 생성되어 공유될 수 있도록 함 
- 싱글톤 클래스에 정의한 상태(state)는 어플리케이션 모두에서 접근이 가능한 전역 상태임


## UML 
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/7ccfb337-6fb1-4670-a28a-6d2144bd89cc)

### Singleton 클래스란?
- 어플리케이션에서 **하나만 존재**하는 특별하고 유니크한 인스턴스를 만드는 곳 
### 싱글톤이 쓰이는 곳 
- 싱글톤 클래스는 일반적으로 리소스 공유, 설정 관련 등에서 사용되며 유일한 객체를 필요로 하는 경우에 쓰임 
### 리소스 공유, 설정관련이 유일(유니크)한 객체를 쓰는 이유?
- 일관성을 유지해야하기 때문임. 여러 객체나 모듈에서 설정 데이터 및 공유 리소스에 접근할 때는 일관된 상태를 유지하는 것이 예기치않은 동작을 방지할 수 있음


## 싱글톤 클래스 구현법  

### 인스턴스 생성관리
- 외부에서 생성자에 접근할 수 없도록 함 
- 하위 클래스 & 상속은 허용되지 않도록 함 <br>
(상속을 허용하는 경우, 서브클래스는 여러 인스턴스를 생성할 수 있게됨)
### 싱글톤 인스턴스 접근법 
- public static 메소드(정적메소드)를 이용해 접근 
### 싱글톤 구현방법 
- Early initialization (Eager Singleton) : 클래스가 로드되거나 초기화될 때 즉시 싱글톤 인스턴스를 생성
- Lazy initialization (Lazy Singleton) : 필요할때만 싱글톤을 생성 



## Eager Singleton 예시 

```java 
public class EagerRegistry{
	
	//외부에서 인스턴스 생성 X
	private EagerRegistry(){}

	//인스턴스를 미리 생성해둠 
	private static final EagerRegistry INSTANCE = new EagerRegistry();


	//생성해둔 인스턴스(자기자신)를 리턴 
	public static EagerRegistry getInstance(){
		return INSTANCE:
	}

}
```
- 생성자의 접근제한자를 private으로 설정하여 외부에서 인스턴스 생성이 안되도록 함 
- getInstance()메소드는 자기자신을 리턴하며, 어디서든 접근이 가능하도록 접근제한자를 public으로 설정 



## Lazy Singleton 예시


### 더블 체크 락(DCL)방식 (동기화처리) 

```java 
public class LazyRegistry{

	//외부 인스턴스 생성 X
	private LazyRegistry(){}

	//volatile : 캐시메모리가 아닌, 항상 메인메모리의 값을 사용 
	//스레드가 항상 메인메모리의 최신 값을 읽도록 함 
	private static volatile LazyRegistry INSTANCE;

	
	public static LazyRegistry getInstance(){
	
		//동기화 처리 
		if(INSTANCE == null){
			synchronized(LazyRegistry.class){
				if(INSTANCE == null){
					INSTANCE = new LazyRegistry();
				}
			}
		}
	
		return INSTANCE:
	}
}

```

-  인스턴스를 미리 생성하고 있지 않음. 동기화 조건에 맞을 때만 생성하도록 함 
-  멀티스레드 환경에서 동기화처리를위해 쓰임 <br>
( 만약 두개의 스레드가 동시에 인스턴스 생성 메소드에 접근한다면 각각 다른 인스턴스가 생성될 것이기 때문임  ) <br>


### Lazy initialization holder idiom(holder에 의한 초기화)

```java 
public class LazyRegistry{
	//외부 인스턴스 생성막기 
	private LazyRegistry(){}

	//inner class
	private static class RegistryHolder{
		//인스턴스 생성을 inner 클래스 내부에 선언 
		static LazyRegistry INSTANCE = new LazyRegistry();
	}
	
	//메소드를 부를 때만 인스턴스 생성됨 
	public static LazyRegistry getInstance(){
		return RegistryHolder.INSTANCE();
	}	
	
}

```

- 인스턴스를 inner 클래스 내부에 숨겨두고 , getInstance()메소드를 사용할 때만 인스턴스가 사용되도록 함 



### Enum

```java 
public enum RegistryEnum{
	//사용하려는 상수 
	INSTANCE;
	
	public void getConfiguration(){
	
	}
}
```


* enum을 이용하는 이유 
1. 상속이 불가함 
2. enum 인스턴스 생성 불가 (enum으로 정의한 상수 중 하나를 선택하여 사용할 수 있지만 , 직접 인스턴스 생성이 불가함)



## 싱글톤 구현팁 
-  Eager Singleton(클래스 로드시 즉시 싱글톤 생성)하는 것을 가장 먼저 구현해놓고 실행시 문제가 있을 경우(멀티스레드 이슈 etc) Lazy Singleton(원할때만 싱글톤 생성)으로 전환하기


## 싱글톤 예시 

java.lang.Runtime 

```java
public class Runtime{

	//외부에서 인스턴스 생성 X
	private Runtime(){}

	private static Runtime currentRuntime = new Runtime();
	
	
	public static Runtime getRuntime(){
		return currentRuntime;
	}
}

```
- Runtime 클래스는 Eager Singleton 패턴을 따르고 있음<br>
- 애플리케이션에서 항상 하나의 Runtime 인스턴스만 사용되며, 초기화가 즉시 이루어지므로 멀티스레드 환경에서 안전한 방식으로 Runtime 클래스의 인스턴스를 관리해줌 <br>.



### 싱글톤 주의사항 
- 유닛테스트가 어려움 <br>
( 어플리케이션 전체에서 단일 인스턴스를 공유하기 때문에, 해당 인스턴스를 모킹하는 것이 어렵다 ) <br>
- 싱글톤이 변경 가능한 전역 상태(mutable global state)를 많이 가지고 있다면, 디자인이 잘못된 것임 <br>
( 어플리케이션 전체에서 사용되기 때문에 버그유발 가능) <br>


### 결론 
- 클래스당 하나의 인스턴스만을 가지게 하고 싶을 떄 사용함
- 스프링에서 빈(bean) 객체를 생성하고 관리할 떄 쓰는 방식
- 스프링 컨테이너(ApplicationContext) 내에서 하나의 빈 객체 인스턴스만을 유지하기 위함임
- 싱글톤 스코프로 빈의 상태를 공유하고 빈을 여러 곳에서 쉽게 주입하거나 참조할 수 있도록 도와줌

### 코드보기 
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/creational/singleton">Code</a> 


<hr>

# 구조패턴 

# AdapterPattern

## 어댑터(Adapter) 패턴이란?
- 클라이언트 코드가 사용하는 인터페이스가 내부 클래스와 다를 때 이 둘을 적응/연결 시켜준다. 
- 인터페이스와 클래스를 감싸서(wrap) 연결하는 패턴이기 때문에 wrapper라고도 불린다. 

## UML 
어댑터 패턴의 구조는 크게 클래스 어댑터(Class Adapter)와 객체 어댑터(Object Adapter)로 나누어진다.
- 클래스 어댑터 ( Class Adapter ) 
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/6b37f66d-be82-4d8a-a9fe-5971f4ec8e8a)
1. 클래스 어댑터는 상속(Inheritance)을 통해 어댑터 역할을 수행한다.
2. 기존의 클래스를 확장(상속)하여 새로운 클래스를 생성한다. <br>
2.1 기존의 클래스를 상속하는 것이기 때문에 메소드 호출시 this 키워드를 사용한다.

- 객체 어댑터(Object Adapter)
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/cc27dfca-5024-4ca3-81e0-f5c4ae6c9be8)
1. 객체 어댑터는 구성(Composition)을 통해 어댑터 역할을 수행한다
2. 어댑터 클래스는 어댑터 대상 클래스의 인스턴스를 내부에 포함된다. <br>
2.1 인스턴스 내부에 포함하는 것이기 때문에 인스턴스 변수로 메소드를 사용한다. 

## 구현예시

직원의 사원카드를 만드는 시스템이다. 

### 클래스 어댑터(Class Adapter) 
- 클라이언트 인터페이스의 적응을 상속(extends)을 통해 실현한다.
 
### UML 
- 클래스 어댑터(Class Adapter)
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/68bc4627-d2d1-458a-9b05-30d829f12d08)
1. BusinessCardDesigner : 클라이언트 코드
2. Customer : 클라이언트 코드가 카드를 만들기 위해 사용하는 인터페이스
3. Employee : 카드가 만들어지기 위한 메소드가 들어있는 클래스 (어댑터 대상)
4. EmployeeClassAdapter : 클라이언트가 사용하는 인터페이스인 Customer와 클라이언트가 필요로 하는 메소드가 들어있는 Employee 클래스를 연결해주는 어댑터 클래스

### 코드예시 

- BusinessCardDesigner 
```java
// 클라이언트코드, 파라메터로 Customer가 필요

public class BusinessCardDesigner{
	public String designCard(Customer customer){
		//생략
	}
}
```

- Customer
```java
//클라이언트 코드가 사용하는 인터페이스
public interface Customer{
	String getName();
	String getDesignation();
}
```

- Employee
```java
//클라이언트 코드가 필요로 하는 메소드가 있는 클래스 
public class Employee{
	private String fullName;
	private String jobTitle;
	// getter & setter
}
```

- EmployeeClassAdapter
```java
//Employee 클래스를 Customer 인터페이스에 적응시켜주는 클래스
public class EmployeeClassAdapter extends Employee implements Customer{
	@Override
	public String getName() {
		return this.getFullName();
	}

	@Override
	public String getDesignation() {
		return this.getJobTitle();
	}
}
```

### 객체 어댑터(Object Adapter) 
-  클라이언트 인터페이스의 적응을 구성(Composition)을 통해 실현한다.

### UML 
- 객체 어댑터(Object Adapter)
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/e928e18a-b0e0-47a3-bc6c-0317650e3ba5)

1. BusinessCardDesigner : 클라이언트 코드
2. Customer : 클라이언트 코드가 카드를 만들기 위해 사용하는 인터페이스
3. Employee : 카드가 만들어지기 위한 메소드가 들어있는 클래스 (어댑터 대상)
4. EmployeeObjectAdapter : 클라이언트가 사용하는 인터페이스인 Customer와 클라이언트가 필요로 하는 메소드가 들어있는 Employee 클래스를 연결해주는 어댑터 클래스

### 코드예시 
- 클래스 어댑터와 동일하며 어댑터 클래스를 구성(Composition)으로 설계하는 점에서 다르다.

- EmployeeObjectAdapter
```java
public class EmployeeObjectAdapter implements Customer{
	private Employee adoptee; // 어댑터 대상 인스턴스를 내부에 포함

	public EmployeeObjectAdapter(Employee adoptee){
		this.adoptee = adoptee;
	}

	@Override
	public String getName(){
		return adoptee.getFullName();
	}
	@Override
	public String getDesignation(){
		return adoptee.getJobTitle();
	}

}
```

## 고려사항 

- 일반적으로 클래스 어댑터는 선호되지 않는다.
  
1. 다중 상속의 제한 <br> 
- 클래스 어댑터는 대상 클래스를 상속받기 때문에, 대상 클래스가 이미 다른 클래스를 상속받고 있는 경우 다중 상속의 문제가 발생할 수 있다. 
2. 클래스가 인터페이스가 아닌 경우 <br> 
- 클래스 어댑터는 어댑터 클래스가 어댑터 대상 클래스를 상속받기 때문에, 대상 클래스가 인터페이스가 아닌 구체적인 클래스인 경우에는 어댑터 패턴을 적용하기 어려울 수 있다.
3. 런타임에 대상 클래스 변경의 어려움 <br>
- 클래스 어댑터는 컴파일 타임에 상속 관계가 정해지기 때문에, 런타임에 동적으로 대상 클래스를 변경하기 어려울 수 있다. 반면에 객체 어댑터는 구성(Composition)을 사용하므로, 런타임에 어댑터 대상 클래스의 인스턴스를 변경하거나 여러 대상 클래스를 동시에 사용하는 등의 유연성을 제공하기 때문에 선호된다.
   


## 결론 
- 클라이언트 코드가 사용하는 인터페이스가 내부 객체와 다를 때 어댑터 패턴으로 설계할 수 있다.
- 클래스 어댑터(Class Adapter)패턴은 어댑터 대상 클래스(내부 객체)를 상속받고, 클라이언트 코드의 인터페이스를 구현하여 설계하는 방법이다.
- 객체 어댑터(Object Adapter) 패턴은 클라이언트 코드의 인터페이스를 구현하고 어댑터 대상 클래스(내부객체)는 인스턴스로 사용하여 설계하는 방법이다. (어댑터 대상 클래스의 서브 클래스 구현을 허용하는 장점이 있다)

### 코드보기 
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/structural/adaptor">Code</a> 
  
<hr>



# BridgePattern

## 브릿지(Bridge) 패턴이란?
- 구상클래스 & 추상클래스가 상속으로 결합되어있을 때 브릿지 패턴을 이용하여 서로 영향을 주지 않고 결합을 풀 수 있다.
- 두 개의 별도 상속 계층을 생성하며 **동작을 위한 구현부와 확장을 위한 추상부를 생성한다** 

## UML 

![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/91bf8459-4423-4991-9ad3-7e56171f95a8)


+ **Abstraction** : 기능 계층의 최상위 클래스이며 구현 부분에 해당하는 클래스 인스턴스를 가지고 해당 인스턴스를 통해 구현 부분의 메소드를 호출한다. <br>
+ **RefinedAbstraction** : 기능 계층에서 새로운 부분을 확장한 클래스  <br>
+ **Implementor** : Abstraction의 기능을 구현하기 위한 인터페이스 정의  <br>
+ **ConcreteImplementor** : 실제 기능을 구현  <br>


## 구현예시 

![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/a48d12ec-cc1c-4577-b583-6fa706724e38)

+ **FifoCollection** : 선입선출 first in first out 알고리즘이 구현된 클래스이다.  offer는 객체를 더하고 poll은 객체를 빼는 메소드이며 둘은 선입선출 알고리즘으로 바탕으로 실행된다. <br>
+ **Queue** : FifoCollection을 구현하는 구상클래스 <br>
+ **LinkedList** : 브릿지 패턴으로 연결될 Implementor 클래스(FifoCollection에는 없는 메소드들이 있으며, Queue에 의해 연결되게 된다.) <br>
+ **SinglyLinkedList, ArrayLinkedList** : LinkedList를 구현한 클래스 <br>
  <br>
브릿지패턴은 **LinkedList와 FifoCollection의 연결**로 이루어지는데, 구체적으로는 FifoCollection의 구현 클래스인 Queue가 LinkedList의 메소드를 사용하면서 연결이 시작된다.
<br>

- 브릿지패턴이 이루어지는 Queue클래스를 살펴보자
```java
public class Queue<T> implements FifoCollection<T>{

        private LinkedList<T> list;

	//클라이언트는 Queue  인스턴스 생성시, LinkedList의 구현클래스를 인자값으로 넘긴다
	public Queue(LinkedList<T> list){
		this.list = list;
	}

	//LinkedList의 메소드를 사용
	@Override
	public void offer(T element){
		list.addLast(element);
	}

	@Override
	public T poll(){
		return list.removeFirst();
	}
}
 
```

- 클라이언트 코드
```java 
public class Client{
	public static void main(String[] args){
 		//Queue 인스턴스 생성시, LinkedList의 구현클래스를 인자값으로 넣어 사용 
		FifoCollection<Integer> collection = new Queue<>(new SinglyLinkedList<>());
		collection.offer(10);
		collection.poll(); //10
  		
	}
}
```

- FifoCollection 과 LinkedList는 서로 변경하지 않으면서, 기능을 자유롭게 사용하고 있다. 이는 두개의 계층 클래스가 각각 독립적으로 이루어졌기 때문에 가능하다.


## 브릿지패턴 사용예시 
- JDBC API인 java.sql.DriverManager 클래스도 브릿지패턴을 사용하고 있다.
  ![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/f8309ce8-783f-4a71-b61e-ef2068e1913c)
<br>
JDBC 드라이버 매니저는 라이브러리로 등록된 DB Driver를 관리하고, DB 커넥션을 획득하는 역할을 한다. 드라이버 매니저의 경우도 구현부와 추상부가 각각 독립적으로 구분되어 있기 때문에 클라이언트는 DriverManager를 변경할 필요없이 간단하게 새로운 DB 드라이버 클래스로 변경할 수 있다. <br>

## 결론
- 추상과 구현의 결합도를 낮추고 싶을 때(decoupling) 브릿지 패턴을 사용한다. 
- 추상과 구현에 각각의 클래스 계층구조를 설계하고 두 개의 구조를 브릿지패턴을 이용하여 연결한다.
- **동작을 위한 구현부와 확장을 위한 추상부를 분리한다**
- stackoverflow에서 쉬운 그림 예시를 찾았다. <br>
 ![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/33b675de-6c31-4bae-bbfb-5268c2d3306e)
- 레거시 코드에서의 브릿지패턴 사용은 매우 복잡하여 사용을 지양한다고 한다. 

  
### 코드보기 
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/structural/bridge">Code</a> 


<hr>



# DecoratorPattern

## 데코레이터 패턴(Decorator Pattern)이란?
- 주어진 상황 및 용도에 따라 어떤 객체에 책임(기능)을 동적으로 추가하는 패턴을 말한다.

## UML
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/951803b1-abf3-41d3-84d0-9f745e06fe3e)
- Component : 클라이언트 코드가 사용하는 인터페이스
- ConcreteComponent : 인터페이스의 구현클래스
- Decorator : 데코레이터 클래스. 인터페이스의 기능을 확장한다. **Decorator**라는 영단어 그대로 장식이 되는, 부수적인 기능을 추가할 수 있다. ConcreteDecorator는 이 Decorator를 상속받아 구현한다. Decorator는 Component라는 재귀적 연관을 이용하여 주기능과 여러 부수적인 기능들을 재조합 할 수 있게 한다.


## 데코레이터 패턴 예시 

### UML 
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/b3767817-de44-456a-866e-bb82b81965d5)

- Message : 클라이언트가 사용하는 인터페이스
- TextMessage : Message의 구현클래스. 기본 텍스트 메시지를 나타낸다.
- Base64EncoderMessage : Base64로 텍스트 메시지를 인코딩 시켜주는 데코레이터.
- HtmlEncodedMEssage : html로 텍스트 메시지를 인코딩 시켜주는 데코레이터 
<br>

### 코드

- Client 코드 
```java
public class Client{
	public static void main(String[] args){

		Message m = new TextMessage("<Force>");

		//데코레이터 패턴사용
		Message decorator = new HtmlEncodedMessage(m);
                // html 인코딩된 컨텐츠 출력
		System.out.println(decorator.getContent()); 

		decorator = new Base64EncodedMessage(decorator);
                // base64 인코딩된 컨텐츠 출력
		System.out.println(decorator.getContent);


	}
}
```


- TextMessage 
```java
public class TextMessage implements Message{
	private String msg;

 	public TextMessage(String msg){
  		this.msg = msg;
  	}

  	@Override
  	public String getContent(){
		return msg;
  	}
}
```

- 데코레이터
```java
public class BaseEncodedMessage implements Message{

	private Message msg;

	public Base64EncodedMessage(Message msg){
		this.msg = msg;
	}

	@Override
	public String getContent(){
		return Base64.getEncoder().encodeToString(msg.getContent().getBytes();
	}
}
```

클라이언트 코드에서는 기본 TextMessage로 시작하여 HtmlEncodeMessage, Base64EncodedMessage로 기능이 추가된 텍스트메시지를 받을 수 있다. <br>
이 두개의 데코레이터로 인해,TextMessage를 수정하지 않고도 특정 기능이 추가되었다.이런식으로 추가적인 기능을 데코레이터 패턴으로 확장시키면 런타임에 동적으로 기능을 조합하고 확장할 수 있다. <br>


## 데코레이터 패턴 예시2
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/8c161c43-76e3-4a42-b7b0-ae23e0fd6598)
- Java I/O 라이브러리도 데코레이터 패턴을 기반으로 설계되있다. 자바의 입출력 시스템은 InputStream,OutputStream 등과 같은 추상 클래스와 인터페이스를 제공하며 이러한 클래스들은 데이터를 읽고 쓰는데 필요한 기본적인 동작만을 정의하고 있다.
- 데코레이터 패턴을 이용하여 기본 동작에서 다양한 기능을 동적으로 확장할 수 있다. 예를들어 BufferedInputStream, BufferedOutputStream,FileReader,FileWriter 등은 모두 데코레이터 패턴을 사용하여 기능을 추가한 예시이다.

  
## 결론 
- 존재하는 클래스에 기능을 추가하고 싶을 때 데코레이터 패턴을 사용한다.
- 데코레이터는 또 다른 데코레이터를 감쌀 수 있으며 이는 원본 객체를 감싸고 있다.
- 클라이언트 코드는 데코레이터 사용여부를 알지 못한다.
 
### 코드보기
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/structural/decorator">Code</a>


<hr>

# CompositePattern

## Composite Pattern 이란?
- 개별 객체와 복합 객체(여러 객체로 이루어진 객체)의 계층 구조를 일관되게 처리하기 위해서 사용한다.
- 주로 트리 구조를 형성하는 객체들 간의 관계를 모델링하는데 활용된다.
- Composite Pattern 의 목적은 클라이언트 코드가 컴포지트와 리프 노드를 동일하게 접근할 수 있도록 하는 것이다.


## UML
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/f9b4408a-fb29-4a3f-ab73-21e8f5e67457)
- **component**
- 컴포지션의 모든 개체에 대한 기본 인터페이스
- 하위 컴포지트를 관리하는 공통 메소드가 있는 인터페이스 또는 추상 클래스
- Leaf 클래스와 전체에 해당하는 Composite 클래스의 공통 인터페이스를 작성
- **leaf**
- 기본 구성 요소의 기본 동작을 구현하며 다른 객체에 대한 참조는 포함되어 있지 않음
- 구체적인 부분 클래스
- Composite 객체의 부품으로 설정
- **composite**
- 전체 클래스
- Leaf를 요소로 가진다.
- 복수개의 Leaf, 복수개의 Composite 객체를 부분으로 가질 수 있다.
- **client**
- 기본 구성 요소 개체를 사용하여 컴포지션 요소에 엑세스 할 수 있음


## 예시 
- **Component**
  ```java
  public abstract class File{
	private String name;

        public File(String name){
           this.name = name;
       }

       public String getName(){}

       public void setName(String name){}

       public abstract void ls();
  	
  }
  ```
  - component 클래스이며 컴포지션 모든 개체에 대한 기본 인터페이스이다.
  - ls를 추상 메소드로 설정하여 , leaf&composite 클래스가 공통으로 해당 메소드를 오버라이딩하도록 강제하였다.

 - **Leaf Node**
```java
public class BinaryFile extends File{
     private long size;

     public BinaryFile(String name, long size){
          super(name);
          this.size = size;
    }

   @Override
   public void ls(){
       System.out.println(getName() + "\t" + size);
    }
}
```
- 리프노드로서 부분 클래스의 역할을 한다.
- 리프노드이기 때문에 자식 클래스는 없다. 
- Composite 클래스의 부분 클래스로 사용된다. 


- **Composite**
```java
public class Directory extends File{

     private List<File> children = new ArrayList<>();

    public Directory(String name){
        super(name);
    }

    @Override
    public void ls(){
       System.out.println(getName());
       children.forEach(File::ls);
  }

  @Override
  public void addFile(File file){
     children.add(file);
 }
}
```
- File 클래스를 확장하여 List<File> children 필드를 통해 복수의 자식 파일 또는 디렉토리를 가질 수 있다.
- ls 메소드는 현재 디렉토리의 이름을 출력하고 자식들에 대해 File::ls 를 호출하여 재귀적으로 모든 파일과 디렉토리의 이름을 출력한다.

- **Client**
```java
public static void main(String[] args){

   //leaf or composite 객체 관계없이 동일하게 ls 메소드를 사용하고 있다. 
    File root = createTreeOne();
    root.ls();

    File root2 = createTreeTwo();
    root2.ls();
}

// composite 객체를 반환(directory)
private static File createTreeOne() {
	File file1 = new BinaryFile("File1", 1000);
	Directory dir1 = new Directory("dir1");
	dir1.addFile(file1);
	File file2 = new BinaryFile("file2", 2000);
	File file3 = new BinaryFile("file3", 150);
	Directory dir2 = new Directory("dir2");
	dir2.addFile(file2);
	dir2.addFile(file3);
	dir2.addFile(dir1);
	return dir2;
}

//leaf node 객체를 반환 (binaryFile)
private static File createTreeTwo() {
	return new BinaryFile("Another file", 200);
}
```
- 클라이언트 코드는 createTreeOne 메서드로 composite 객체인 directory를 반환하고, createTreeTwo 메서드를 통해 leaf node인 binaryFile을 반환한다. 
- 두 객체의 계층 차이의 관계없이 동일하게 ls() 메소드를 호출하여 사용하고 있다. 즉, 클라이언트는 단일 객체이든 복합 객체이든 동일한 방식으로 다룰 수 있다. 

## 결론 
- 부모-자식 계층이거나 복합 계층으로 이루어진 구조라면 Composite Pattern을 고려한다.
- Composite Pattern의 주요 목적은 클라이언트 코드가 단일 객체와 복합 객체(Composite)를 동일한 방식으로 다룰 수 있도록 하는 것이다.
  
### 코드보기
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/structural/composite">Code</a>


<hr>

# FacadePattern

## Facade Pattern 이란?
- 'Facade'는 얼굴 또는 건물의 외부 전면을 나타내며, 디자인 패턴에서는 복잡한 서브 시스템들의 의존성을 최소화하는 패턴이다.
- 서브 시스템에 있는 인터페이스들에 대한 **통합 인터페이스**를 제공하여 서브 시스템을 더 쉽게 사용할 수 있도록 만드는 더 높은 수준의 인터페이스를 말한다.
- 결합도가 강하게 묶여 있는 레거시 코드에서 유용하게 사용할 수 있다.
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/5d5d57ed-ce53-473b-8edb-47940271884e)


## 예시 UML 

![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/7b0fb65b-5398-4297-a785-c17b80b88219)
- 이메일을 보내는 시스템이며, 서브시스템으로 Mailer, Email, StationaryFactory 등등이 있으며 이들은 강하게 결합(tightly coupled)되어 있다.
- 클라이언트가 이메일을 보내는 작업을 진행할 때마다 서브시스템이 바로 작업을 진행하는 것이 아니라, Facade 클래스인 EmailFacade와만 접촉하여 각각의 기능을 사용한다.

## 코드
- 퍼사드 패턴 구현은 매우 간단하다. 클라이언트 코드에 있는 서브시스템 작업들을 퍼사드 클래스로 옮겨주면 끝 !
- 클라이언트 코드는 서브 시스템을 직접 사용하지 않고 퍼사드 클래스를 통해 사용한다.

- **클라이언트 코드**
```java
public class Client {

	public static void main(String[] args) {
		Order order = new Order("101", 99.99);
                //퍼사드 클래스를 사용하여 이메일을 보내는 작업을 진행
                EmailFacade facade = new EmailFacade();
		boolean result = facade.sendOrderEmail(order);
		
		System.out.println("Order Email "+ (result?"sent!":"NOT sent..."));
		
	}
	
//     기존에 구현되어있던 이메일을 보내는 메소드.
//     퍼사드 클래스로 전부 옮김 
//	private static boolean sendOrderEmailWithoutFacade(Order order) {
//		Template template = TemplateFactory.createTemplateFor(TemplateType.Email);
//		Stationary stationary = StationaryFactory.createStationary();
//		Email email = Email.getBuilder()
//					  .withTemplate(template)
//					  .withStationary(stationary)
//					  .forObject(order)
//					  .build();
//		Mailer mailer = Mailer.getMailer();
//		return mailer.send(email);
//	}
	
}

```

- **퍼사드 클래스**
```java
public class EmailFacade {

	//기존 클라이언트 코드에 있던 코드
	public boolean sendOrderEmail(Order order) {
		Template template = TemplateFactory.createTemplateFor(TemplateType.Email);
		Stationary stationary = StationaryFactory.createStationary();
		Email email = Email.getBuilder()
					  .withTemplate(template)
					  .withStationary(stationary)
					  .forObject(order)
					  .build();
		Mailer mailer = Mailer.getMailer();
		return mailer.send(email);
	}
}
```
- 기존 클라이언트코드에 구현되어있던 작업들을 모두 EmailFacade로 옮겼음 


## 퍼사드 패턴 구현시 고려사항 
- 퍼사드 패턴의 주목적은 클라이언트가 서브시스템을 더 이상 복잡하게 사용하지 않도록 결합도를 느슨하게 하는 것에 있다.
- 퍼사드 클래스로 인터페이스나 추상클래스를 사용할 수 있다 (예시에서는 구상클래스로 사용했음)
- 레가시 코드가 아닌 새로 구상한 시스템에 퍼사드 패턴이 필요하다는 것은 설계를 처음부터 다시 확인해야함을 의미한다. (설계가 처음부터 제대로 되어있는 시스템은 퍼사드 패턴이 필요없는 경우가 많다)
- 무분별하게 사용되는 경우, 기존에 구상된 디자인을 숨겨버릴 수 있기 때문에 주의해서 사용해야한다. 

## 결론 
- 서브시스템이 다수의 클라스&인터페이스로 구현되어있다면 퍼사드 패턴을 고려한다.
- 퍼사드 패턴은 보통 새 시스템이 레거시 시스템과 통신하기 위해 사용한다. 예를 들어 어떤 기업의 시스템은 클라스끼리 매우 강하게 결합되있고 코드도 2백만 줄이나 되며 리펙토링을 한번도 하지 않은 경우라고 치자. 그럼에도 이 시스템에는 중요한 기능이 포함되어있기 때문에 새로 만드는 시스템도 이 레거시 시스템에 의존할 수 밖에 없다. 이런 경우, 퍼사드 패턴을 사용하여 복잡한 레거시 코드에 대한 좀더 단순한 뷰(view)를 제공한다. 새 시스템은 퍼사드 객체와만 통신하고, 이 퍼사드 객체가 레거시 코드와 관련된 복잡한 로직을 대신한다.

### 코드
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/structural/facade">Code</a>

<hr>

# FlyweightPattern

## Flyweight Pattern 이란?
- Flyweight은 종합격투기에서 쓰는 용어인데, 57kg 이하의 가벼운 체급을 나타낸다. 이 뜻 그대로 디자인패턴에서는 **메모리 사용을 작게** 만들어주는 패턴을 말한다. 
- 반복되는 객체를 캐시에 저장해두고 필요할 때만 일시적으로 사용하여 메모리를 줄이는 기법이다.
- 예를들어 FPS 게임에서 총을 쏠 떄 나오는 총알을 생각하면된다. 총알을 만약 각각 하나의 객체로 모두 독립적으로 생성한다면, 메모리를 지나치게 사용해서 성능이 나빠진다. 사실 총알은 위치만 다를 뿐 가지고 있는 특징들은 모두 같다. 총알의 색, 크기 등은 모두 같은데 이를 총알 객체가 독립적으로 가지고 있을 필요는 없다. 이때 플라이웨이트 패턴을 사용하여 메모리를 절약할 수 있다.
- 위의 예시의 총알 객체의 경우 위치, 속도는 각각 총알이 가지고 있는 **고유한/유니크한 정보**이다. 이러한 정보를 내적속성(intrinsic state)라고 부르며, 이러한 속성들은 총알 자신만이 수정할 수 있다.
- 총알의 색, 크기와 같은 외부의 공통된 정보를 외적속성(extrinsic state)라고 하며, 이는 클라이언트의 코드로 명령을 받아 수행되는 특성이다. 
- intrinsic state(총알의 속도, 위치)는 각각의 총알 객체가 스스로 수정할 수 있지만, extrinsic state(총알의 색깔, 크기)는 총알 공장에서 만들어주는 대로 계속 유지해야하는 정보이다. 
  
## UML
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/d5745f5e-d25b-4940-a226-e32f671dc7cc)
-  **Client** : Flyweight Pattern을 이용하는 클라이언트
-  **FlyweightFactory** : Flyweight 객체를 생성하고 공유하는 클래스
-  **Flyweight** 인터페이스 : extrinsic state(공통된 정보)를 가지고 있으며 작업을 실행한다.
-  **Flyweight1** 구상클래스 : Flyweight 인터페이스를 구현하고, intrinsic state(고유한 정보)를 저장한다.

## 예시 
- 에러메시지를 출력하는 시스템을 Flyweight Pattern으로 설계

- **Flyweight 인터페이스**
```java
public interface ErrorMessage{
	//외적속성(extrinsic state). 그냥 에러메시지 출력!
	String getText(String code);
}
```
- 에러메시지를 출력하는 것 자체는 고유한 특성을 가지고 있지 않음
- 아직 구체적이진 않으며, 추후 클라이언트 코드로부터 명령을 받아 구체적인 속성(구체적으로 어떤 에러메시지인지)이 될것임
  
- **Flyweight 구상클래스**
```java
public class SystemErorrMessage implements ErrorMessage{
      //내적속성(intrinsic state), 유니크한 정보
      private String messageTemplate;

      private String helpUrlBase;

      public SystemErrorMessage(){
           messageTemplate = "Application encounteredError";
           helpUrlBase = "http://///google.com//q===";
      }

    //외적속성+내적속성을 합하여 클라이언트코드가 사용할 수 있음 
     @Override
     public String getText(String code){
        return messageTemplate + helpUrlBase + code; 
     }

}
```
- Flyweight 인터페이스를 구현한 Flyweight 구상클래스이다. 
- 에러메시지에 대한 더 구체적인/유니크한 속성이 정의되어있음 (intrinsic state)
- getText() 메소드를 오버라이딩하여, extrinsic state와 intrinsic state를 결합하여 사용하도록 함

- **Flyweight Factory** 클래스
```java
public class ErrorMessageFactory{

     //Flyweight 인스턴스를 얻기 위한 키(key)로 작용
      public enum ErrorType{ UserError,SystemError }

     //Eager Singleton으로 클래스 로드시 즉시 인스턴스를 생성하고 ,
     //해당 인스턴스를 전역적으로 제공한다(static)
     //getInstance() 메소드로만 인스턴스 접근가능 
     private static final ErrorMessageFactory FACTORY = new ErrorMessageFactory();

     public static ErrorMessageFactory getInstance(){
        return FACTORY;
    }


    //키에 따른 에러메시지 유형 
    private Map<ErrorType,SystemErrorMessage> errorMessages = new HashMap<>();

    private ErrorMessageFactory(){
        errorMessages.put(ErrorType.UserError,
                             new SystemErrorMessage("User Error", "google.com/~");
         errorMessages.put(ErrorType.SystemError,
                              new SystemErrorMessage("System Error", 'google.com/~");
    }

   //키에 따른 에러메시지 객체 반환 
   public SystemErrorMessage getError(ErrorType type){
      return errorMessages.get(type);
  }

}
```
- Flyweight 객체를 얻기위한 팩토리클래스이다. 
- enum ErrorType은 UserError와 SystemError를 정의하였는데 이는 에러메시지의 구체적인 내용, 즉 내적속성(Intrinsic State)를 나타낸다.
- 해당 enum을 기준으로 ErrorMessageFactory 클래스는 서로 다른 내적속성을 가진 SystemErrorMessage 객체를 생성하여 관리한다.
- ErrorMEssageFactory클래스는 싱글톤으로 구성되어있는데, private static final ErrorMessageFactory FACTORY = new ErrorMEssageFactory()를 보면 인스턴스를 클래스 내부에서 미리 생성한다. 이렇게 인스턴스를 설정해두면 어플리케이션이 실행될 때 단 하나의 인스턴스만 생성되며, 이후에는 getInstance() 메소드를 통해서만 인스턴스에 접근이 가능하다.
- 싱글톤으로 구성된 ErrorMessageFactory는 동일한 에러 유형에 대해서는 항상 같은 인스턴스를 반환하여 중복을 피하고, 메모리 절약을 실현한다.
- 각각의 에러메시지들은 HashMap에 저장해두고, 에러메시지 유형에 따른 객체를 생성해준다.


- **Client**
```java
public class Client{
	public static void main(String[] args){

		SystemErrorMessage msg1 = ErrorMessageFactory.getInstance().getError(ErrorType.UserError);
	}
}
```
- ErrorMessageFactory는 싱글톤으로 구성되어있기 때문에 클래스 로드시 인스턴스가 바로 생성되며 getInstance()를 통해서만 인스턴스에 접근할 수 있다. 따라서 ErrorMessageFactory.getInstance()를 이용, 미리 생성되있는 인스턴스에 접근하고 에러메시지 유형(ErrorType.UserError) 키값을 전달하여 특정한 에러메시지 객체를 얻는다.


## 결론
- Flyweight pattern은 반복되는 객체를 캐시에 저장시키고(싱글톤을 이용) 필요할 때만 일시적으로 사용해서 메모리 사용을 줄임
- 자바에서는 String Pool이 플라이웨이트 패턴으로 되있고, 게임 개발에서 많이 쓰는 디자인패턴
- 아직 제대로 감이 잡히지 않아서.. 실제 프로젝트시 연동해봐야겠다

## 코드
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/structural/flyweight">Code</a>

<hr>

# ProxyPattern

## 프록시 패턴이란?
- Proxy는 "대리"의 의미로 디자인패턴에서는 **대리인처럼 대상객체를 대신하여 로직을 제어**하는 구조패턴(Structural Pattern)을 말한다.
- 프록시 패턴의 종류는 크게 static proxy(정적 프록시)와 dynamic proxy(동적 프록시)로 나누어진다
  1. **Static Proxy(정적 프록시)** : 프록시가 대상객체를 직접 참조한다. 컴파일시 프록시가 대상객체를 참조하기 때문에 런타임 중에 변경할 수 없다.
  2. **Dynamic Proxy(동적 프록시)** : 프록시와  대상객체 사이에 중간계층을 형성하기 때문에 런타임시 프록시객체를 동적으로 생성한다.
 
## 프록시패턴 UML
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/5f7f4ce4-184b-4e8b-9661-1d76b81fe2be)

- Subject : 클라이언트가 사용하는 인터페이스  
- RealSubject : Subject를 구현한 구상클래스. 대상객체라고 부른다.
- **Proxy**
  1. 대상객체(RealSubject)의 참조값을 가지고, 클라이언트가 필요로하는 로직들을 제공한다.
  2. 대상객체와는 다른 클래스이기 때문에 별도의 로직을 추가할 수 있다.
  3. 클라이언트가 대상객체를 원하기 전까지 프록시로 대신하여 제공할 수 있다.
     
## 구현예시 

### UML 
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/c9132b86-e5b3-489e-a39f-cdf6886c031d)
- Image : 클라이언트가 사용하는 인터페이스
- BitmapImage : Image를 구현하는 구상클래스. 실제 데이터가 있는 대상객체이다.
- ImageProxy : 대상객체의 참조값을 가지고 로직들을 제공하는 프록시 객체
- ImageFactory : 클라이언트가 사용하는 팩토리. 프록시 객체의 인스턴스를 만들어줌

# 정적 프록시(Static Proxy)

- BitmapImage : Image인터페이스를 구현한 구상클래스 . 클라이언트가 필요로 하는 데이터가 있다.
```java
public class BitmapImage implements Image {
	
	private Point2D location;
	private String name;
	
	public BitmapImage(String filename) {
		System.out.println("Loaded from disk:"+filename);
		name = filename;
	}
 //(생략)
```

- ImageProxy : proxy 객체이며 대상객체를 필요할 때만 쓰도록 해줌 
```java
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

    //(생략)

}

```
- setLocation 메소드를 보면, 이미지가 null이 아닐때만 실제 대상객체에 값을 전달하고 있다. 즉, 필요할때만 대상객체를 부르고, 불필요한 경우 프록시 필드에 저장해둔다.
  
- 프록시 인스턴스를 전달하는 ImageFactory. 클라이언트가 사용하게 됨
```java
public class ImageFactory{
	public static Image getImage(String name){
		return new ImageProxy(name);
	}
}
```
- 실제 대상객체인 BitmapImage를 바로 전달하지 않고 팩토리를 이욯하여 프록시 인스턴스로 대신 전달한다.

# 동적 프록시(Dynamic Proxy)
- 동적프록시는 자바 리플렉션(Java Reflection)을 기반으로 동작하는 기술 중의 하나이다.
- 자바 리플렉션은 실행 중인 자바 프로그램 내에서 클래스의 정보를 조사하고, 클래스의 메소드,필드,생성자 등을 동적으로 검사하고 사용하는 기술이다. 
- 동적프록시는 InvocationHandler 인터페이스에 있는 invoke메소드의 정의를 통해 구현될 수 있다.
- invoke 메소드의 시그니처는 다음과 같다.
```java
public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
```
- **proxy** : 동적 프록시 객체
- **method** : 현재 호출된 객체
- **args** : 메소드에 전달된 인수배열
 
- invoke메소드는 다음과 같이 사용될 수 있다.
```java
public class ImageInvocationHandler implements InvocationHandler{
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
		//Image 클래스의 setLocation 메소드를 찾고,
               // 해당 메소드를 setLocationMethod에 할당 
		Method setLocationMethod = Image.class.getMethod("setLocation", new Class[]{Point2D.class});
		//현재 호출되는 메소드가 setLocation일때
                //Point2d 객체를 반환하여 출력한다.
		if(setLocationMethod.equals(method)){
			Point2D point2d = (Point2D)args[0];
                        System.out.println(point2d);
		}
		return null;
	}
}
```

- 위의 코드에서는 InvocationHandler 인터페이스를 구현한다. InvocationHandler는 Relection api이며, 단일메소드인 invoke 메소드를 구현하여 동적으로 메소드를 가로채고 처리한다.

- 프록시객체를 생성하는 팩토리 클래스는 relection API인 Proxy 클래스를 통해서 구현할 수 있다.
- Proxy 클래스의 newProxyInstance 메소드를 사용하면 invoke 메소드가 들어있는 ImageInvocationHandler 클래스를 불러서 최종적으로 동적프록시를 가능하게 해준다.
```java
public class ImageFactory{
	public static Image getImage(){
		return (Image)Proxy.newProxyInstance(ImageFactory.getClassLoader(), new Class[] {Image.class}, new ImageInvocationHandler());
	}
}
```
- **Proxy.newProxyInstance** : 동적 프록시 생성 메소드,  Image 인터페이스를 구현한 객체가 반환된다.
- **ImageFactory.getClassLoader()** : ImageFactory의 클래스 로더를 사용하여 프록시클래스의 로딩에 사용될 클래스 로더를 지정한다.
- **new Class[]{Image.class}** : 프록시 객체가 구현할 인터페이스의 배열이다.
- **new ImageInvocationHandler()** : 프록시 객체의 메소드를 호출할 때 호출되는 핸들러 객체를 지정한다.

- 클라이언트코드 
```java
public class Client{
	public static void main(String[] args){
		Image img = ImageFactory.getImage();
 		img.setLocation(new Pont2D(-10,0));
	}
}
```
- 클라이언트 코드에서는 단순히 setLocation 메소드만 호출하였으나, 동적프록시 객체의 의해 invoke 메소드가 호출되어 Point2D 객체가 프린트 될것이다. 


## 결론
프록시 패턴은 제어 흐름을 조정하기 위한 목적으로 중간에 계층을 도입하여 여러 기능을 추가하거나 제어하면서 실제 서비스를 대신 수행하는 패턴이다. 

## 코드
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/structural/proxy">Code</a>

<hr>

# ChainOfResponsibility
 
## 책임 연쇄 패턴(Chain of Responsibility) 패턴이란? 
- 요청(request)을 받는 코드가 스스로 요청을 처리할 수 없을 때, Successor라고 불리우는 후속객체가 요청을 처리하도록 함으로서 코드간의 결합도를 낮춰준다.
- 객체들이 서로 연결되도록(chain) 설계하여 각각의 객체는 자신의 바로 다음 객체의 참조값을 가지고 있다.
- 따라서 객체가 request를 처리하지 못한다면, Successor라고 불리우는 후속객체가 request를 처리하도록 한다.

## UML
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/e13de03b-31f7-4e1e-bfeb-e650131444c2)
- **Handler** : 요청을 처리하기 위한 객체들이 가질 인터페이스
- **ConcreteHandler1,2** : 요청의 종류에 따라 자신이 처리할 수 있는 부분을 구현한 객체
- **Client** : request 요청객체

## 책임연쇄패턴 구현방법
### Abstract Handler
- request를 처리하는 메소드를 정의하며, 처리불가시 후속객체에 접근할 수 있는 메소드를 정의

### ConcreteHandler 
- Handler 인터페이스의 구현제이며, request를 처리할 수 있는지 체크한다.
- 만약 request를 처리할 수 없다면, 후속객체에 request를 전달한다.

## Implementation
- 다음은 휴가를 제출하는 프로그램의 UML이다. 휴가 승인은 Lead, Manager, Director에 따라 승인이 이루어진다. 
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/c5bbb36f-6a21-4cb7-9335-2ee7e73b58a4)
- LeaveApprover : Handler 인터페이스로서 processRequest 메소드가 사원의 휴가제출(LeaveApplication)을 전달받는다.
- Employee : LeaveApprover의 구상클래스, processRequest 메소드에 Lead, Manager, Director를 차례대로 전달하여 승인이 완료되지 않았다면 후속객체가 처리하도록 한다.
  
- **Employee**
```java
public abstract class Employee implements LeaveApprover{

	private String role;
        //후속객체(successor)
	private LeaveApprover successor;
	
	public Employee(String role,LeaveApprover successor) {
		this.role = role;
		this.successor = successor;
	}
	
	//후속객체에서 처리가 안됬을 경우, 다음 후속객체에게 LeaveApplication을 전달
	@Override
	public void processLeaveApplication(LeaveApplication application) {
		if(!processRequest(application) && successor != null) {
			successor.processLeaveApplication(application);
		}
	}
	
	protected abstract boolean processRequest(LeaveApplication application);
	

	@Override
	public String getApproverRole() {
		return role;
	}
	
}

```
- processLeaveApplication 메소드의 조건문으로 processRequest 반환값을 주어서 값이 거짓인 경우, 후속객체가 leaveApplication을 처리하도록 한다.
- processRequest 메소드는 abstract으로 설정하여 후속객체가 반드시 정의해야한다. 반환값인 참/거짓을 통해 후속객체가 처리할 것인지 아닌지를 설정한다.

  
- **Director**
```java
  public class Director extends Employee {

	public Director(LeaveApprover nextApprover) {
		super("Director", nextApprover);
	}
	
	@Override
	protected boolean processRequest(LeaveApplication application) {
		if(application.getType() == application.getType().PTO) {
			application.approve(getApproverRole());
			return true;
		}
		return false;
	}
	
}
```

- 휴가타입이 PTO인 경우에만 휴가를 승인하도록 한다. 



- **ProjectLead**
```java
public class ProjectLead extends Employee{
	
	public ProjectLead(LeaveApprover successor) {
		super("Project Lead",successor);
	}

	@Override
	protected boolean processRequest(LeaveApplication application) {
		if(application.getType() == LeaveApplication.Type.Sick) {
			if(application.getNoOfDays() <=2 ) {
				application.approve(getApproverRole());
				return true;
			}
		}
		return false;
	}

}
```

- ProjectLead는 휴가 타입이 Sick이고 이틀 미만의 경우만 true를 반환한다.
- 해당 기준에 만족하지 않으면, ProjectLead의 ProcessRequest는 false를 반환하고, 후속객체가 있다면  Handler인터페이스가 후속객체로 요청을 전달한다.

- **Client** 
```java
public class Client {

	public static void main(String[] args) {
	   LeaveApplication application = LeaveApplication.getBuilder().withType(Type.Sick)
			   						  .from(LocalDate.now()).to(LocalDate.of(2024,6,5))
			   						  .build();
	   
	   LeaveApprover approver = createChain();
	   approver.processLeaveApplication(application);
	   System.out.println(application);
	}
	
	private static LeaveApprover createChain() {
		Director director = new Director(null);
		Manager manager = new Manager(director);
		ProjectLead lead = new ProjectLead(manager);
		
		return lead;
	}

	
}

```
- createChain 메소드에서 Handler 구상객체끼리 체인을 만들었다. 
- Manager가 휴가 거절을 하면, Director에게 요청이 가고, ProjectLead가 휴가 거절을 하면, Manger에게 요청이 간다.


  ## 예시
- Servlet Filter에서 쓰이는 디자인패턴이다. 각각의 filter는 request를 처리할 수 있으며, request 처리 후 체인을 통해 후속객체에게 넘겨준다.
- javax.servlet.Filter
```java
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
```
- chain 파라메터는 서블릿 필터 체인의 다음 필터로 요청을 전달하는데 사용된다. 
- 만약 다음 필터가 없는 경우, 체인의 끝에 도달했음을 의미하며, 이는 서블릿으로 요청이 전달되어 마무리된다.
  
## 결론
- 클라이언트로부터 요청을 하나의 객체가 처리하는 것이 아닌, 여러개의 처리 객체로 나누고, 이들을 사슬(chain)처럼 연결하여 처리한다.
- 이러한 처리 객체를 Handler라고 부르며, 요청을 받아들이면 요청을 처리할 수 있는지, 없으면 다음 Handler 객체에 처리에 대한 책임을 전달한다.

### Code
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/behavioral/chainRes">Code</a>

<hr>

# CommandPattern

## Command Pattern ?  
- request를 객체로 취급하여 command라는 객체에 캡슐화시킨다.
- 실행 메소드가 command 객체안에 있기 때문에 실행을 늦출 수 있고, 다른 코드에도 쉽게 전달할 수 있다.



## UML
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/347b779f-2ef4-41f8-911a-04fd11d07d7a)

- Command : 실행될 기능에 대한 인터페이스. 실행되는 기능을 execute 메소드로 선언함
- Concrete Command :  실제 명령 클래스. 작업을 수행하는데 필요한 모든 정보를 포함하고 Receiver객체와 연결된다. 
- Invoker : 기능의 실행을 요청하는 호출자 클래스
- Receiver : ConcreteCommand에서 execute 메소드를 구현할 떄 필요한 클래스, ConcreteCommand의 기능을 실행하기 위해 사용하는 수신자 클래스 
- Client : 커맨드 객체 인스턴스를 생성하고, receiver를 설정한다.


### Code
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/behavioral/command">Code</a>


<hr>

# Interpreter 

## Interpreter Pattern 이란?
- 특정 언어의 문법을 해석(Interprete)하고 실행해주는 역할을 한다.
- 클래스들은 특정 언어의 문법을 표현하는 역할을 하고, 해당 언어를 해석하는데 도움을 준다.

## UML 
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/e3952e9c-0147-492b-9d8d-2d9ae8e005c7)
- 추상적인 표현을 client가 구문을 이용하여 구체적인 표현들로 정의한다.
- AbstractExpression 인터페이스는 context를 받아 해당 문장을 번역하는 기능을 제공한다.
- TerminalExpression은 더 이상 분해되지 않는 최소 단위의 표현식이며, NonterminalExpression은 하나 이상의 터미널 또는 다른 비터미널 방식으로 표현된다.
- Context는 AbstractExpression이 필요로하는 상태값을 가지고 있는 객체이다.


## Example 
예시코드는 사용자의 권한에 따라 Report에 접근권한을 부여하는 프로그램이다. <br>
Report에 정의된 권한과 User의 권한을 비교하여 승인 여부를 결정한다. <br>

- 사용자 객체이며, 권한리스트를 가지게 된다.

```java
public class User{

	private List<String> permissions;

	//생략

	public User(String username, String... permissions){
		this.permissions = new ArrayList<>();
		Stream.of(permissions).forEach(e -> this.permissions.add(e.toLowerCase()));
	}
}
```


- Non Terminal Expression 객체이다.
- 예를들어 사용자는 "NOT ADMIN" 과 "FINANCE_USER AND ADMIN"과 같은 Non terminal expression을 전달할 수 있다. 이때 각각의 단어 사이에 NOT이나 AND를 조합하여 해석되도록 만들어야한다. 
<br>
- 아래는 사용권한에 And가 추가될 수 있는지를 검사하는 AndExpression 클래스이다. 이는 builder패턴에서 사용된다.  

```java
public class AndExpression implements PermissionExpression{

	private PermissionExpression expression1;
	private PermissionExpression expression2;
	
	public AndExpression(PermissionExpression expression1, PermissionExpression expression2) {
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	
	@Override
	public boolean interpret(User user) {
		return expression1.interpret(user) && expression2.interpret(user);
	}

	@Override
	public String toString() {
		return expression1 + "And " + expression2;
	}
	
}
```

- ExpressionBuilder가 반환하는 것은 추상구문트리(abstract syntax tree,AST)이며, 이는 Stack 자료구조로 되어있다.
- 추상구문트리(AST)란 프로그래밍 언어의 소스 코드를 구문적으로 분석하여 그 구조를 나타내는 트리 자료 구조이다.
**parse()** : StringTokenizer는 빈칸을 기준으로 token이 생성되기 때문에 Report객체에  FINANCE_USER AND ADMIN이라고 입력될시 세개의 토큰이 생성된다<br>
token에 non terminal expression이 포함되면, 해당 표현식을 stack에 저장한다.<br>
**buildExpression()** : 저장된 표현식을 stack에서 제거하고, 표현식에 맞는 Non Terminal Expression 객체를 호출한다.<br>
그 후 , permission 리스트에 사용될 non terminal expression 객체를 추가한다. 


```java

public class ExpressionBuilder{

	private Stack<PermissionExpression> permissions = new Stack<>();

	private Stack<String> operators = new Stack<>();

	public PermissionExpression build(Report report){
		parse(report.getPermission());
		buildExpression();
		if (permissions.size() > 1 || !operators.isEmpty()) {
			System.out.println("ERROR!");
		}
		return permissions.pop();
	}

	private void parse(String permission){
		StringTokenizer tokenizer = new StringTokenizer(permission.toLowerCase());
		while(tokenizer.hasMoreTokens()){
			String token;
			switch((token = tokenizer.nextToken())){
				case "and" :
					operators.push("and");
					break;
				case "not" :
					operators.push("not");
					break;
				case "or" :
					operators.push("or");
					break;
				default :
					permissions.push(new Permission(token));
					break;
			}
		}
	}

	
	private void buildExpressions() {
		while (!operators.isEmpty()) {
			String operator = operators.pop();
			PermissionExpression perm1;
			PermissionExpression perm2;
			PermissionExpression exp;
			switch (operator) {
			case "not":
				perm1 = permissions.pop();
				exp = new NotExpression(perm1);
				break;
			case "and":
				perm1 = permissions.pop();
				perm2 = permissions.pop();
				exp = new AndExpression(perm1, perm2);
				break;
			case "or":
				perm1 = permissions.pop();
				perm2 = permissions.pop();
				exp = new OrExpression(perm1, perm2);
				break;
			default:
				throw new IllegalArgumentException("Unknown operator:" + operator);
			}
			permissions.push(exp);
		}

}

```

**Client** 
- 빌더패턴을 이용하여 Report의 abstract syntax tree를 반환한다.
```java
Report report1 = new Report("Cashflow","NOT ADMIN");
ExpressionBuilder builder = new ExpressionBuilder();

//abstract syntax tree를 생성
PermissionExpression exp = builder.build(report1);
User u1 = new User("Dave","FINANCE_USER","ADMIN");

exp.interpret(u1); //false
```

## 결론
- 인터프리터 패턴은 문법 규칙을 나타내는 클래스를 정의하고, 이를 해석하여 실행할 수 있는 클래스를 만들어 구성한다
- 단점으로는 문법의 수가 많아질 떄 이를 정의하는 클래스도 동시에 많아지게 된다. 따라서 복잡한 문법보다는 간단한 문법 규칙을 관리하는 것에 사용된다.

<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/behavioral/interpreter">code</code>


<hr>

# Mediator 

## Mediator Pattern
- 모든 클래스 간의 복잡한 로직(상호작용)을 캡슐화하여 하나의 클래스에 위임하여 처리하는 패턴
- M:N의 관계에서 M:1의 관계로 복잡도를 떨어뜨려 유지 보수 및 재사용의 확장성에 유리한 패턴
- 커뮤니케이션이 복잡한 경우, 이를 해결해주어 커플링을 약화시켜주는 패턴이다.


## UML 

![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/aa23c815-f4c5-4311-8a87-fec0b17f081c)

- Mediator : Colleague 객체 간의 상호참조를 위한 인터페이스. 클라이언트 상태 등록, 실행 등의 메소드 정의
- Colleague : 다른 Colleague 객체들과의 상호작용을 위한 인터페이스
- ConcreteMediator : Mediator 구현클래스 , Colleague간의 상호참조를 조정
- ConcreteColleage : Colleague 구현 클래스, Mediator를 통해 다른 Colleague와의 상호참조

## Mediator 예시 

- JavaUI를 책임지는 JavaFX를 이용해서 중재자패턴을 만들었다. 클라이언트는 Text,Label 등등 여러개의 UI 컴포넌트들을 변경하게 되는데, 상태를 변경하는 객체를 부를 때마다 각각의 객체들은 기존 상태값을 서로 전달받아야한다. 이렇게 변경할 때마다 컴포넌트끼리 현재 상태를 전달한다면 결합도가 높아진다. 이를 완화하기 위해 Mediator 객체를 만들어서 컴포넌트의 상태가 변경되면 Mediator 객체에게 변경된 값을 전달하고, Mediator 객체가 각각의 UI들에게 현재 객체의 상태를 전달한다.
  

- 아래는 Mediator 객체인 UIMediator이다. Mediator 객체는 상태값을 변경하는 객체들을 가지고 있어야하기 때문에 List로 담아두었고, 상태값이 변경되면 valueChagned 메소드를 이용하여 List에는 변경된 상태값으로 저장하고 Colleague역할을 하는 UIControl에 변경된 상태값을 전달한다. UIControl는 각각의 변경객체들의 인터페이스이기 떄문에, 어떤 객체든지 현재의 상태값을 알 수 있다. 즉 서로를 참조하지 않고도 UIControl을 이용하여 현재의 상태값을 알 수 있다.
  
```java
public class UIMediator {
	List<UIControl> colleagues = new ArrayList<>();

	public void register(UIControl control){
		colleagues.add(control);
	}

	public void valueChanged(UIControl control){
		colleagues,stream().filter(c-> c!= control).forEach(c-> c.controlChanged(control));
	}
}
```

## Mediator Pattern VS Observer Pattern 
- **Mediator**
- 객체간의 복잡한 로직을 캡슐화 하는 것이 목적이다.
- 각각의 객체 상태 변화를 모든 객체들이 알 수 있다. 
- **Observer**
- 객체끼리 일대다 관계의 결합도를 만드는 것에 있다(publish-subscribe 패턴)
- 서로 상태를 공유하는 것이 목적이 아님 ! 개별적으로 객체 상태가 변화되도 다른 객체는 모름

## 단점
- 하나의 제어 객체만 있기 때문에, 상호작용해야하는 객체가 많아진다면 복잡도가 올라간다.

### code 
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/behavioral/mediator">Code</a>


<hr>

# Iterator

## Iterator Pattern 이란?

- 컬렉션 구현 방법을 노출시키지 않으면서도 그 집합체 안에 들어있는 모든 항목에 접근할 수 있는 방법을 제공한다.
- 모든 항목에 일일이 접근하는 작업을 컬렉션 객체가 아닌 반복자 객체에서 맡게 된다.
- 컬렉션 객체 안에 들어있는 모든 항목에 접근하는 방식이 통일되어 있으면 어떤 종류의 집합체에 대해서도 사용할 수 있는 다형적인 코드를 만들 수 있다.

## UML 
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/eb97a0c1-c42c-4547-8335-aea14d1dfc5b)
- Collection 인터페이스는 List인터페이스에 의해 확장된다.
- List인터페이스는 iterator()라는 팩토리 메소드를 가지고 있다. iterator 팩토리 메소드는 iterator 인터페이스를 반환한다.
- List인터페이스에 구현되어있는 iterator 인스턴스들은 ListIterator로 불리운다.
- ListIterator 인스턴스는 iterator 인터페이스의 구현체이며 Collection API에서 다양한 객체들을 어떻게 반복해야하는지를 알고 있다. 구성(Composition)과정에서 인터페이스를 정의한다.

## 예시 
java.util.Scanner 로 iterator 패턴으로 되어있다. InputStream은 stream을 통해 내부의 모든 바이트 항목에 접근할 수 있다.

```java
Scanner sc = new Scanner(System.in);
// read an integer from input stream
int i  = sc.nextInt();
```

### code 

<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/behavioral/iterator">code</a>

<hr>

# Memento

## Memento Pattern 이란?
- 캡슐화를 유지하면서 객체 내부 상태를 외부에 저장하는 방법
- 객체 상태를 외부에 저장했다가 해당 상태로 다시 복귀할 수 있다.

## UML 

![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/b9378e12-8d7b-4081-80f4-1fa2248d4426)

**Originator** : 클라이언트에게 request를 전달받으면, Originator가 memento 인스턴스를 만들어 현재 상태를 저장하거나 상태값을 복구한다.
- 상태를 저장하라는 request인 경우 => Originator는 Memento 객체를 부르고, 현재 상태를 저장시킨다.
- 상태를 복구하려는 request인 경우 => Originator는 원하는 상태를 가지고 있는 Memento 호출하여 상태를 복구시킴
**Caretaker** : 클라이언트와 Originator의 중개자 역할이다. Memento 객체들을 관리함
- 상태를 저장하라는 request인 경우 => Originator로 부터 Memento를 전달받아 Memento에 상태를 저장한다.
- 상태를 복구하려는 request인 경우 => Originator에게 복구를 위한 Memento를 전달한다.
  

## Memento VS Command 
- Memento는 객체의 state(상태값을 저장하고) Command는 객체를 캡슐화(Encapsulation)시켜서 요청을 저장한다
- Memento에서는 state가 originator를 제외하고 모두에게 감추어져있지만 Command에서는 state를 읽을 수 있다.
  (실제로 Memento 패턴에서는 getter를 쓰면 안되지만 command에서는 제한이 없다) 

### Code
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/behavioral/memento">code</a>


# Observer

## Observer Pattern 이란? 
- 객체의 상태 변화를 관찰하는 관찰자들(Observer) 목록을 이벤트를 발생시키는 이벤트 객체에 등록하여, 이벤트가 발생할 때마다 메소드를 통해 관찰자들(Observer) 객체들에게 알려주어 그에 맞게 행위를 하는 디자인 패턴이다.
- publisher-subscriber 패턴이라고도 한다.
- one-to-many
  
## UML 
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/5f091491-43fd-4c4a-a7f6-ee2288cdbe52)
Observer라는 인터페이스에는 notify()라는 함수가 정의되어있다. notify()는 이벤트가 발생했을 때 처리할 행위를 정의한다.
Subject(이벤트 발생객체)에는 observerCollection이 존재하며, 여기에 Observer 객체들이 저장된다. notifyObserver()는 Observer 객체들의 목록들의 notify()를 실행해 이벤트 발생에 따른 처리를 각 Observer에게 전달한다. registerObserver(observer)는 observer를 등록, unregisterObserver(observer)는 특정 observer를 리스트에서 제거한다.


### code
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/behavioral/observer">code</a>

<hr>

# State 

## State Pattern이란?
- 객체의 내부 상태가 바뀔 때마다 객체의 행동을 바꿀 수 있도록 도와주는 패턴이다. 마치 객체의 클래스가 바뀌는 것 처럼 보여줄 수 있다. 
- 객체 상태를 별도 클래스로 캡슐화하여 현재 상태를 나타내는 객체에게 행동을 위임한다.
- 내부 상태가 바뀔 때마다 행동이 달라진다는 사실을 쉽게 알 수 있다.
- 메인 클래스를 수정하지 않고도 상태값을 자유롭게 추가할 수 있다. 

## UML 
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/c51f0070-f016-429e-8223-c45fecdf0424)

- Document (=Context) : 모든 행동을 자체적으로 구현하는 대신, 현재 상태를 나타내는 상태 객체 하나를 참조해 그 상태 객체에게 행동을 위임한다.
- State : 모든 구상 상태 클래스들이 구현해야하는 공통 인터페이스
- Draft(=ConcreateState) : Document로 부터 전달된 요청을 상태별로 구현하여 처리한다.


## 예시
Order에 관련된 상태객체를 따로 빼서 설계했음. 메인 클래스를 건들지 않고 쉽게 상태값을 추가/삭제 할 수 있다. SRP를 준수했다고 볼 수 있다.
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/behavioral/state">Code </a>

<hr>

# Strategy 

## 전략(Strategy)패턴이란 ?
- 각 케이스에 따라 다른 알고리즘이 사용될 때
- 케이스가 추가될 때마다 코드를 수정하는 것이 복잡해짐 => 케이스에 따란 알고리즘을 객체로 분리

## UML 
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/240080d7-0bb0-43cb-bf17-859b55f39777)
- Strategy : 전략을 추상화시킨 인터페이스
- Concrete Strategy : 각 케이스에 맞는 클래스로 구체적인 알고리즘을 제공
- Context : Strategy를 실행시킴

## Comparator 
java.util.Comparator 인터페이스는 strategy 패턴으로 구현할 수 있다. Comparator는 각각의 구현체는 서로 다른 sorting 알고리즘을 구현할 수 있다. 
객체의 나이(int)와 이름(String)에 따른 sorting을 시현할 때는 서로 데이터타입이 다르기 때문에 아래와 같은 다른 알고리즘으로 구현할 수 있다. 

```java
class SortByAge implements Comparator<User>{
	@Override
	public int compare(User o1,User o2){
		return o1.getAge() - o2.getAge();
	}
}
```

```java
class SortByName implements Comparator<User>{
	@Override
	public int compare(User o1, User o2){
		return o1.getName().compareToIgnoreCase(o2.getName());
	}
}

```



## 예시
OrderPrinter 라는 추상객체가 각각의 알고리즘들을 구현하고 있는 형태로 만들었다. 클라이언트는 사용하고 싶은 알고리즘의 객체를 생성자 인자값으로 넣어 원하는 메소드를 만들 수 있다. 
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/behavioral/strategy">Code </a>

<hr>

# TemplateMethod

## 템플릿 메소드 패턴이란?
슈퍼클래스에서 알고리즘에 대한 메소드의 뼈대(skeleton)만 작성하고, 서브클래스에서 해당 메소드를 상속(inheritance)받아 구체적인 구현을 재정의(overriding)하는 패턴이다. 소프트웨어 개발 원칙 중 DRY(Don't repeat yourself)에 해당한다. 즉 같은 코드를 중복되어 작성하지 말란 원칙이다. 템플릿 메소드 패턴은 동일한 알고리즘을 슈퍼 클래스에서 작성하기에 코드 중복을 최소화한다. 또한, 알고리즘을 바닥부터 다시 생성하지 않고, 서브 클래스를 새로 만들고 필요한 메소드만 재정의하면 됨으로 코드 추가 및 수정이 수월하다. 

# UML 
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/8f67fcd7-9b7a-41e6-b32c-4dfbcb7406a3)


## 예시
OrderPrinter를 abstract 클래스로 설정하고 이를 서브 클래스가 구현하게 했음. 특히 abstract 메소드들은 여러개의 작업으로 나누었기 때문에, 상위클래스를 건들지 않고 메소드를 단계별로 자유롭게 커스터마이징 할 수 있음 
<a href="https://github.com/puddingForever/JavaDesignPattern/tree/main/JavaDesignPattern/src/behavioral/templatemethod">Code </a>

<hr>

# Visitor

## 방문자 패턴이란?
- OOP에서 객체는 그 객체가 하는 행동을 메소드로 가지고 있다. 예를 들어 "나는 상점에 방문했다. 나는 ~를 한다." 즉 '나'라는 객체는 '상점'이라는 객체를 입력받은 후 이 상점에 대해서 뭔가를 한다. 이것이 일반적인 OOP지만, Visitor 패턴에서는 "상점에 내가 방문을 했다. 내가~를 하게 한다" 라고 해석할 수 있다. '상점'이라는 객체가 '나'를 입력받은 후 , '나'라는 객체의 행동을 호출하는 것이다. 즉, 사용자는 방문자 입장이 아니라, 방문 공간의 입장에서 먼저 생각해보게 된다.
- 각각의 클래스들의 데이터 구조에서 처리 기능을 분리하여 별도의 클래스로 구성하는 패턴이다. 이렇게 분리된 처리 기능은 각각의 클래스를 방문(visit)하여 수행한다.
  

- 
## UML 
![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/ff6229b7-1a9c-439a-8f03-df607dffd474)

- 데이터를 저장하는 Element와 알고리즘을 저장하는 Visitor를 분리시킨다. Visitor에 따라 여러 기능을 수행할 수 있다. 


















