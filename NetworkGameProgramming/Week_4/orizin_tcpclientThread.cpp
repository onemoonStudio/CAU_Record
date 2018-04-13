#include <stdio.h>
#include <WinSock2.h>

#define MAX_CLIENT 5
#define MAX_MESSAGE 5


DWORD WINAPI NetReceive(LPVOID socketConnect)
{

	return NULL;
}


void main()
{
	WSADATA wsaData;
	SOCKET socketConnect[MAX_CLIENT];
	struct sockaddr_in serverAddr;
	int  k;
	HANDLE handleThread[MAX_CLIENT];


	::WSAStartup( 0x202, &wsaData );

	for(k=0;k<MAX_CLIENT;k++){
		socketConnect[k] = INVALID_SOCKET;
		socketConnect[k] = ::socket( AF_INET, SOCK_STREAM, IPPROTO_TCP );
		if( socketConnect[k] == INVALID_SOCKET )
		{
			printf( "Cannot create socket %d !!\n", k);
			continue;
		}
	}

	//  ������ ������ ������ �����Ѵ�.
	::memset( &serverAddr, 0, sizeof( serverAddr ) );
	serverAddr.sin_family		= AF_INET;
	serverAddr.sin_addr.s_addr	= ::inet_addr( "127.0.0.1" ); //::htonl(INADDR_LOOPBACK); 
	serverAddr.sin_port			= ::htons( 8600 );

	for(k=0;k<MAX_CLIENT;k++){
		if( socketConnect[k] != INVALID_SOCKET){
			if(::connect( socketConnect[k], ( struct sockaddr* )&serverAddr, sizeof( serverAddr ) ) == SOCKET_ERROR )
			{
				printf( "Cannot connect to server %d !!\n", k);
				socketConnect[k] = INVALID_SOCKET;
				continue;
			}
			else{
// create thread for receive packets

			}
		}
	}



	//  ������ ����� �Ѵ�.
	int count=0;
	while(count++ < MAX_MESSAGE){
		char sendBuffer[127];
		int sentBytes;

		char recvBuffer[127];
		int  RecvBytes=0;


		for(k=0;k<MAX_CLIENT;k++){

			if(socketConnect[k] != INVALID_SOCKET){
				sprintf(sendBuffer, "%d> Test Message to server", k);
				if(count==MAX_MESSAGE)
					strcat(sendBuffer, "!");

				sentBytes = ::send( socketConnect[k], sendBuffer, ::strlen( sendBuffer ) + 1, 0 );
				if(sentBytes<0){
					::shutdown( socketConnect[k], SD_BOTH );
					::closesocket( socketConnect[k] );
					socketConnect[k]=INVALID_SOCKET;
				}
				else{
					printf( "%d> %d bytes sent.\n", k, sentBytes );


				}
			}
		}

//		Sleep(200);
	}

// close main() and threads gracefully using ::WaitForMultipleObjects()


	for(k=0;k<MAX_CLIENT;k++){
		::shutdown( socketConnect[k], SD_BOTH );
		::closesocket( socketConnect[k] );
	}
	::WSACleanup();

	printf("Server Connection Closed !\n");
	char temp[120];
	fgets(temp, 119, stdin);
}
