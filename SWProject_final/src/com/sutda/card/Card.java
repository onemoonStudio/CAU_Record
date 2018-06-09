/*
* 패의 특징을 담은 클래스 입니다.
* 섯다 패의 경우에 몇월인지 그리고 해당 카드의 특수 기능이 있는지 굉장히 중요합니다.
* 따라서 이를 보기 쉽게 나타내었고, print를 통해서 카드를 직관적으로 확인할 수 있습니다.
* */

package com.sutda.card;

public class Card {
    private int month;
    private int property;

    public Card(int month , int property){
        this.month = month;
        this.property = property;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getProperty() {
        return property;
    }

    public void setProperty(int property) {
        this.property = property;
    }

    public String checkProperty(){
        if(this.property == 1 ) return "일반 패 ";
        else
            switch (this.month){
                case 1:
                    return "소나무에 두루미 - 光 ";
                case 2:
                    return "매화에 휘파람 새 ";
                case 3:
                    return "벛꽃에 막 - 光 ";
                case 4:
                    return "등나무에 두견새 ";
                case 5:
                    return "창포에 다리 ";
                case 6:
                    return "모란에 나비 ";
                case 7:
                    return "싸리나무에 맷돼지 ";
                case 8:
                    return "억새에 기러기 - 光 ";
                case 9:
                    return "국화에 술잔 ";
                case 10:
                    return "단풍에 사슴 ";
                default:
                    return "error";
            }


    }
    public void printCard(){
        System.out.println(this.month + " 月 ( "+this.checkProperty()+")");
    }
}
