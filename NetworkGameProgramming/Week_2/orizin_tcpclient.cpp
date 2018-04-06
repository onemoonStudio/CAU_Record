#include <stdio.h>
#include <WinSock2.h>

void main()
{
	WSADATA wsaData;
	SOCKET socketConnect;
	struct sockaddr_in serverAddr;

	::WSAStartup( 0x202, &wsaData );

//	::socket();

	//  접속할 서버의 정보를 설정한다.
	::memset( &serverAddr, 0, sizeof( serverAddr ) );
//	serverAddr.sin_family
//	serverAddr.sin_addr.s_addr 
//	serverAddr.sin_port


//	::connect();

	//  서버와 통신을 한다.
	while(1){
		char sendBuffer[127] = "Test client message...";
		char recvBuffer[127];
		int sentBytes, recvBytes;

		printf(">");
		gets(sendBuffer);

//		sentBytes = ::send();
		printf( "%d bytes sent.\n", sentBytes );

//		recvBytes = ::recv();
		printf( "%d bytes Received: %s\n", recvBytes, recvBuffer );
	}

	::shutdown( socketConnect, SD_BOTH );
	::closesocket( socketConnect );
	::WSACleanup();
}
