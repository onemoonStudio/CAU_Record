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

LPDIRECTDRAWCLIPPER	ClipScreen;

int gFullScreen=0, Click=0;
int gWidth=640*2, gHeight=480;
int MouseX=100, MouseY=gHeight/2;

////////////////////

LPDIRECTSOUND       SoundOBJ = NULL;
LPDIRECTSOUNDBUFFER SoundDSB = NULL;
DSBUFFERDESC        DSB_desc;

HSNDOBJ Sound[10];


BOOL _InitDirectSound( void )
{
    if ( DirectSoundCreate(NULL,&SoundOBJ,NULL) == DS_OK )
    {
        if (SoundOBJ->SetCooperativeLevel(MainHwnd,DSSCL_PRIORITY)!=DS_OK) return FALSE;

        memset(&DSB_desc,0,sizeof(DSBUFFERDESC));
        DSB_desc.dwSize = sizeof(DSBUFFERDESC);
        DSB_desc.dwFlags = DSBCAPS_PRIMARYBUFFER | DSBCAPS_CTRLVOLUME | DSBCAPS_CTRLPAN;

        if (SoundOBJ->CreateSoundBuffer(&DSB_desc,&SoundDSB,NULL)!=DS_OK) return FALSE;
        SoundDSB -> SetVolume(DSBVOLUME_MAX); // DSBVOLUME_MIN
        SoundDSB -> SetPan(DSBPAN_RIGHT);
        return TRUE;
    }
    return FALSE;
}

void _Play( int num )
{
    SndObjPlay( Sound[num], NULL );
}

////////////////////////


BOOL Fail( HWND hwnd )
{
    ShowWindow( hwnd, SW_HIDE );
    MessageBox( hwnd, "DIRECT X 초기화에 실패했습니다.", "게임 디자인", MB_OK );
    DestroyWindow( hwnd );
    return FALSE;
}

void _ReleaseAll( void )
{
    if ( DirectOBJ != NULL )
    {
        if ( RealScreen != NULL )
        {
            RealScreen->Release();
            RealScreen = NULL;
        }
        if ( SpriteImage != NULL )
        {
            SpriteImage->Release();
            SpriteImage = NULL;
        }
        if ( BackGround != NULL )
        {
            BackGround->Release();
            BackGround = NULL;
        }
        DirectOBJ->Release();
        DirectOBJ = NULL;
    }
}

long FAR PASCAL WindowProc( HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam )
{
	int Step=5;


    switch ( message )
    {
/*
        case    WM_MOUSEMOVE    :   MouseX = LOWORD(lParam);
                                    MouseY = HIWORD(lParam);
                                    break;
*/
		case	WM_LBUTTONDOWN	: 	Click=1;
									_Play( 3 );
									break;
        case	WM_KEYDOWN:            
            switch (wParam)
            {
                case VK_ESCAPE:
                case VK_F12: 
                    PostMessage(hWnd, WM_CLOSE, 0, 0);
                    return 0;            

				case VK_LEFT: 
					MouseX-=Step;
					return 0;

                case VK_RIGHT: 
					MouseX+=Step;
					return 0;

                case VK_UP:
					MouseY-=Step;	
					return 0;

                case VK_DOWN: 
					MouseY+=Step;
					return 0;

				case VK_SPACE:
					Click=1;
					_Play( 3 );
					break;
			}
            break;

        case    WM_DESTROY      :  _ReleaseAll();
                                    PostQuitMessage( 0 );
                                    break;
    }
    return DefWindowProc( hWnd, message, wParam, lParam );
}

BOOL _GameMode( HINSTANCE hInstance, int nCmdShow, int x, int y, int bpp )
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
    wc.hIcon = LoadIcon( NULL, IDI_APPLICATION );
    wc.hCursor = LoadCursor( NULL, IDC_ARROW );
    wc.hbrBackground = GetStockBrush(BLACK_BRUSH);
    wc.lpszMenuName = NULL;
    wc.lpszClassName = "TEST";
    RegisterClass( &wc );


	if(gFullScreen){
		if((MainHwnd=CreateWindowEx (0, "TEST", NULL, WS_POPUP, 0, 0, GetSystemMetrics(SM_CXSCREEN), 
				GetSystemMetrics(SM_CYSCREEN), NULL, NULL, hInstance, NULL ))==NULL)
			ExitProcess(1);
	}
	else{
		if((MainHwnd=CreateWindow("TEST", "Slave", WS_OVERLAPPEDWINDOW, 0, 0, x, 
									y, NULL, NULL, hInstance, NULL))==NULL)
			ExitProcess(1);
		SetWindowPos(MainHwnd, NULL, 100, 100, x, y, SWP_NOZORDER);
	}

    SetFocus( MainHwnd );
    ShowWindow( MainHwnd, nCmdShow );
    UpdateWindow( MainHwnd );
//    ShowCursor( FALSE );

    result = DirectDrawCreate( NULL, &pdd, NULL );
    if ( result != DD_OK ) return Fail( MainHwnd );

    result = pdd->QueryInterface(IID_IDirectDraw, (LPVOID *) &DirectOBJ);
    if ( result != DD_OK ) return Fail( MainHwnd );


	// 윈도우 핸들의 협력 단계를 설정한다.
	if(gFullScreen){
	    result = DirectOBJ->SetCooperativeLevel( MainHwnd, DDSCL_EXCLUSIVE | DDSCL_FULLSCREEN );
		if ( result != DD_OK ) return Fail( MainHwnd );

		result = DirectOBJ->SetDisplayMode( x, y, bpp);
		if ( result != DD_OK ) return Fail( MainHwnd );

		memset( &ddsd, 0, sizeof(ddsd) );
		ddsd.dwSize = sizeof( ddsd );
		ddsd.dwFlags = DDSD_CAPS | DDSD_BACKBUFFERCOUNT;
		ddsd.ddsCaps.dwCaps = DDSCAPS_PRIMARYSURFACE | DDSCAPS_FLIP | DDSCAPS_COMPLEX;
		ddsd.dwBackBufferCount = 1;

	    result = DirectOBJ -> CreateSurface( &ddsd, &RealScreen, NULL );
	   if ( result != DD_OK ) return Fail( MainHwnd );

		memset( &ddscaps, 0, sizeof(ddscaps) );
		ddscaps.dwCaps = DDSCAPS_BACKBUFFER;
		result = RealScreen -> GetAttachedSurface( &ddscaps, &BackScreen );
		if ( result != DD_OK ) return Fail( MainHwnd );
	}
	else{
	    result = DirectOBJ->SetCooperativeLevel( MainHwnd, DDSCL_NORMAL );
		if ( result != DD_OK ) return Fail( MainHwnd );

		memset( &ddsd, 0, sizeof(ddsd) );
	    ddsd.dwSize = sizeof( ddsd );
		ddsd.dwFlags = DDSD_CAPS;
	    ddsd.ddsCaps.dwCaps = DDSCAPS_PRIMARYSURFACE;
		ddsd.dwBackBufferCount = 0;

		result = DirectOBJ -> CreateSurface( &ddsd, &RealScreen, NULL );
	    if(result != DD_OK) return Fail(MainHwnd);

		memset( &ddsd, 0, sizeof(ddsd) );
		ddsd.dwSize = sizeof(ddsd);
	    ddsd.dwFlags = DDSD_CAPS | DDSD_HEIGHT | DDSD_WIDTH;
		ddsd.ddsCaps.dwCaps = DDSCAPS_OFFSCREENPLAIN;
		ddsd.dwWidth = x; 
		ddsd.dwHeight = y;
		result = DirectOBJ->CreateSurface( &ddsd, &BackScreen, NULL );
		if ( result != DD_OK ) return Fail( MainHwnd );

		result = DirectOBJ->CreateClipper( 0, &ClipScreen, NULL);
		if ( result != DD_OK ) return Fail( MainHwnd );

		result = ClipScreen->SetHWnd( 0, MainHwnd );
		if ( result != DD_OK ) return Fail( MainHwnd );

		result = RealScreen->SetClipper( ClipScreen );
		if ( result != DD_OK ) return Fail( MainHwnd );

		SetWindowPos(MainHwnd, NULL, 100, 100, x, y, SWP_NOZORDER | SWP_NOACTIVATE); 
	}


    return TRUE;
}


extern void CommInit(int argc, char **argv);
extern void CommSend(char *sending);
extern void CommRecv(char *recvData);

static int Rcount = 0;
static int arr[21][9] = { 0, };
static int character[4] = {0,};
static int coll = 0;
static int Collision = 0;


void _GameProcDraw(char *recvData)
{
    RECT BackRect = { 0, 0, 640, 480 };
	RECT DispRect = { 0, 0, gWidth, gHeight };
	RECT SpriteRect, dstRect, WinRect;


	BackScreen -> BltFast(0, 0, BackGround, &BackRect, DDBLTFAST_WAIT | DDBLTFAST_NOCOLORKEY); 
	BackScreen -> BltFast(640, 0, BackGround, &BackRect, DDBLTFAST_WAIT | DDBLTFAST_NOCOLORKEY); 

	/* enter codes for received data for splite and stones */

	sscanf(recvData, "%d %d %d %d %d %d %d %d %d", &arr[Rcount][0], &arr[Rcount][1], &arr[Rcount][2], &arr[Rcount][3]
		, &arr[Rcount][4], &arr[Rcount][5], &arr[Rcount][6], &arr[Rcount][7], &arr[Rcount][8]);
	Rcount++;
	if (Rcount == 20) Rcount = 0;


	// 캐릭터
	SpriteRect.left = arr[0][1];
	SpriteRect.top = arr[0][2];
	SpriteRect.right = arr[0][3];
	SpriteRect.bottom = arr[0][4];
	MouseX = arr[0][5];
	MouseY = arr[0][6];

	BackScreen->BltFast(MouseX - 50, MouseY - 35, SpriteImage, &SpriteRect, DDBLTFAST_WAIT | DDBLTFAST_SRCCOLORKEY);


	for (int k = 1; k < 21; k++){

		SpriteRect.left = arr[k][1];
		SpriteRect.top = arr[k][2];
		SpriteRect.right = arr[k][1] + 30;
		SpriteRect.bottom = arr[k][2] + 30;

		dstRect.left = arr[k][5];
		dstRect.top = arr[k][6];
		dstRect.right = dstRect.left + 30 + arr[k][4];
		dstRect.bottom = dstRect.top + 30 + arr[k][4];

		BackScreen->Blt(&dstRect, Gunship, &SpriteRect, DDBLT_WAIT | DDBLT_KEYSRC, NULL);

	}

	

	// 돌 - 20개

	/*for (int k = 1; k <= 20; k++){

		SpriteRect.left = arr[k][1];
		SpriteRect.top = arr[k][2];
		SpriteRect.right = arr[k][1] + 30;
		SpriteRect.bottom = arr[k][2] + 30;

		dstRect.left = arr[k][5];
		dstRect.top = arr[k][6];
		dstRect.right = dstRect.left + 30 + arr[k][4];
		dstRect.bottom = dstRect.top + 30 + arr[k][4];

		BackScreen->Blt(&dstRect, Gunship, &SpriteRect, DDBLT_WAIT | DDBLT_KEYSRC, NULL);

	}
	Collision = arr[20][7];
	coll = arr[20][8];
	

	if (Rcount == 20) Rcount = 0;

	if (coll){
		if (!Collision){
			Collision = 1;
			_Play(4);
		}
	}
	else
		Collision = 0;*/

	
	




	GetWindowRect(MainHwnd, &WinRect);
	RealScreen->Blt(&WinRect, BackScreen, &DispRect, DDBLT_WAIT, NULL ); 
}



int PASCAL WinMain( HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow )
{
    MSG msg;

    if ( !_GameMode(hInstance, nCmdShow, gWidth, gHeight, 32) ) return FALSE;

    SpriteImage = DDLoadBitmap( DirectOBJ, "EXAM3_1.BMP", 0, 0 );
    BackGround  = DDLoadBitmap( DirectOBJ, "EXAM3_2.BMP", 0, 0 );
    Gunship  = DDLoadBitmap( DirectOBJ, "EXAM3_3.BMP", 0, 0 );

    DDSetColorKey( SpriteImage, RGB(0,0,0) );
    DDSetColorKey( Gunship, RGB(0,0,0) );

	CommInit(NULL, NULL);

///////////////////

    if ( _InitDirectSound() )
    {
        Sound[0] = SndObjCreate(SoundOBJ,"MUSIC.WAV",1);
        Sound[1] = SndObjCreate(SoundOBJ,"LAND.WAV",2);
        Sound[2] = SndObjCreate(SoundOBJ,"GUN1.WAV",2);
        Sound[3] = SndObjCreate(SoundOBJ,"KNIFE1.WAV",2);
        Sound[4] = SndObjCreate(SoundOBJ,"DAMAGE1.WAV",2);
        Sound[5] = SndObjCreate(SoundOBJ,"DAMAGE2.WAV",2);

//        SndObjPlay( Sound[0], DSBPLAY_LOOPING );
    }
//////////////////

    while ( !_GetKeyState(VK_ESCAPE) )
    {

        if ( PeekMessage(&msg, NULL, 0, 0, PM_NOREMOVE) )
        {
            if ( !GetMessage(&msg, NULL, 0, 0) ) return msg.wParam;

            TranslateMessage( &msg );
            DispatchMessage( &msg );
        }
//        else _GameProc();
    }
    DestroyWindow( MainHwnd );

    return TRUE;
}
