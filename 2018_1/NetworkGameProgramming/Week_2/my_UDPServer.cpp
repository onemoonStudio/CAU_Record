#include <stdio.h>
#include <conio.h>
#include <string.h>
#include <Winsock2.h>

#define  ECHOMAX	255
void ErrorHandling(const char *message);


void main()
{
	WSADATA wsaData;
	SOCKET listenSocket;
	struct sockaddr_in echoServerAddr;
	struct sockaddr_in echoClientAddr;
	char echoBuffer[ECHOMAX];
	int receiveSize, clientAddrLen;

	//  네트워크를 초기화 한다.
	::WSAStartup(0x202, &wsaData);

	//  UDP 소켓을 생성한다.
	listenSocket =::socket(PF_INET,SOCK_DGRAM,0);
	if (listenSocket == INVALID_SOCKET)
		ErrorHandling("Error : socket() error");

	//  sockaddr_in 구조체의 정보를 설정한다.
	::memset(&echoServerAddr, 0, sizeof(echoServerAddr));
	echoServerAddr.sin_family = AF_INET;
	echoServerAddr.sin_addr.s_addr = htonl(INADDR_ANY);
	echoServerAddr.sin_port = htons(8600);

	if (::bind(listenSocket, (SOCKADDR *)&echoServerAddr, sizeof(echoServerAddr)) == SOCKET_ERROR)
		ErrorHandling("Error : bind() Error");

	while (1)
	{
		clientAddrLen = sizeof(echoClientAddr);

		//  클라이언트로부터 메시지가 도착하기를 기다린다.
		receiveSize = ::recvfrom(listenSocket, echoBuffer, ECHOMAX, 0,
			(SOCKADDR *)&echoClientAddr, &clientAddrLen);
		if (receiveSize < 0)
			continue;
		// recvfrom 을 통해서 echoClientAddr 에 관한 정보가 자동으로 생성이 된다는 것을 알아두자
		printf("Handling client - %s\n>%d Bytes : %s\n", ::inet_ntoa(echoClientAddr.sin_addr), receiveSize, echoBuffer);

		//  받은 메시지를 클라이언트로 되돌려 보낸다.
		::sendto(listenSocket, echoBuffer, receiveSize, 0, 
			(SOCKADDR *)&echoClientAddr, sizeof(echoClientAddr));


	}
	//system("pause");
	//  소켓의 입/출력 버퍼를 비우고 비활성화 시킨다.
	::shutdown(listenSocket, SD_BOTH);

	//  소켓 작업을 종료한다.
	::closesocket(listenSocket);

	::WSACleanup();

	
}

void ErrorHandling(const char *message) {
	fputs(message, stderr);
	fputc('\n', stderr);
	exit(1);
}