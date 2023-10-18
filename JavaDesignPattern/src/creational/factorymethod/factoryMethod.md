# Factory Method ?

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
  // 추상메소드이기 상속받은 클래스에서 오버라이드 필수 
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
	
// DevCreator 안에 팩토리 메소드(인스턴스를 만드는 단순한 애) 가 있음 
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
``
- iterator()메소드는 iterator 인터페이스를 구현한 컬랙션 객체에서 사용되며 <br>
컬랙션 객체에서 반복자객체(iterator)를 생성하고 반환하는 역할을 함

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
- 팩토리 메소드는 상속받는 서브클래스에서 오버라이드되어 인스턴스를 생성함
- 구현 방법이 복잡함 + 유닛테스팅이 많이 필요함


