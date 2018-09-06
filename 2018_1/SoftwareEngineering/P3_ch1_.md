# ch1 : Object-Oriented Analysis and Design

## Analysis and Design
- `Analysis` 
    - Investigation : what is `problem` and what `requirements`
    - `requirements analysis` or `Object Orientede Analysis`(OOA)
    - Discover `domain concept`
    - 메소드가 나오면 안된다 그냥 개념만 있다.

- `Design`
    - `Conceptual solution` to fullfill requirements
    - `Object Oriented Design`(OOD) or `database design`
    - `sw Object`(현실에는 없을수도 있다) 를 정의한다. 

## UML 이란 무엇인가?

- UML ( Unified Model Language )
- 구조와 요소들을 보여주는 언어라고 생각하자 

### `Three Ways` to apply UML
1. UML as `Sketch`
    - 스케치 용도 , 사실상 정보의 누락이 상관없다. 이해만 하면 된다.
    - informal and incomplete `diagrams`
1. UML as `Blueprint`
    - 스케치보다는 정확하다 . 잘못된 정보가 있어서는 안된다. 
    - detailed design diagrams
    - 2 가지에 사용된다. 
        1. reverse engineering
        1. code generation
        - 코드를 역으로 이해시키거나 코드를 만들거나 할때
1. UML as `programming language`
    - Template 같은 느낌이다.
    - 프로그래머가 코드를 만지지 않는다. 완성된 코드이다.
    - `complete executable specification` of software

### `Three Perspective` to Apply UML
1. Conceptual perspective
    - `diagrams` are interpreted as `describing things` in real world or `domain` of interest
1. Specification (software) perspective
    - `diagrams` describes `software abstractions` or `components`
    - but 특정 환경에 대한 언급은 없다 ( C , java )
1. Implementation (software) perspective
    - `diagrams` describe software implementations in a particular tech
    - 특정한 환경에서의 실행 결과를 보여준다.
- 1 번에서 3 번으로 갈수록 구체적이다.

### UML 은 결국
- `OOAD` 가 아니다!
    - 체계적으로 개발하는 방식이 아니다.
- UML 은 객체 지향적 사고를 가르치지 않는다.
- MDA 로서 사용하지 않는다면 , 완벽한 솔루션이 아니다.



