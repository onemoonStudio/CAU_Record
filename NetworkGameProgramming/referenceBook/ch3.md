# 주소체계와 데이터 정렬 

## 소켓에 할당되는 IP주소와 PORT번호

##### ip : internet protocol

### 인터넷 주소

- IPv4(Internet protocol version 4) -> 4byte 주소체계
- IPv6(Internet protocol version 6) -> 16byte 주소체계

### 라우터와 스위치    
- 외부로부터 수신된 데이터를 호스트에 전달하고, 호스트가 전달하는 데이터를 외부로
    송신해주는 물리적 장치를 말한다.
- 일반 컴퓨터와 다를게 없지만 특수한 목적을 가지고 설계 및 운영을 한다는 특징이 있다.


### PORT 번호를 사용하는 이유
- 하나의 운영체제 내에서 소켓을 구분하려는 목적으로 사용되며 , 16비트로 표현된다.
- 따라서 범위는 0 ~ 65535이다.
    - 0~1023 은 well-known PORT 라고 해서 특정 프로그램에 할당하기로 되어있다.
- PORT 번호는 중복이 불가능 하지만 TCP 소켓과 UDP 소켓은 포트를 공유하지 않기 때문에
중복되어도 상관이 없다.


### 주소 정보의 표현

- IPv4를 위한 주소표현 구조체
    - 주소체계 , IP , PORT 가 필요함
- 이 구조체는 bind 함수에 주소정보를 전달하는 용도로 사용된다.

<code>
    
    struct sockaddr_in{
        sa_family_t     sin_familly     // 주소체계 (Address Family)
        uint16_t        sin_port        // 16비트 TCP/UDP PORT
        struct in_addr  sin_addr        // 32비트 IP
        char            siz_zero[8]     // 사용 x
    }

    struct in_addr{
        int_addr_t      s_addr;         // 32 비트 IPv4 인터넷 주소
    }
    
</code>

#### sin_family 
- 주소체계 정보 저장

|이름 | 정보 |
|-----|-----|
| AF_INET | IPv4 인터넷 프로토콜에 적용하는 주소체계 |
| AF_INET6 | IPv6 인터넷 프로토콜에 적용하는 주소체계 |
| AF_LOCAL  | 로컬 통신을 위한 유닉스 프로토콜 주소체계 |

#### sin_port 
- 16 비트 PORT 번호 저장
- 중요한 점은 네트워크 바이트 순서로 저장해야 한다는 점이다!!
    - htons

#### sin_addr
- 32 비트 IP 주소정보 저장
- 이 또한 중요한것은 네트워크 바이트 순서
    - htonl

#### sin_zero
- 특별한 의미 없음 0으로 할당하면 된다.
- sockaddr_in 의 크기를 구조체 sockaddr 와 일치시키기 위해 삽입된 멤버
- 없으면 안된다.

이를 통해서 주소정보체계 구조체를 만들고 난 뒤에 아래 bind 함수에 전달된다.

<code>

    struct sockaddr_in serv_addr;

    if( bind(serv_sock,
        (sturct sockaddr*) &serv_addr,
        sizeof(serv_addr)) 
        == -1){ErrorHandling ! }
        
</code>

### 네트워크 바이트 순서와 인터넷 주소 변환

- cpu에 따라 정보를 저장하는 방식이 다르다 . Big Endian 과 Little Endian 으로 나뉜다.
- Big Endian
    - 오른쪽부터 저장한다. 
    - 예를 들어 0x0001 일때 0000 0000 0000 0001 방식
- Little Endian 
    - 왼쪽부터 저장한다.
    - 예를 들어 0x0001 일때 0001 0000 0000 0000 방식
- 이 두가지가 다르기 때문에 네트워크에서 정보를 전달할 때 신뢰성을 위해서 하나로 통일한다.
- Big Endian으로 통일 
- 따라서 port와 ip주소는 항상 네트워크 바이트 순서로 바뀌어야 한다.
    - PORT => htons
    - IP => htonl

### 인터넷 주소의 초기화와 할당
- 문자열 정보를 네트워크 바이트 순서의 정수로 변환하기

<code>
    
    in_addr_t inet_addr (const char* string);
    -> 성공시 빅 엔디안 32비트 정수, 실패시 INADDR_NONE

    int inet_aton(const char* string, struct in_addr* addr);
    -> 성공시 1 , 실패시 0 
    -> 위와 동일한 실행결과 하지만 아래 방식을 더 많이 사용함
    -> string은 ip주소정보 문자열
    -> addr 은 변환된 정보를 저장할 in_addr 구조체 변수의 주소 값
    ( 윈도우에서는 aton이 없다 따라서 atoi로 사용한다. )
    
</code>