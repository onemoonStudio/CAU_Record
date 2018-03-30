# 소켓의 타입과 프로토콜의 설정

## 프로토콜이란 무엇인가?

- 프로토콜 : 컴퓨터 상호간의 대화에 필요한 통신규약 

## 소켓의 생성 
```
    int socket(int domain , int type ,int protocol);
```

- domain : 소켓이 사용할 프로토콜 체계 전달 (protocol family)
- type : 소켓의 데이터 전송방식에 대한 정보 전달
- protocol : 두 컴퓨터 통신간에 사용되는 프로토콜 정보 전달

### domain
#### 프로토콜 체계

| 이름  | 프로토콜 체계 (protocol family ) | 
| :---------- | :--------- | 
| PF_INET    | IPv4 인터넷 프로토콜 체계 | 
| PF_INET6    | IPv6 인터넷 프로토콜 체계 | 
| PF_LOCAL    | 로컬 통신을 위한 UNIX 프로토콜 체계 | 
| PF_PACKET    | LOW LEVEL 소켓을 위한 프로토콜 체계 | 
| PF_IPX    | IPX 노벨 프로토콜 체계|  

- 가장 많이 쓰이는 프로토콜 체계는 PF_INET 
- 이 챕터 에서는 PF_INET 을 기준으로 설명한다.

### type 
#### 소켓의 타입 
- 크게 2가지가 있다.
- SOCK_STREAM ( 연결지향형 소켓)
    - 중간에 데이터 소멸이 없다.
    - 순차적으로 데이터가 전송된다.
    - 데이터의 경계가 없다. ( Boundary 가 없다.)
        - 따라서 write 횟수와 read 횟수가 동일할 필요가 없다.
    - 소켓의 연결은 반드시 1:1 이어야 한다.
    - <code>@ 결론 : 신뢰성 있는 순차적 바이트 기반의 연결지향 데이터 전송 방식의 소켓</code>
- SOCK_DGRAM ( 비 연결지향형 소켓)
    - 중간에 데이터 소멸이 있을 수 있다.
    - 데이터의 경계가 존재한다.
        - write 횟수와 read 횟수가 동일해야 한다.
    - 한번에 전송할 수 있는 데이터의 크기가 제한된다.
    - <code>@ 결론 : 신뢰성과 순차적 데이터 전송을 보장하지 않는 , 고속의 데이터 전송을 목적으로 하는 소켓</code>
    
### protocol 
#### 프로토콜의 최종 선택

- domain 과 type으로도 protocol이 선택되지만 아래의 예외 상황을 고려해서 protocol까지 사용한다.
    - 굳이 예외 상황을 체크 안할때에는 0을 넣어도 무방하다.
    - 예외 : 하나의 프로토콜 체계 안에 데이터 전송방식이 동일한 프로토콜이 둘 이상 존재하는 경우

### 예시

``` 
    int tcp_socket = socket( PF_INET, SOCK_STREAM, IPPROTO_TCP);
```
- IPv4 인터넷 프로토콜 체계에서 동작하는 연결지향형 데이터 전송 소켓
- IPPROTO_TCP 는 이 두 조건을 만족시키는 유일한 프로토콜이다.
- 이때 생성되는 소켓이 <code>TCP 소켓</code>이다.

``` 
    int udp_socket = socket( PF_INET, SOCK_DGRAM, IPPROTO_UDP);
```
- IPv4 인터넷 프로토콜 체계에서 동작하는 비 연결지향형 데이터 전송 소켓
- IPPROTO_UDP 는 이 두 조건을 만족시키는 유일한 프로토콜이다.
- 이때 생성되는 소켓이 <code>UDP 소켓</code>이다.
