/*
현재 배경을 대리석 타일로 깔아놓았다.
발자국 bmp 만들어놨고, 이를 활용하면 될 것 같음
*/
#include <windows.h>
#include <windowsx.h>
#include <ddraw.h>
#include <stdio.h>

#include "ddutil.h"

#include <dsound.h>
#include "dsutil.h"

#define _GetKeyState( vkey ) HIBYTE(GetAsyncKeyState( vkey ))
#define _GetKeyPush( vkey )  LOBYTE(GetAsyncKeyState( vkey ))

HWND MainHwnd;

LPDIRECTDRAW         DirectOBJ;
LPDIRECTDRAWSURFACE  RealScreen;
LPDIRECTDRAWSURFACE  BackScreen;
LPDIRECTDRAWSURFACE  SpriteImage;
LPDIRECTDRAWSURFACE  BackGround;
LPDIRECTDRAWSURFACE  Gunship;
// edit when you want to add img
LPDIRECTDRAWSURFACE  Bricks;
LPDIRECTDRAWSURFACE  FootPrint;
LPDIRECTDRAWSURFACE  WhiteBack;



LPDIRECTDRAWCLIPPER	ClipScreen;

int gFullScreen = 0, Click = 0;
// set width and height of window
int gWidth = 480*2, gHeight = 480;

int MouseX = 30, MouseY = 30;

int u_dir = 1;

int WallsPos[6][12] = {0, };

////////////////////

LPDIRECTSOUND       SoundOBJ = NULL;
LPDIRECTSOUNDBUFFER SoundDSB = NULL;
DSBUFFERDESC        DSB_desc;

HSNDOBJ Sound[10];


BOOL _InitDirectSound(void)
{
	if (DirectSoundCreate(NULL, &SoundOBJ, NULL) == DS_OK)
	{
		if (SoundOBJ->SetCooperativeLevel(MainHwnd, DSSCL_PRIORITY) != DS_OK) return FALSE;

		memset(&DSB_desc, 0, sizeof(DSBUFFERDESC));
		DSB_desc.dwSize = sizeof(DSBUFFERDESC);
		DSB_desc.dwFlags = DSBCAPS_PRIMARYBUFFER | DSBCAPS_CTRLVOLUME | DSBCAPS_CTRLPAN;

		if (SoundOBJ->CreateSoundBuffer(&DSB_desc, &SoundDSB, NULL) != DS_OK) return FALSE;
		SoundDSB->SetVolume(DSBVOLUME_MAX); // DSBVOLUME_MIN
		SoundDSB->SetPan(DSBPAN_RIGHT);
		return TRUE;
	}
	return FALSE;
}

void _Play(int num)
{
	SndObjPlay(Sound[num], NULL);
}


BOOL Fail(HWND hwnd)
{
	ShowWindow(hwnd, SW_HIDE);
	MessageBox(hwnd, "DIRECT X 초기화에 실패했습니다.", "게임 디자인", MB_OK);
	DestroyWindow(hwnd);
	return FALSE;
}

// edit when you want to add img
void _ReleaseAll(void)
{
	if (DirectOBJ != NULL)
	{
		if (RealScreen != NULL)
		{
			RealScreen->Release();
			RealScreen = NULL;
		}
		if (SpriteImage != NULL)
		{
			SpriteImage->Release();
			SpriteImage = NULL;
		}
		if (BackGround != NULL)
		{
			BackGround->Release();
			BackGround = NULL;
		}

		if (Bricks != NULL)
		{
			Bricks->Release();
			Bricks = NULL;
		}
		if (FootPrint != NULL)
		{
			FootPrint->Release();
			FootPrint = NULL;
		}
		if (WhiteBack != NULL)
		{
			WhiteBack->Release();
			WhiteBack = NULL;
		}

		DirectOBJ->Release();
		DirectOBJ = NULL;
	}
}

// key control processes or mouse 
long FAR PASCAL WindowProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam)
{
	int Step = 10;


	switch (message)
	{
		/*
		case    WM_MOUSEMOVE    :   MouseX = LOWORD(lParam);
		MouseY = HIWORD(lParam);
		break;
		*/
	case	WM_LBUTTONDOWN: 	Click = 1;
		_Play(3);
		break;
	case	WM_KEYDOWN:
		switch (wParam)
		{
		case VK_ESCAPE:
		case VK_F12:
			PostMessage(hWnd, WM_CLOSE, 0, 0);
			return 0;

		case VK_LEFT:
			u_dir = 3;
			MouseX -= Step;
			return 0;

		case VK_RIGHT:
			u_dir = 1;
			MouseX += Step;
			return 0;

		case VK_UP:
			MouseY -= Step;
			u_dir = 0;
			return 0;

		case VK_DOWN:
			u_dir = 2;
			MouseY += Step;
			return 0;

		case VK_SPACE:
			Click = 1;
			_Play(3);
			break;
		}
		break;

	case    WM_DESTROY:  _ReleaseAll();
		PostQuitMessage(0);
		break;
	}
	return DefWindowProc(hWnd, message, wParam, lParam);
}

// basic setting
BOOL _GameMode(HINSTANCE hInstance, int nCmdShow, int x, int y, int bpp)
{
	HRESULT result;
	WNDCLASS wc;
	DDSURFACEDESC ddsd;
	DDSCAPS ddscaps;
	LPDIRECTDRAW pdd;

	wc.style = CS_HREDRAW | CS_VREDRAW | CS_DBLCLKS;
	wc.lpfnWndProc = WindowProc;
	wc.cbClsExtra = 0;
	wc.cbWndExtra = 0;
	wc.hInstance = hInstance;
	wc.hIcon = LoadIcon(NULL, IDI_APPLICATION);
	wc.hCursor = LoadCursor(NULL, IDC_ARROW);
	wc.hbrBackground = GetStockBrush(BLACK_BRUSH);
	wc.lpszMenuName = NULL;
	wc.lpszClassName = "h_master";
	RegisterClass(&wc);


	if (gFullScreen){
		if ((MainHwnd = CreateWindowEx(0, "h_master", NULL, WS_POPUP, 0, 0, GetSystemMetrics(SM_CXSCREEN),
			GetSystemMetrics(SM_CYSCREEN), NULL, NULL, hInstance, NULL)) == NULL)
			ExitProcess(1);
	}
	else{
		if ((MainHwnd = CreateWindow("h_master", "slient", WS_OVERLAPPEDWINDOW, 0, 0, x,
			y, NULL, NULL, hInstance, NULL)) == NULL)
			ExitProcess(1);
		SetWindowPos(MainHwnd, NULL, 100, 100, x, y, SWP_NOZORDER);
	}

	SetFocus(MainHwnd);
	ShowWindow(MainHwnd, nCmdShow);
	UpdateWindow(MainHwnd);
	//    ShowCursor( FALSE );

	result = DirectDrawCreate(NULL, &pdd, NULL);
	if (result != DD_OK) return Fail(MainHwnd);

	result = pdd->QueryInterface(IID_IDirectDraw, (LPVOID *)&DirectOBJ);
	if (result != DD_OK) return Fail(MainHwnd);


	// 윈도우 핸들의 협력 단계를 설정한다.
	if (gFullScreen){
		result = DirectOBJ->SetCooperativeLevel(MainHwnd, DDSCL_EXCLUSIVE | DDSCL_FULLSCREEN);
		if (result != DD_OK) return Fail(MainHwnd);

		result = DirectOBJ->SetDisplayMode(x, y, bpp);
		if (result != DD_OK) return Fail(MainHwnd);

		memset(&ddsd, 0, sizeof(ddsd));
		ddsd.dwSize = sizeof(ddsd);
		ddsd.dwFlags = DDSD_CAPS | DDSD_BACKBUFFERCOUNT;
		ddsd.ddsCaps.dwCaps = DDSCAPS_PRIMARYSURFACE | DDSCAPS_FLIP | DDSCAPS_COMPLEX;
		ddsd.dwBackBufferCount = 1;

		result = DirectOBJ->CreateSurface(&ddsd, &RealScreen, NULL);
		if (result != DD_OK) return Fail(MainHwnd);

		memset(&ddscaps, 0, sizeof(ddscaps));
		ddscaps.dwCaps = DDSCAPS_BACKBUFFER;
		result = RealScreen->GetAttachedSurface(&ddscaps, &BackScreen);
		if (result != DD_OK) return Fail(MainHwnd);
	}
	else{
		result = DirectOBJ->SetCooperativeLevel(MainHwnd, DDSCL_NORMAL);
		if (result != DD_OK) return Fail(MainHwnd);

		memset(&ddsd, 0, sizeof(ddsd));
		ddsd.dwSize = sizeof(ddsd);
		ddsd.dwFlags = DDSD_CAPS;
		ddsd.ddsCaps.dwCaps = DDSCAPS_PRIMARYSURFACE;
		ddsd.dwBackBufferCount = 0;

		result = DirectOBJ->CreateSurface(&ddsd, &RealScreen, NULL);
		if (result != DD_OK) return Fail(MainHwnd);

		memset(&ddsd, 0, sizeof(ddsd));
		ddsd.dwSize = sizeof(ddsd);
		ddsd.dwFlags = DDSD_CAPS | DDSD_HEIGHT | DDSD_WIDTH;
		ddsd.ddsCaps.dwCaps = DDSCAPS_OFFSCREENPLAIN;
		ddsd.dwWidth = x;
		ddsd.dwHeight = y;
		result = DirectOBJ->CreateSurface(&ddsd, &BackScreen, NULL);
		if (result != DD_OK) return Fail(MainHwnd);

		result = DirectOBJ->CreateClipper(0, &ClipScreen, NULL);
		if (result != DD_OK) return Fail(MainHwnd);

		result = ClipScreen->SetHWnd(0, MainHwnd);
		if (result != DD_OK) return Fail(MainHwnd);

		result = RealScreen->SetClipper(ClipScreen);
		if (result != DD_OK) return Fail(MainHwnd);

		SetWindowPos(MainHwnd, NULL, 100, 100, x, y, SWP_NOZORDER | SWP_NOACTIVATE);
	}


	return TRUE;
}


// communicate with server
extern void CommInit(int argc, char **argv);
extern void CommSend(char *sending);
extern void CommRecv(char *recvData);

void settingWall(){
	WallsPos[0][5] = 1; WallsPos[0][9] = 1;

	WallsPos[1][0] = 1; WallsPos[1][2] = 1; WallsPos[1][3] = 1; WallsPos[1][5] = 1;
	WallsPos[1][7] = 1; WallsPos[1][8] = 1; WallsPos[1][9] = 1; WallsPos[1][11] = 1;

	WallsPos[2][7] = 1; WallsPos[2][11] = 1;

	WallsPos[3][2] = 1; WallsPos[3][5] = 1; WallsPos[3][6] = 1; 
	WallsPos[3][4] = 1; WallsPos[3][9] = 1; WallsPos[3][10] = 1;

	WallsPos[4][6] = 1; WallsPos[4][7] = 1; WallsPos[4][11] = 1;

	WallsPos[5][2] = 1; WallsPos[5][4] = 1; WallsPos[5][9] = 1; WallsPos[5][10] = 1;

}

RECT userAndWall(int x, int y){
	RECT result;
	bool check = false;
	int StepSize = 10;
	int wpx, wpy;

	for (int i = 0; i < 6; i++){
		for (int j = 0; j < 12; j++){
			if (WallsPos[i][j] == 1 ){
				wpx = 80 * j + 40; wpy = 80 * i + 40;

				if ( wpx - 65 < x +25
					&& x+25 < wpx + 65 
					&& wpy + 65 > y +25
					&& wpy - 65 < y+25 ){
					check = true;
					switch (u_dir)
					{
					case 0:
						y += StepSize;
						break;
					case 1:
						x -= StepSize;
						break;
					case 2:
						y -= StepSize;
						break;
					case 3:
						x += StepSize;
						break;
					default:
						break;
					}
				}
				if (check) break;

			}
		}
	}

	result.left = x;
	result.top = y;
	result.right = result.left + 50;
	result.bottom = result.top + 50;

	return result;
}

// this function is called by process in every 30ms
void CALLBACK _GameProc(HWND hWnd, UINT message, UINT wParam, DWORD lParam)
{
	RECT DispRect = { 0, 0, gWidth, gHeight };

	RECT SpriteRect, dstRect, WinRect;
	RECT FootRect,FootDestRect;
	RECT WallRect, WallDestRect;
	char sendData[200];


	// left and top coordinates
	// fill background with marbleTile
	/*for (int i = 0; i < 12; i++){
		for (int k = 0; k < 6; k++)
			BackScreen->BltFast(80 * i, 80 * k, MarbleTile, &BackRect, DDBLTFAST_WAIT | DDBLTFAST_NOCOLORKEY);
	}*/

	// background 
	BackScreen->BltFast(0, 0, WhiteBack, &DispRect, DDBLTFAST_WAIT | DDBLTFAST_NOCOLORKEY);
	// Wall
	settingWall();

	WallRect.left = 0; WallRect.top = 0; WallRect.right = 80; WallRect.bottom = 80;

	for (int i = 0; i < 6; i++){
		for (int j = 0; j < 12; j++){
			if (WallsPos[i][j] == 1) {
				WallDestRect.left = j * 80; WallDestRect.top = i * 80;
				WallDestRect.right = WallDestRect.left + 80; WallDestRect.bottom = WallDestRect.top + 80;
				BackScreen->Blt(&WallDestRect, Bricks, &WallRect, DDBLT_WAIT | DDBLTFAST_SRCCOLORKEY, NULL);
			}
		}
	}

	// Character


	static int Frame = 0;
	/*SpriteRect.left = Frame * 100;
	SpriteRect.top = 0;
	SpriteRect.right = SpriteRect.left + 100;
	SpriteRect.bottom = 70;*/

	if (u_dir == 0){
		FootRect.left = 150;
		FootRect.top = 0;
		FootRect.right = 300;
		FootRect.bottom = 150;
	}
	else if (u_dir == 1){
		FootRect.left = 0;
		FootRect.top = 0;
		FootRect.right = 150;
		FootRect.bottom = 150;
	}
	else if (u_dir == 2){
		FootRect.left = 0;
		FootRect.top = 150;
		FootRect.right = 150;
		FootRect.bottom = 300;
	}
	else{
		FootRect.left = 150;
		FootRect.top = 150;
		FootRect.right = 300;
		FootRect.bottom = 300;
	}
	

	/*if (Click){
		if (++Frame >= 4){
			Frame = 0;
			Click = 0;
		}
	}*/

	if (MouseX <= 0) MouseX = 0;
	if (MouseX > gWidth - 50) MouseX = gWidth - 50;
	if (MouseY <= 25) MouseY = 25;
	if (MouseY > gHeight - 50) MouseY = gHeight - 50;

	//check user and wall
	
	RECT result = userAndWall(MouseX, MouseY);
	// character size = > 50 X 50 
	MouseX = result.left;
	MouseY = result.top;


	FootDestRect.left = MouseX;
	FootDestRect.top = MouseY;
	FootDestRect.right = FootDestRect.left + 50;
	FootDestRect.bottom = FootDestRect.top + 50;

	//BackScreen->BltFast(MouseX - 50, MouseY - 35, SpriteImage, &SpriteRect, DDBLTFAST_WAIT | DDBLTFAST_SRCCOLORKEY);
	BackScreen->Blt(&FootDestRect, FootPrint, &FootRect, DDBLT_WAIT | DDBLTFAST_SRCCOLORKEY, NULL);


	

	if (gFullScreen)
		RealScreen->Flip(NULL, DDFLIP_WAIT);
	else{
		GetWindowRect(MainHwnd, &WinRect);
		RealScreen->Blt(&WinRect, BackScreen, &DispRect, DDBLT_WAIT, NULL);
	}
}




int PASCAL WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow)
{
	MSG msg;

	if (!_GameMode(hInstance, nCmdShow, gWidth, gHeight, 32)) return FALSE;

	SpriteImage = DDLoadBitmap(DirectOBJ, "EXAM3_1.BMP", 0, 0);
	BackGround = DDLoadBitmap(DirectOBJ, "EXAM3_2.BMP", 0, 0);
	Gunship = DDLoadBitmap(DirectOBJ, "EXAM3_3.BMP", 0, 0);
	// edit when you want to add img
	Bricks = DDLoadBitmap(DirectOBJ, "bricks_24.BMP", 0, 0);
	FootPrint = DDLoadBitmap(DirectOBJ, "user_fp_4.BMP", 0, 0);
	WhiteBack = DDLoadBitmap(DirectOBJ, "whiteBack.BMP", 0, 0);


	// z-index 를 높히기 위해서 여기에 설정하는 것 같음
	DDSetColorKey(SpriteImage, RGB(0, 0, 0));
	DDSetColorKey(FootPrint, RGB(0, 0, 0));
	DDSetColorKey(Gunship, RGB(0, 0, 0));
	

	SetTimer(MainHwnd, 1, 30, _GameProc);

	CommInit(NULL, NULL);

	///////////////////

	if (_InitDirectSound())
	{
		Sound[0] = SndObjCreate(SoundOBJ, "MUSIC.WAV", 1);
		Sound[1] = SndObjCreate(SoundOBJ, "LAND.WAV", 2);
		Sound[2] = SndObjCreate(SoundOBJ, "GUN1.WAV", 2);
		Sound[3] = SndObjCreate(SoundOBJ, "KNIFE1.WAV", 2);
		Sound[4] = SndObjCreate(SoundOBJ, "DAMAGE1.WAV", 2);
		Sound[5] = SndObjCreate(SoundOBJ, "DAMAGE2.WAV", 2);

		//        SndObjPlay( Sound[0], DSBPLAY_LOOPING );
	}
	//////////////////

	while (!_GetKeyState(VK_ESCAPE))
	{

		if (PeekMessage(&msg, NULL, 0, 0, PM_NOREMOVE))
		{
			if (!GetMessage(&msg, NULL, 0, 0)) return msg.wParam;

			TranslateMessage(&msg);
			DispatchMessage(&msg);
		}
		//        else _GameProc();
	}
	DestroyWindow(MainHwnd);

	return TRUE;
}