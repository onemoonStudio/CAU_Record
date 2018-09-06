# Object-Oriented Analysis and Design

## Software development - Solving problems

### purpose of analysis and design 
- requirements -> design of system to-be
- evolve a robust architecture (튼튼한 아키텍쳐를 위해서 )
- 실행 환경에 맞추기 위해서

### `Analysis and Design`
- `Analysis` 
    - focuse on understanding `problems`
    - idealized design
    - `Functional Requirements` 
    - Behavior
    - 문제 자체를 이해하기 위한 과정
    - 기능들에 집중한다.
- `Design`
    - Focus on understanding the `solution`
    - `Performance`
    - Close to `real code`
    - `Non-functional` requirements
    - `A large model`
    - 특정 환경이나 퍼포먼스 등을 고려한다

## `Procedural Programming`
- Program is organized around procedures
- Focusing on `data structure` , `algorithms` , `sequencing of steps`
    - `Program = Algorithm + Data Structure`
- 단점
    - 거대하고 복잡한 것들은 만들기 어렵다.
    - symantic gap
        - analysis + design -> code 로 하기 너무 어렵다
    - 실제 상황이랑 맞춰서 디자인을 하면 굉장히 차이가 많이 난다.
    - Rigid 
        - 뭐 하나를 추가하면 다시 deploy
    - Fragile 
        - if else 등으로 세분화 되어 있다면 찾기가 힘들다
    - Immobile
        - 몇개의 기능이 필요 없더라도 다 가지고 다녀야 한다

## `Object-Oriented Programming` (`OOP`)
- Teminology
    - `Object` : 사람 , 장소 , 물건 (noun)
    - `Method` : `object`에 의한 행동
    - `Class` : 비슷한 Object들의 카테고리
- `Object` 
    - data 와 method 를 둘다 가지고 있다.
    - send and receive messages from actions

## Strength of Object Technology 
- `A single Language` 
    - used by user , analyst , designer 
- `UML` ( Unified Modeling Language )
- code를 작성할 때 앞서 만든 구조(`architecture`)를 활용한다.
- models `reflect real world`
    - easy to understand 
- `stability`
    - 작은 변화가 나타나도 전체적인 흐름에는 영향을 끼치지 않는다.
- `adaptive to change`
    - 변화 수긍적

