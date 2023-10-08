빌더 패턴(Builder Pattern) 
===================================

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


> 인스턴스 생성시 불편한 객체 한개를 더 살펴봅시다. <br> 
> 아래의 Me라는 객체의 인스턴스를 생성하기 위해서는 먼저 Nation이라는 객체와 Bank라는 객체를 생성한 후 , Bank라는 객체를 감싸고 있는 collection 객체도 생성해야만 함. <br>
> 
```java
	class Me{
		public Me(Nation nation,List<Bank> banks,String name){
			//초기화 
		}
	}
```

> 이 모든 과정이 불편하다고 느꼈다면 ..빌더패턴을 사용하면 간단하게 해결할 수 있음 ~! 

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
- build()라는 메소드가 있어야 진정한 빌더인터페이스 ~ StoreDTO는 최종결과물(ClothingStoreDTO)를 구현해주는 인터페이스임 (loose coupling을 위해서 이렇게 씀)
  
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
- Director라고 표신된 곳이 빌더패턴을 부르는 애임. 메소드 체이닝으로 값 셋팅을 해줌.
- main메소드에서 빌더패턴을 사용하면 끝 !! 

### 빌더패턴을 더 쉽게 구현해보자 

- 위의 예시는 클래스,인터페이스를 여러개 나눠서 빌더패턴을 사용했지만, 많은 개발자들은 Builder클라스를 결과물 객체 내부의 static inner class로 만들어서 사용해버림

  








 













  
  


  


















