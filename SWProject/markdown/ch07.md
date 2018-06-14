# Object and Classes

objects has a unique `identity` , `state`, and `behavior`
- state
    - the state of an object consists of a set of data fields
- behavior
    - the behavior of an object is defined by a set of methods

### class provides a speciall methods , known as `constructors`
- constructor do not have a return type
- 만약 따로 constructor 를 설정해주지 않았다면 , default 로 no-arg constructor 가 생성된다.
```java
class Circle{
    // data fields
    double radius = 1.0;
    
    // Constructor
    Circle(){
        
    }
    // methods
    double getArea(){

    }
}
```

- field 에 reference type 변수를 선언한 뒤 초기화를 안해줬다면 `null`값이 들어가게 된다.

## Garbage Collection
c1 object 와 c2 object가 있을 때 만약 `c1 = c2;` 를 실행하게 된다면, c1 은 결국 c2 를 가르키게 되고 이전에 c1 object 를 가르키는건 없어진다.

따라서 c1 을 쓰레기(garbage)로 인식하고 , JVM에서 알아서 회수해간다.

혹은 object에 명시적으로 null 을 선언해준다면 , 이 또한 garbage 로 인식한다.

## Date
```java
java.util.Date date = new java.util.Date();
// date.toString() => Wed Jun 06 16:17:04 KST 2018
```

## Random
기존에 Math.random() 은 간단하지만 여러 활용이 어려웠다.

java.util.random 은 더 다양한 메소드 들을 제시한다.

```java 
java.util.Random rnd = new java.util.Random();

```

- Random() : 현재 시간을 seed로 주고 랜덤 오브젝트를 리턴한다.
- Random(seed : long) : long 값을 seed로 설정해 줄 수 있다.
- nextInt() : 랜덤한 int 값을 리턴한다.
- nextInt(n : int) : 0~n 사이의 값을 리턴한다. ( exclusive 0<k<n )
- nextLong() : 랜덤한 Long 값을 리턴한다.
- nextDouble() : double 값 리턴 ( 0 ~ 1 )
- nextFloat() : float 값 리턴 ( 0F ~ 1F )
- nextBoolean : Boolean 리턴

! 동일한 시드를 가지고 있는 random object 를 만든다면 , 동일한 값들이 리턴 될것이다.

## 멤버 변수 및 메소드
- Intance variables , and Methods
- Static variables Contants , and Methods

## modifier
1. public 
    - 모두 접근 가능
1. default
    - 같은 패키지 내 접근 가능
1. private
    - 같은 클래스 내에서만 접근이 가능

