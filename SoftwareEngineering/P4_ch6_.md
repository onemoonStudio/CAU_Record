# ch6 : Use Cases

![up_artifacts relationship](./imgs/ch6_up_relationship.png)

## Use Cases
- `text story`
- 어떤 actor가 goal을 성취하기 위한 스토리를 글로 표현한 것이다.
- diagram이 아니다!

### Definition : Actors , Scenarios and Use Cases
- Actor
    - 사람뿐만 아니라 어떤 행동을 하는 모든것을 지칭한다.
- Scenarios 
    - actor와 system간의 연속된 행동들과 반응들을 말한다.
    - 또한 `UseCase Instance` 라고도 한다.
- Use Case
    - Actor가 목적을 이루기 위해 시스템을 사용하면서 관련된 성공 시나리오 그리고 실패 시나리오 들의 집합이다.

## Use Case and Use Case Model
- UseCase Modeling 이 더 큰개념
    - Use-Case Modeling 과정 안에 Use-Case Model이 들어가 있다.

## UseCase 를 사용하는 이유
- `유저의 목표`를 확인하기 쉽다
- `유저의 관점과 목표`를 강조한다.
- UseCase는 스케일을 유동적으로 조절할 수 있다.

## UseCase는 functional requirements ?
- YES
    - functional requirements이다.
    - FURPS 중에서 `F`를 담당한다.
    - 다른곳에서도 사용 가능하다.

## Three Kinds of Actors
1. Primary Actor
    - 서비스에 의해서 수행되는 목표를 가지고 있는 존재
1. Supporting Actor
    - 페이먼트 시스템
1. Offstage Actor
    - usecase에 관심이 있으면서 둘다 아닌 존재
    - 세금 같은 것들...

## Sections Meaning 
- Level
    - User-Goal level
    - Subfunctional level
- Stakeholders and Interest lists 
    - 시스템이 해야 하는 것에 대한 제시를 해준다.
- Preconditions , Success Guarantees (PostConditions)
    - 시나리오가 시작되기 전 되야하는 것들
    - 시나리오가 끝났을 때의 결과들 
- Main Success Scenario
    - happy path
- Extensions ( Alternate Flows)
    - happy path에서의 가지로 뻗친 상황들
    - `Main Success Scenario + Extionsions` 로 stakeholder를 만족시켜야 한다.

## How to Find UseCases
1. `system boundary` 를 정한다
1. `primary actor` 를 정한다
1. 각 primary actor 의 `goal` 을 정한다
1. `UseCase`를 정한다.

- actor 와 goal 은?
    1. usecase diagram을 사용
    1. actor - goal list -> usecase diagram

## Who is Primary Actor
- 중요하다 핵심!
    - `Boundary` 를 신경써야 한다.
    - `primary actor` 는 `boundary 바깥`에 있다.
![ch6_primary](./imgs/ch6_primary_actor.png)

- useful level usecase 를 정하는 테스트 3가지
    1. the boss test
    1. EBP test(Elementary Business Process)
        - 한사람이 어떤 기간동안 할수 있는 양을 생각한다.
    1. Size test

## Using UML : Use Case Diagrams
- UseCase Diagram 은 다음을 설명해준다
    - name of usecase
    - actors
    - relationship
- Activies Diagram 도 있다.
    - text가 더 많으며 그림과 같이 설명해놨다.



