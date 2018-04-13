/*
	TCP 방식으로 데이터를 주고 받기 위해 구현한 서버	
*/

#include <stdio.h>
#include <WinSock2.h>

void main()
{
	WSADATA wsaData;
	SOCKET socketListen, socketClient;
	struct sockaddr_in serverAddr;

	::WSAStartup( 0x202, &wsaData );
	// 2.2 버전을 사용한다.


	socketClient = INVALID_SOCKET;
	socketListen = ::socket( AF_INET, SOCK_STREAM, IPPROTO_TCP );
	// 디스크립터 할당 ipv4, stream , tcp
	if( socketListen == INVALID_SOCKET )
	{
		printf( "Socket create error !!\n" );
		return;
	}

	::memset( &serverAddr, 0, sizeof( serverAddr ) );
	serverAddr.sin_family		= AF_INET;
	serverAddr.sin_addr.s_addr	= ::htonl( INADDR_ANY ); // ::inet_addr( "165.194.115.25" ); //::htonl( INADDR_LOOPBACK  );
	serverAddr.sin_port			= ::htons( 8600 );
	// 서버 구조체 초기화 
	// ipv4 , 어디서든 들어올 수 있도록 설정 , 8600 포트

	if( ::bind( socketListen, ( struct sockaddr* )&serverAddr, sizeof( serverAddr ) ) == SOCKET_ERROR )
	{
		printf( "bind failed!! : %d\n", ::WSAGetLastError() );
		return;
	}
	// 디스크립터에 구조체를 설정해준다.
	// 디스크립터 , 구조체의 주소 , 구조체의 사이즈 

	if( ::listen( socketListen, SOMAXCONN ) == SOCKET_ERROR )
	{
		printf( "listen failed!! : %d\n", ::WSAGetLastError() );
		return;
	}
	// connect가 들어올때 까지 대기한다
	// 총 SOMAXCONN 까지 연결을 받을 수 있다. ( 대기 큐 )

	while( 1 )
	{
		// 접속처리 클라이언트는 하나만 받는다.
		if( socketClient == INVALID_SOCKET )
		{
			fd_set fds;
			struct timeval tv = { 0, 100 };	

			FD_ZERO( &fds );
			FD_SET( socketListen, &fds );
			// socketlisten 을 fds에 할당

			::select( 0, &fds, 0, 0, &tv );
			if( FD_ISSET( socketListen, &fds ) )
			// 정상적으로 socketListen이 할당되었을 경우
			{
				struct sockaddr_in fromAddr;
				int size = sizeof( fromAddr );

				socketClient = ::accept( socketListen, ( struct sockaddr* )&fromAddr, &size );
				// accept를 통해서 socketClient를 만든다. 
				// client에서는 connect와 연결된다. 
				printf( "Accepted a client : %s\n", ::inet_ntoa( fromAddr.sin_addr ) );
				// 클라이언트의 주소를 보여준다.
			}
		}
		else
		{
			// 접속이 이루어진 후에는 echo 통신을 한다.
			while(1){

			char recvBuffer[128];
			int recvBytes;

			recvBytes = ::recv( socketClient, recvBuffer, 128, 0); //MSG_OOB );
			// 128 바이트를 서버로부터 받는다.
			if(recvBytes<2)
				break;

			printf( "%d bytes received : %s\n", recvBytes, recvBuffer );

			::send( socketClient, recvBuffer, recvBytes, 0 );
			// echo
			}

			// 통신이 끝난 후에는 클라이언트 접속을 해제한다.
			::shutdown( socketClient, SD_BOTH );
			::closesocket( socketClient );
			socketClient = INVALID_SOCKET;
		}
	}

	::WSACleanup();
}

// 프로퍼티 - 타겟 - visuall studio platform tool set => 2013 