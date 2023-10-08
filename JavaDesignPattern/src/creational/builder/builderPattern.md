빌더 패턴(Builder Pattern) 
===================================

### 한마디 정리 
- 객체를 바로 생성하는 것이 아닌 **여러 과정을 거쳐 객체가 생성** 되는 특징을 가지고 있음
- 생성자를 여러개로 나누어 객체 구성요소간의 느슨한 결합(loose coupling)을 촉진함
  
### 빌더패턴을 공부하기 전에 ..  

> 질문을 하나 하겠습니다. 아래의 Book클라스는 파라메터를 받는 생성자 하나만 있습니다 <br>
> Book클래스를 이용해 인스턴스를 생성할 때의 불편한 점은 무엇이 있나요?  (두가지) 

```java
class Book{
	public Book(double price,int weight,String author,String clientName){
		//초기화
	}
}
```

> 답변
1. Book클라스는 인스턴스를 생성할때 반드시 생성자에 넣어줄 인자값을 다 셋팅해주어야합니다. <br>
왜냐하면 파라메터가 있는 생성자를 만든다면 기본생성자는 자동으로 생성되지 않기 때문이죠 <br>
new Book(),new Book("hi")로 인스턴스를 생성하는 것은 불가하며 반드시 new Book(double타입 인자값,int타입 인자값,String타입 인자값,String타입 인자값)으로만 인스턴스를 생성해야하는 불편함이 있습니다.
double price 와 int weight만 필요한 경우에도 뒤의 author와 clientName부분은 null값이라도 넣어야 에러가 나지 않습니다.

3. Book클라스의 생성자를 제대로 확인하지 않는다면, **author와 clientName의 인자값을 서로 다르게 셋팅** 해줄 수 있는 위험이 있습니다. <br>
자바에서는 생성자의 이름이 아닌 타입을 검사하기 떄문에 String author와 String clientName의 값이 뒤바뀌어도 컴파일러 에러가 발생하지 않습니다. <br>


> 인스턴스 생성시 불편한(?) 객체 한개를 더 살펴보겠습니다. <br> 
> 아래의 Me라는 객체의 인스턴스를 생성하기 위해서는 먼저 Nation이라는 객체와 Bank라는 객체를 생성한 후 , Bank라는 객체를 감싸고 있는 collection 객체도 생성해야만 합니다. <br>
> 이 모든 과정이 불편하다고 느끼셨다면 .. 이제 빌더패턴을 사용해봅시다 ! 
> 
```java
	class Me{
		public Me(Nation nation,List<Bank> banks,String name){
			//초기화 
		}
	}
```

### 빌더(Builder)란 도대체 무엇인가?
- 인스턴스 생성시 생성자의 형태에 따른 **생성순서의 규칙을 단순화** 해줌 
- 복잡한 객체를 생성하는 클래스를 분리하여 느슨한 결합(loose coupling)을 위해 추상개념을 사용함

#### 빌더패턴 구현순서  

- ![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/6f862b88-f30b-4304-9946-ba67a6bed525)

1.Product (결과물) 
- 내가 만들고자하는 최종 객체

2.Builder 
- Product(결과물)을 만들기위한 **부품들을 제공해주는 인터페이스**
- 인스턴스를 생성하기 위해 생성자의 파라메터 값들을 한번에 셋팅해주는 것이 아니라, 필요값만 개별적으로 셋팅할 수 있도록 해줌! (위 예시의 Book클래스를 보면 weight,price,author,clientName 모두 셋팅해줘야 객체가 생성되는데, 빌더를 이용하면 price만 넣어도 인스턴스가 생성됨)
- 이미 만들어진 결과물 객체를 부르는 메소드도 가질 수 있음
(빌더를 이용하여 Product(결과물)을 만들었다면 해당 결과물을 다시 부를 수 있는 메소드도 가질 수 있음

3.ConcreteBuilder 
- Builder인터페이스의 구현클래스 
- 부품들을 이용해서 최종 결과물을 만드는 곳 !
- 객체를 생성하기 위한 절차를 관리(최종 객체를 만들기위해 필요한 정보를 추적하고 관리하는 역할을 함)

4.Director 
- builder를 이용하여 최종 객체를 생성하는 부분


### 빌더패턴을 이용하여 식료품점(GroceryStoreDTO)과 옷가게(ClothingStoreDTO)들을 만들어보자

- UML 

![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/69778fe6-c30f-46ee-8892-5365337bf37e)


- Product이 표시된 곳은 최종결과물이다. 나는 ClothingStoreDTO와 GroceryStoreDTO를 만들 것임
- loose coupling을 위해 ClothingStoreDTO는 인터페이스를 구현해서 만들도록 설계해봄 (abstract이라고 표시된 곳은 인터페이스를 사용예정)
- StoreDTOBuilder는 인터페이스로 되어있으며, 가게운영에 필요한 부품들을 만들기 위해 간단한 메소드들이 작성되있음
- Client는 메인클라스 ! Director(최종객체를 생성하는 부분)의 역할을 한다. 
- 주황색은 식료품점을 마드는 빌더임. 빌더인터페이스를 이용해서 다른 타입의 객체도 만들어봄











  
  


  


















