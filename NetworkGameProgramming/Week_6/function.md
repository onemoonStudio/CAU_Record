# ip & dns 관련 쓰이는 함수들

## getaddrinfo

- `getaddrinfo`
    - 이 함수는 domain address 를 받아서 네트워크 주소 정보(IP address)를 가져오는 함수이다. 
    - Domain Address -> IP Address
    - 총 4개의 변수를 가지고 1~3 입력 매개변수와 4번째 매개변수는 출력 매개변수이다.
    - 마지막에는 freeaddrinfo를 해서 메모리의 누수를 막아야 한다.

```

    int getaddrinfo(const char *hostname, const char *service, const struct addrinfo *hints, struct addrinfo **result);
    // 성공하면 0 을 반환한다. 실패시 에러메세지 반환
    void freeaddrinfo(struct addrinfo *res);
```

| 매개변수 이름  | 설명 | 
| :---------- | :--------- | 
| hostname    | 호스트 이름 혹은 주소문자열 (주소문자열은 점으로 구분하는 IPv4의 10진 문자열이나 16진 문자열 둘다 올 수 있다.) | 
| service    | 서비스 이름 혹은 10진수로 표현한 포트 번호 문자열 | 
| hints    | getaddrinfo 함수에게 힌트를 준다. addrinfo 구조체를 넘긴다. | 
| result    | addrinfo 구조체이며 링크드 리스트이다. | 

- hints.ai_flags
    - AI_PASSIVE    : bind function 으로 불린 address 사용 ( 보통 localhost )
    - AI_CANONNAME  : name -> ip 주소
    - AI_NUMERICHOST: