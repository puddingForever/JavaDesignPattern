# 프로토타입 패턴 (Prototype Pattern)


### 프로토타입 패턴이란?
이미 존재하는 인스턴스를  프로토타입(Prototype : 표준 , 기준 , 템플릿)으로 삼고, <br>
프로토타입을 복제하여 간단하게 인스턴스를 만들 수 있음 


### UML 

![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/f44772ae-2f2c-4859-8bb0-c3a2e98e085a)


**Prototype** :  **Clone() 메소드**가 들어있는 추상객체 <br>
**ConcretePrototypeA,B** : 추상객체를 상속받은 구현객체. Prototype의 clone() 메소드를 오버라이딩하여 자기자신을 복제할 수 있음 <br>
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
>미사일을 추상객체로 만들고, 아이언돔,생화학무기,초음속미사일을 구상객체로 만들어보았음 <br>

![image](https://github.com/puddingForever/JavaDesignPattern/assets/126591306/59ea9306-7571-465d-b235-70afe8b9b6c8)

**Missiles** : 프로토타입으로 만들 추상객체, 내부에 clone()메소드가가 있어서 자기 자신을 복제할 수 있도록 함 <br>
**HyperSonicMissiles,IronDome** : Missiles을 구현하는 구상객체, 자기 자신을 복제할 수 있음 <br>
**BioWeapon** : Missiles을 구현하는 구상객체, 이 객체는 복제가 불가하도록 바꿔봄 <br>


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

Missile은 프로토타입으로 사용될 추상객체이다<br>
Cloneable 인터페이스(마커인터페이스)를 구현하여, JVM에게 복제가 가능한 객체라는 것을 알려주고 <br>
Object의 clone()메소드를 오버라이딩하여 자기자신을 복제하고 리턴한다 <br>
주의점은 복제가 불가한 객체도 있기때문에 CloneNotSupportedException을 throw로 던져준다. <br>
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


          //아이언돔 인스턴스 복제
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
3. clone()메소드에 객체 초기화 설정을 하여, 복제시 초기화 상태로 복제되게 해줌
   > 초기화 과정이 없으면 , 복제이기 떄문에 이전 인스턴스의 값이 그대로 나옴 

### 프로토타입 패턴 사용예시 
Object 객체의 clone()은 프로토타입 패턴 그 자체임 <br>
Cloneable 인터페이스로 특정 객체는 복제가 가능하다는 것을 표시하고, Object 객체의 clone()메소드를 오버라이딩하여 <br>
실제 인스턴스를 복제함 <br>

### 결론 
1. 상태의 변화가 많이 없는 대용량의 인스턴스를 만들고 싶을 때 사용하기
2. clone()메소드로 간단하게 인스턴스 복제가 가능 
3. 로직이 거의 없는 얕은 복사라서 Java개발자들에게 선호되는 패턴은 아니라고 한다.




