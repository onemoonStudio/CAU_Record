#include <stdio.h>
#include <conio.h>
#include <string.h>
#include <Winsock2.h>

#define  ECHOMAX	255

void main()
{
	WSADATA wsaData;
	SOCKET listenSocket;
	struct sockaddr_in echoServerAddr;
	struct sockaddr_in echoClientAddr;
	char echoBuffer[ECHOMAX];
	int receiveSize, clientAddrLen;

	//  ��Ʈ��ũ�� �ʱ�ȭ �Ѵ�.
	::WSAStartup( 0x202, &wsaData );

	//  UDP ������ �����Ѵ�.
//	::socket();


	//  sockaddr_in ����ü�� ������ �����Ѵ�.
	::memset( &echoServerAddr, 0, sizeof( echoServerAddr ) );
//	echoServerAddr.sin_family
//	echoServerAddr.sin_addr.s_addr
//	echoServerAddr.sin_port


//	::bind();


	while( 1 )
	{
		clientAddrLen = sizeof( echoClientAddr );

		//  Ŭ���̾�Ʈ�κ��� �޽����� �����ϱ⸦ ��ٸ���.
//		::recvfrom();
		if( receiveSize < 0 )
			continue;

		printf( "Handling client - %s\n>%d Bytes : %s\n", ::inet_ntoa( echoClientAddr.sin_addr ), receiveSize, echoBuffer );

		//  ���� �޽����� Ŭ���̾�Ʈ�� �ǵ��� ������.
//		::sendto();


	}

	//  ������ ��/��� ���۸� ���� ��Ȱ��ȭ ��Ų��.
	::shutdown( listenSocket, SD_BOTH );

	//  ���� �۾��� �����Ѵ�.
	::closesocket( listenSocket );

	::WSACleanup();
}