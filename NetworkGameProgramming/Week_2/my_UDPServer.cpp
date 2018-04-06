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

	//  ��Ʈ��ũ�� �ʱ�ȭ �Ѵ�.
	::WSAStartup(0x202, &wsaData);

	//  UDP ������ �����Ѵ�.
	listenSocket =::socket(PF_INET,SOCK_DGRAM,0);
	if (listenSocket == INVALID_SOCKET)
		ErrorHandling("Error : socket() error");

	//  sockaddr_in ����ü�� ������ �����Ѵ�.
	::memset(&echoServerAddr, 0, sizeof(echoServerAddr));
	echoServerAddr.sin_family = AF_INET;
	echoServerAddr.sin_addr.s_addr = htonl(INADDR_ANY);
	echoServerAddr.sin_port = htons(8600);

	if (::bind(listenSocket, (SOCKADDR *)&echoServerAddr, sizeof(echoServerAddr)) == SOCKET_ERROR)
		ErrorHandling("Error : bind() Error");

	while (1)
	{
		clientAddrLen = sizeof(echoClientAddr);

		//  Ŭ���̾�Ʈ�κ��� �޽����� �����ϱ⸦ ��ٸ���.
		receiveSize = ::recvfrom(listenSocket, echoBuffer, ECHOMAX, 0,
			(SOCKADDR *)&echoClientAddr, &clientAddrLen);
		if (receiveSize < 0)
			continue;
		// recvfrom �� ���ؼ� echoClientAddr �� ���� ������ �ڵ����� ������ �ȴٴ� ���� �˾Ƶ���
		printf("Handling client - %s\n>%d Bytes : %s\n", ::inet_ntoa(echoClientAddr.sin_addr), receiveSize, echoBuffer);

		//  ���� �޽����� Ŭ���̾�Ʈ�� �ǵ��� ������.
		::sendto(listenSocket, echoBuffer, receiveSize, 0, 
			(SOCKADDR *)&echoClientAddr, sizeof(echoClientAddr));


	}
	//system("pause");
	//  ������ ��/��� ���۸� ���� ��Ȱ��ȭ ��Ų��.
	::shutdown(listenSocket, SD_BOTH);

	//  ���� �۾��� �����Ѵ�.
	::closesocket(listenSocket);

	::WSACleanup();

	
}

void ErrorHandling(const char *message) {
	fputs(message, stderr);
	fputc('\n', stderr);
	exit(1);
}