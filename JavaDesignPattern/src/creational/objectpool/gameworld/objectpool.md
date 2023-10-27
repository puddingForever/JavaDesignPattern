# 오브젝트 풀패턴 (Object Pool)

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




