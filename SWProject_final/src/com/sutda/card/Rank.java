/*
두개의 카드를 가지고 패의 조합을 계산합니다.

monthOne , Two : 패의 월입니다.
propertyOne , Two : 패의 특성을 나타냅니다.
rank : 누가 이겼는지 확인을 쉽게 하기 위해서 만들었습니다. 낮을수록 좋은 패 입니다.

 */
package com.sutda.card;

public class Rank {

    private int monthOne;
    private int propertyOne;
    private int monthTwo;
    private int propertyTwo;
    private int rank;

    public Rank(Card one , Card two) {
        this.monthOne = one.getMonth();
        this.propertyOne = one.getProperty();
        this.monthTwo = two.getMonth();
        this.propertyTwo = two.getProperty();
    }

    public String checkPare(){
        if
                ( this.propertyOne == 2 && this.propertyTwo ==2
                && (this.monthOne == 3 && this.monthOne == 8)
                && (this.monthTwo == 3 && this.monthTwo == 8)
                )
        {
            this.rank = 100;
            return " 3 8 광땡 ";
        }else if
                (this.propertyOne == 2 && this.propertyTwo ==2
                && (this.monthOne == 3 || this.monthOne == 8 || this.monthOne == 1)
                && (this.monthTwo == 3 || this.monthTwo == 8 || this.monthTwo == 1)
                )
        {
            this.rank = 101;
            return " "+this.monthOne+" "+this.monthTwo+" 광땡 ";
        }

        if(this.monthOne == this.monthTwo){
            this.rank = 200;
            return this.monthOne+" 땡 ";
        }

        if((this.monthOne == 1 && this.monthTwo == 2 ) || (this.monthOne == 2 && this.monthTwo == 1 )){
            this.rank = 301;
            return " 알리 ";
        }else if((this.monthOne == 1 && this.monthTwo == 4 ) || (this.monthOne == 4 && this.monthTwo == 1 )){
            this.rank = 302;
            return " 독사 ";
        }else if((this.monthOne == 1 && this.monthTwo == 9 ) || (this.monthOne == 9 && this.monthTwo == 1 )){
            this.rank = 303;
            return " 구삥 ";
        }else if((this.monthOne == 1 && this.monthTwo == 10 ) || (this.monthOne == 10 && this.monthTwo == 1 )){
            this.rank = 303;
            return " 장삥 ";
        }else if((this.monthOne == 4 && this.monthTwo == 10 ) || (this.monthOne == 10 && this.monthTwo == 4 )){
            this.rank = 304;
            return " 장사 ";
        }else if((this.monthOne == 4 && this.monthTwo == 6 ) || (this.monthOne == 6 && this.monthTwo == 4 )){
            this.rank = 305;
            return " 장삥 ";
        }else if(this.monthOne + this.monthTwo == 10){
            this.rank = 306;
            return " 갑오 ";
        }else{
            int temp = this.monthOne + this.monthTwo;
            if(temp == 10) {
                this.rank = 500;
                return " 망통 ";
            }else{
                this.rank = 450;
                if((this.monthOne + this.monthTwo) > 10) {
                    this.rank = this.rank + 10 - (this.monthOne + this.monthTwo);
                }else{
                    this.rank -= (this.monthOne + this.monthTwo);
                }
                int ggut = (this.monthOne + this.monthTwo)>10? this.monthOne + this.monthTwo -10 : this.monthOne + this.monthTwo;
                return " "+ggut + "끗 ";
            }
        }


    }

    public int getRank() {
        return rank;
    }
}
