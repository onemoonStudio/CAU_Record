# 네트워크 프로그래밍과 소켓의 이해

## 소켓의 생성과정

1. 소켓 생성
    - socket 함수 호출
2. IP주소와 PORT번호 할당 
    - bind 함수 호출
3. 연결요청 가능상태로 변경
    - listen 함수 호출 
4. 연결요청에 대한 수락
    - accept 함수 호출


## winsock 초기화

- 버전 라이브러리 초기화를 위해서 사용된다.

<code> 

    # include <winsock2.h>
        
    int WSAStartup(WORD wVersionRequested, LPWSADATA lpwSAData);
</code>

- wVersionRequested : 사용하는 윈도우 소켓의 버전을 말한다.
    - 0x0201 일때 앞에 두자리는 주버전 뒤의 두자리는 부버전을 말한다.
    - 상단의 버전은 2.1이다.
    - 이를 쉽게 MAKEWORD(2,1) 로도 선언할 수 있다.
- LPWSADATA : WSADATA 의 포인터형 
    - WSADATA 구조체 변수의 주소값을 전달해야 한다.
- 성공시 0 이 반환되고 실패시 에러코드가 반환된다.

<code>
    
    WSADATA wsaData;
    if(WSAStartup(MAKEWORD(2,1) , &wsaData) != 0){
        ErrorHandling("WSAStartup Error!");
    }
    return 0;
</code>

- 보통 위와 같은 방식으로 처리한다.