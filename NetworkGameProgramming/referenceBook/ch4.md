# TCP 기반 서버 클라이언트 1

## TCP 기반 서버 , 클라이언트

### TCP 기반 서버

- 기본적인 함수 호출 순서
    1. socket()     // 소켓 생성
    2. bind()       // 소켓 주소 할당
    3. listen()     // 연결요청 대기상태
    4. accept()     // 연결 허용
    5. read() / write()     // 데이터 송수신
    6. close()      // 연결종료

#### 연결요청 대기상태로의 진입

<code>  

    int listen(int sock , int backlog);    
    // 성공시 0 , 실패시 -1 반환 
</code>

- int sock 
    - 연결요청 대기상태에 두고자 하는 소켓의 파일 디스크립터 전달, 이 함수의 인자로 전달된 디스크립터의
    소켓이 서버 소켓(리스닝 소켓)이 된다. 
- int backlog()
    - 연결요청 대기 큐(Queue)의 크기정보 전달, 5가 전달되면 클라이언트의 연결요청을 5개 까지 대기시킬 수 있다.

- listen 함수 이후에 클라이언트에서 connect 함수가 호출되어야 한다.
    - 이 전에 connect가 호출될 시 에러

#### 클라이언트의 연결요청 수락

- 서버 소켓은 문지기이다!
- 따라서 새로운 소켓을 만들어 클라이언트와 데이터를 주고 받아야 하는데 이를 accept를 통해서 할 수 있다.


<code>

    int accept(int sock, struct sockaddr * addr,socklen_t * addrlen);
    // 성공 시 생성된 소켓의 파일 디스크립터 , 실패 시 -1 반환
</code>
