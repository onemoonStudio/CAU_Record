# Simple Merge

### Hint
- Wikipidia 에서 LCS 알고리즘 과 diff 알고리즘을 읽는 것이 좋다.
- [LCS Algorithm]([http://en.wikipedia.org/wiki/Longest_common_subsequence_problem)
- [Diff Utility](http://en.wikipedia.org/wiki/Diff)

## Goal of Project
- compare two file and merge
- ref > [WinMerge Youtube](https://www.youtube.com/watch?v=pU5lW0fBc-o)
- 비교하는 것을 넘어서야 한다. 

## Must Features
1. 파일을 보고 , 수정하고 , 저장하는 기능이 구현되어야 한다.
    - 두개의 에디터 패널이 있어야 한다.
    - 각 에디터 패널 상단에 `Load` , `Edit` , `Save` 버튼이 있어야 한다.
        - `Load` : 파일 시스템에서 원하는 파일을 찾는 역할을 한다.
        - `Edit` : edit panel 에서 수정할 수 있도록 만든다.
        - `Save` : 수정된 항목으로 저장 되어야 한다.
1. 두 파일을 비교하는 기능
    - 메인 패널에는 `Compare` 버튼이 있다.
        - `Compare` : 두 파일중에서 서로 다른 것들의 백그라운드와 폰트를 변경한다.
        - `LCS Algorithm` 을 사용해서 두 패널의 텍스트를 비교한다.
1. 두 파일을 합치는 기능
    - 서로 다른 텍스트들을 동일하게 만든다. ( Merge )
    - 이때 바뀐 텍스트들만 순차적으로 선택할 수 있도록 만들어야 한다. 
    - 두 가지 Merge 버튼이 있다.
        - `Copy to Right` : 오른쪽에 있는 텍스트로 왼쪽 텍스트를 수정한다.
        - `Copy to Left` : 왼쪽에 있는 텍스트로 오른쪽 텍스트를 수정한다.
    - 개발할 당시에 `JUnit` 이나 `EasyMock`과 같은 테스팅 프레임워크를 사용해야 한다. 이는 모든 도큐멘테이션에도 명시되어야 한다.
        - 이를 위해서 GUI 는 MVC 패턴으로 만들어져야 한다. 따라서 MVC 패턴의 선행 학습이 필요하다.
        - 테스팅을 할 때에는 정상적인 상황에서의 체크 방식과 비 정상적인 상황에서의 반응을 테스트 케이스에 명시해야 한다.

## Summary
- `Must Features`의 기능이 모두 들어간 `Simple Merge` 프로그램을 만들어야 한다.
- `unit-testable` 한 방식으로 만들어야 한다.
    - `JUnit` 이나 `EasyMock` 과 같은 `Testing FrameWork`를 개발할 때 사용해야 한다.

## Final Submissions Should Include

1. All the executable files/source code/configuration data/test code/test data files
    -	All the executable files/source codes, configuration, test codes, test data should be submitted
    - Include a document “README” explaining how to execute and build your software

1. (Use Case-based) Software Requirement Specification (See attachment for samples)
    - Introduction to the system 
    - Use case diagrams
    - Use case descriptions
    - System sequence diagrams
    - Non-functional requirements (Quality requirements, Constraints, etc)
    - Requirement Dependency Traceability 
    - Development and Target Platforms
    - Project Glossary
    - Document Revision History

1. Analysis & Design, Implementation, and Test Document:
    - Domain model (utilize class diagrams)
    - Software Architecture + Design Model (utilize Class diagrams, Sequence diagrams, Statechart, etc)
    - Clearly indicate major design decisions!
    - must include the explanation that how MVC concept was applied to your design
    - must include explanation that how your program was designed to be testable by Unit-test tools.
    - explain how object-oriented design principles were applied to your design along with diagrams/code examples
    - usage of your program and the screen shots of examples 
    - Functional unit test cases and their results
    - System test cases and their results
    - NOTE: In case your program is not fully functional, you should mention those limitations in detail.

  
1. Project Management Report
    - Indicate the address of your github project repository!
    - Briefly explain your project progress history
    - Briefly explain your experience



