# 싱글톤 패턴(Singleton)

## 싱글톤(Singleton) 패턴이란?
- 싱글톤 클래스는 오직 하나의 인스턴스를 가지며, 그 인스턴스에 전역적(global)으로 접근할 수 있는 단일 지점(single point)을 제공함
- "단일 지점(single point)"이라는 용어는 메서드나 필드 값을 통해 싱글톤 클래스에 접근할 수 있는 장소를 가리킴
- 싱글톤 패턴은 클래스를 딱 한 번만 인스턴스화하고 , 이 인스턴스가 전역적으로 유일하게 사용되도록 보장됨. 즉, 같은 클래스의 여러 객체가 생성되는 것을 방지하고, 오직 하나의 객체만 생성되어 공유될 수 있도록 함 
- 싱글톤 클래스의 상태는 전역적으로 공유되기 때문에, 싱글톤 클래스에 정의한 상태(state)는 어플리케이션 모두에서 접근이 가능한 전역 상태(global state)가 됨 


## UML 

### Singleton 클래스란?
- 특별하고 유니크한 인스턴스를 만드는 곳 
### 왜 유니크하게 만드나?
- 싱글톤 클래스는 일반적으로 리소스 공유, 설정 관련 등에서 사용되며 유일한 객체를 필요로 하는 경우에 쓰임 
### 리소스 공유, 설정관련이 유니크한 객체를 쓰는 이유?
- 일관성을 유지해야하기 때문임. 여러 객체나 모듈에서 설정 데이터 및 공유 리소스에 접근할 때는 일관된 상태를 유지하는 것이 에기치않은 동작을 방지할 수 있음


## 싱글톤 클래스 구현법  

### 인스턴스 생성관리
- 외부에서 생성자에 접근할 수 없도록 함 
- 하위 클래스/ 상속은 허용되지 않도록 함 <br>
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

-  인스턴스를 미리 생성하고 있지 않음. 조건에 맞을 때 생성하도록 함 
-  멀티스레드 환경에서 동기화처리를 위해 쓰임  <br>
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
-  Eager Singleton(클래스 로드시, 즉시 싱글톤 생성)하는 것을 가장 먼저 구현해놓고 실행시 문제가 있을 경우(멀티스레드 이슈 etc) Lazy Singleton으로 전환하기


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
- Runtime 클래스(자바 실행환경과 관련된 다양한 작업수행)는 Eager Singleton 패턴을 따르고 있음<br>
- 애플리케이션에서 항상 하나의 Runtime 인스턴스만 사용되며, 초기화가 즉시 이루어지므로 멀티스레드 환경에서 안전한 방식으로 Runtime 클래스의 인스턴스를 관리해줌 <br>.



### 싱글톤 주의사항 
- 유닛테스트가 어려움 <br>
( 어플리케이션 전체에서 단일 인스턴스를 공유하기 때문에, 해당 인스턴스를 모킹하는 것이 어렵다 ) <br>
- 싱글톤이 변경 가능한 전역 상태(mutable global state)를 가지고 있다면, 디자인이 잘못된 것임 <br>
( 어플리케이션 전체에서 사용되기 때문에 버그유발 가능) <br>
