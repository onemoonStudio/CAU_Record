# VS 설정해야 할 것들

## base
#### Header folder 
- header 파일
- lib 파일

#### resource folder
- 관련된 cpp 파일

#### debug 파일

debug 폴더에 여러 파일들을 넣어줘야 한다.
BMP sound 등등...

## properties 

1. SDL check -> No
1. lib 추가
    - dsound.lib (기본)
    - ws2_32.lib (기본)
    - ddraw.lib (추가)
    - dxguid.lib (추가)

