# Thinking in Objects

## Immutable Object and Class

한번 생성이 된 후에 값을 변경할 수 없는 객체인 경우에 `Immutable Object` 라고 한다. 
또한 이러한 객체를 만드는 클래스를 `Immutable Class` 라고 한다.

모든 멤버변수가 private 이고, setter 가 없다고 해도 무조건 Immutable Class 인건 아니다. 다른 클래스가 끼어있다면, class 내 참조하는 값이 변경될 수 있기 때문이다.

따라서 Immutable Class 의 조건은
1. 모든 멤버변수는 private
1. No mutators(setter) 
1. No accessor methods ( 값을 변경할 수 있는 )

## `this` Keyword

객체 자신을 가르키는 지시자이다. (refers to an Object itself)

보통 두가지로 많이 사용한다.
1. hidden data fields 를 가리킬 때 많이 쓰이곤 한다.
1. Constructor 에서 동일 클래스 내에 다른 Constructor 를 가르킬 때에도 쓰인다.
