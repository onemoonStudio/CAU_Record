/*
	TCP 방식으로 데이터를 주고 받기 위해 구현한 클라이언트
*/
#include <stdio.h>
#include <WinSock2.h>

void main()
{
	WSADATA wsaData;
	SOCKET socketConnect;
	struct sockaddr_in serverAddr;
	::WSAStartup( 0x202, &wsaData );
	// 2.2 버전을 사용한다.

	socketConnect = ::socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
	// 디스크립터 할당 ipv4, stream , tcp
	if (socketConnect == INVALID_SOCKET)
		printf("socket() error!");

	// 접속할 서버의 정보를 설정한다.
	::memset( &serverAddr, 0, sizeof( serverAddr ) );
	serverAddr.sin_family = AF_INET;
	serverAddr.sin_addr.s_addr = ::inet_addr("127.0.0.1");
	serverAddr.sin_port = ::htons(8600);
	// 서버 구조체 초기화 
	// ipv4 , address , port
		

	if (::connect(socketConnect, (struct sockaddr*)&serverAddr, sizeof(serverAddr) ) == SOCKET_ERROR)
		printf("connect() error!");
		// server로 connect 
		// listen 상태여야 한다. 아니면 error

	
	// 서버와 통신을 한다. 
	while(1){
		
		char sendBuffer[127];
		char recvBuffer[128];
		int sentBytes, recvBytes;

		scanf("%s", sendBuffer);
		// input from user

		printf("> ");
		fputs(sendBuffer,stderr);
		fputc('\n', stderr);


		sentBytes = ::send(socketConnect,sendBuffer,sizeof(sendBuffer)+1, 0);
		printf( "%d bytes sent.\n", sentBytes );
		// +1 을 붙이는 이유는 NULL이 포함될 수 있기 때문에 붙인다.

		recvBytes = ::recv(socketConnect, recvBuffer, sizeof(recvBuffer), 0);
		// recv는 그대로 받는다.
		printf( "%d bytes Received: %s\n", recvBytes, recvBuffer );
	}

	::shutdown( socketConnect, SD_BOTH );
	::closesocket( socketConnect );
	::WSACleanup();
	// socket 을 종료한다.
	system("pause");
}
