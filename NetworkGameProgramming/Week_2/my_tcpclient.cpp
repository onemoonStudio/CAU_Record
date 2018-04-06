#include <stdio.h>
#include <WinSock2.h>

void main()
{
	WSADATA wsaData;
	SOCKET socketConnect;
	struct sockaddr_in serverAddr;
	::WSAStartup( 0x202, &wsaData );

	socketConnect = ::socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
	if (socketConnect == INVALID_SOCKET)
		printf("socket() error!");

	// 접속할 서버의 정보를 설정한다.
	::memset( &serverAddr, 0, sizeof( serverAddr ) );
	serverAddr.sin_family = AF_INET;
	serverAddr.sin_addr.s_addr = ::inet_addr("127.0.0.1");
	serverAddr.sin_port = ::htons(8600);
		

	if (::connect(socketConnect, (struct sockaddr*)&serverAddr, sizeof(serverAddr) ) == SOCKET_ERROR)
		printf("connect() error!");

	
	// 서버와 통신을 한다. 
	while(1){
		
		char sendBuffer[128];
		char recvBuffer[128];
		int sentBytes, recvBytes;

		scanf("%s", sendBuffer);
		// input from user

		printf("> ");
		fputs(sendBuffer,stderr);
		fputc('\n', stderr);


		sentBytes = ::send(socketConnect,sendBuffer,sizeof(sendBuffer)-1, 0);
		printf( "%d bytes sent.\n", sentBytes );

		recvBytes = ::recv(socketConnect, recvBuffer, sizeof(recvBuffer)-1, 0);
		printf( "%d bytes Received: %s\n", recvBytes, recvBuffer );
	}

	::shutdown( socketConnect, SD_BOTH );
	::closesocket( socketConnect );
	::WSACleanup();
	system("pause");
}
