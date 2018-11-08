//
//  lecture_time.cpp
//
//  Created by 김현태 on 07/11/2018.
//  Copyright © 2018 onemoon. All rights reserved.
//
// 테스트 전에 알아둘 것 -> 나는 console에서 입력했기 때문에 \n이 포함된 string을 base로 잡았다는걸 생각
// 만약 readfile 형태로 할꺼면 숫자가 약간 달라진다.

#include <iostream>
#include "string"
using namespace std;


//void ReplaceStringInPlace(std::string& subject, const std::string& search,
//                          const std::string& replace) {
//    size_t pos = 0;
//    while ((pos = subject.find(search, pos)) != std::string::npos) {
//        subject.replace(pos, search.length(), replace);
//        pos += replace.length();
//    }
//}

int p_mat[207][207];

int weekToNum(string str){
    if(str.find("ㅇㅜㅓㄹ") != string::npos){
        return 0;
    } else if(str.find("ㅎㅗㅏ") != string::npos){
        return 1;
    } else if(str.find("ㅅㅜ") != string::npos){
        return 2;
    } else if(str.find("ㅁㅗㄱ") != string::npos){
        return 3;
    } else if(str.find("ㄱㅡㅁ") != string::npos){
        return 4;
    } else return -1;
}

int twoChToInt(char first , char second) {
    if(first < '0' || first > '9' || second < '0' || second > '9') return -1;
    return (first-'0')*10 + (second-'0');
}

void addPopulation(int type , string str , int mat[207][207]) {
    // weekMulti -1 리턴되면 주말이 아니다.
    // 각 주에는 41개의 타임이 있다.
    int weekMulti = weekToNum(str);
    int before_days = weekMulti*41;
    
    int strLen = str.length();
    int st_time , en_time;
    
    int charIdx = 0;
    for(int i=0 ; i<strLen ; i++)
        if(str.at(i) >= '0' && str.at(i) <='9') {
            charIdx = i;
            break;
        }
    
    if(type == 1) { // 월1,2,3 등등...
        st_time = str.at(charIdx)-'0';
        en_time = str.at(strLen-1)-'0';
        
        st_time = before_days+4*(st_time-1);
        en_time = before_days+4*en_time;
        
        
        
    } else {
        // 월(10:00 ~ 12:00)
        int st_h = twoChToInt(str.at(charIdx+1), str.at(charIdx+2));
        int st_m = twoChToInt(str.at(charIdx+4), str.at(charIdx+5));
        st_time = 4*(st_h-9)+(st_m/15);
        int en_h = twoChToInt(str.at(charIdx+7), str.at(charIdx+8));
        int en_m = twoChToInt(str.at(charIdx+10), str.at(charIdx+11));
        en_time = 4*(en_h-9)+(en_m/15);
        
    }
    
    cout<< before_days << " " << st_time <<" " << en_time << "\n";
    
}


int main(int argc, const char * argv[]) {
    // insert code here...
    cout << "Hello, World!\n";
    char inputStr[100];
    fgets(inputStr, sizeof(inputStr), stdin);
    string base(inputStr);
    
    
    // 특정 character 찾기
    // 2개의 수업이 있는지 확인
    string new_target =  " / ";
    size_t target_idx;
    string str_1 , str_2;
    bool divided_lec = false;
    
    if((target_idx = base.find( new_target )) != std::string::npos ) {
        str_1 = base.substr(0,target_idx);
        str_2 = base.substr(target_idx+3,base.length()-(target_idx+3)-1);
        divided_lec = !divided_lec;
    } else {
        // 아무것도 안해도 된다.
        // base 그대로 사용
    }
    
    // 타입체크
    int checkType = base.find("(") == string::npos ? 2:1;
    
    // 수업 명시 타입 확인
    // 숫자 타입 > 금7,8,9
    if(checkType == 1){
        if(divided_lec){
            cout << "str 1 :" << str_1 <<"@\n";
            cout << "str 2 :" << str_2 <<"@\n";
            addPopulation(1,str_1, p_mat);
            addPopulation(1,str_2, p_mat);
        } else {
            cout << "base : " << base <<"\n";
            addPopulation(1,base, p_mat);
        }
    } else {
        if(divided_lec){
            cout << "str 1 :" << str_1 <<"@\n";
            cout << "str 2 :" << str_2 <<"@\n";
            addPopulation(2,str_1, p_mat);
            addPopulation(2,str_2, p_mat);
        } else {
            cout << "base : " << base <<"\n";
            addPopulation(2,base, p_mat);
        }
        
    }
    
    
    return 0;
}
