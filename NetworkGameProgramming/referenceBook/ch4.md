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

- int sock 
    - 서버 소켓의 파일 디스크립터 전달
- struct sockaddr * addr
    - 연결요청 한 클라이언트의 주소정보를 담을 변수의 주소 값 전달, 클라이언트의 주소 정보가 채워진다.
- socklen_t * addrlen
    - 두번째 매개변수로 전달된 주소의 변수크기를 바이트 단위로 전달, 단 크기정보를 변수에 저장한 다음에 변수의 주소 값을 전달한다.

- accept 함수는 '연결요청 대기 큐'에서 대기중인 클라이언트의 연결요청을 수락하는 기능의 함수이다.
- 호출성공 시 내부적으로 데이터 입출력에 사용할 소켓을 생성하고, 그 소켓의 파일디스크립터를 반환한다.

### TCP 기반 클라이언트

- 클라이언트의 구현과정은 소켓의 생성과 연결의 요청이 전부이다.
- 서버에서 listen 함수를 호출하고 연결요청 대기 큐를 만들어 놓으면 그때부터 클라이언트에서 연결 요청을 할 수가 있다. connect 함수를 이용한다.

<code>

    int connect (int sock , const struct sockaddr * servaddr , socklen_t addrlen);
    // 성공시 생성된 소켓의 파일 디스크립터 , 실패시 -1 반환
</code>

- int sock 
    - 클라이언트의 소켓의 파일 디스크립터 전달
- const struct sockaddr * servaddr 
    - 연결요청 할 서버의 주소정보를 담은 변수의 주소 값을 전달
- socklen_t addrlen
    - 두 번째 매개변수 servaddr에 전달된 주소의 변수 크기를 바이트 단위로 전달

- connect 함수가 제대로 반환이 되었지만 당장의 서비스가 이루어진다는 보장은 없다.
    - accept가 아닌 연결요청 대기 큐 에 들어가면 반환되기 때문이다.