# 도메인 네임 시스템 ( Domain Name System )

- 도메인?
    - 복잡한 IP 주소를 기억하고 입력하기 어렵기 때문에 도메인을 통해서 IP 주소를 대신한다
    - 도메인 != IP 주소 이다.
    - DNS 서버에 도메인을 전달하고 DNS 서버에서 IP 를 받는 방식으로 진행된다.
    - ping naver.com 을 하게 된다면 naver.com 의 IP 주소를 얻을 수 있다.
    
## 도메인 이름을 이용해서 IP 주소 얻어오기

```

    struct hostent * gethostbyname(const char * name);
    // 성공시 hostent 구조체 변수의 주소 값, 실패시 NULL 반환
    // 아래는 성공시 반환되는 구조체

    struct hostent
    {
        char * h_name;      // official name
        char ** h_aliases;  // alias list
        int h_addrtype      // host address type
        int h_length;       // address length
        char ** h_addr_list;// address list
    }
```

| 구조체 인자  | 설명 | 
| :---------- | :--------- | 
| h_name    | 공식 도메인 이름  | 
| h_aliases    | 접속할 수 있는 다른 도메인 이름들 | 
| h_addrtype    | h_addr_list로 반환된 IP 주소체계의 대한 정보 | 
| h_length    | IP주소의 크기정보 , IPv4 -> 4 , IPv6 -> 16 | 
| h_addr_list    | 도메인 이름데 대한 IP주소가 정수의 형태로 반환된다.|  

## IP주소를 이용해서 도메인 정보 얻어오기

```

    struct hostent * gethostbyaddr( const char * addr, socklen_t len, int family);
    // 성공 시 hostent 구조체 변수의 주소값 , 실패시 NULL포인터 반환
```

| 변수 이름 | 설명 | 
| :---------- | :--------- | 
| addr     | IP 주소를 지니는 in_addr 구조체 변수의 포인터 전달  | 
| len    | 첫 번째 인자로 전달된 주소정보의 길이 , IPv4->4, IPv6->16 | 
| family    | IPv4->AF_INET , IPv6->AF_INET6 | 