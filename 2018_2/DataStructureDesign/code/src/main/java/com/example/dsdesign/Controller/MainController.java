package com.example.dsdesign.Controller;

import com.example.dsdesign.DTO.ItemsTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class MainController {

    @RequestMapping(value = "/hello" , method = RequestMethod.GET)
    public String test(){
        return "hello world";
    }

    // TODO
    // return type and check type
    @RequestMapping(value = "/test" , method = RequestMethod.GET)
    public void test2() throws Exception{
        String targeURL;
        int areacode = 33; // 충북
        String mystart ="20181015";

        SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
        Date startDate = fm.parse(mystart);
        if(startDate.after(fm.parse("20181011"))) System.out.println("@@@@");



        int[] eve = new int[4];
        targeURL =
                "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?ServiceKey="
                        + "key"
                        +"&eventStartDate="+"20181015"
                        +"&eventEndDate="+"20181018"
                        +"&areaCode=" + areacode
                        +"&sigunguCode=&cat1=&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1";  // &_type=json
        try {
            Document document = Jsoup.connect(targeURL).get();
            Elements elements = document.select("item");
            System.out.println(elements.size());

            for(int i=0;i<elements.size();i++){
                for(int day =0;day<4;day++){
                    if((Integer.parseInt(elements.get(i).select("eventstartdate").text()) <= Integer.parseInt(mystart+day) )&&
                            (Integer.parseInt(mystart+day) <= Integer.parseInt(elements.get(i).select("eventenddate").text())) ){
                        eve[day] ++;
                    }

                }
            }


            System.out.println(Arrays.toString(eve));

        }catch (IOException e){
            System.out.println("Error !!");
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/ham")
    public void testham(){
        String[] hamil = hamiltonian();

    }

    // localhost:8080/go/20181015/6/20181025/6
    // 0 2 4 6 7 5 3 1 0
    @RequestMapping(value = "/go/{go}/{godays}/{back}/{backdays}")
    public String algorithm(
            @PathVariable(value = "go") String go ,
            @PathVariable(value = "godays") String godays ,
            @PathVariable(value = "back") String back ,
            @PathVariable(value = "backdays") String backdays
    ) throws Exception{

        StringBuilder sb = new StringBuilder();

//        Date s_start = format.parse(go);
        int s_start = Integer.parseInt(go);
        int s_place_length = 4;
        int s_days = Integer.parseInt(godays);
        int s_eve[][] = new int[s_days][s_place_length];

        int e_start = Integer.parseInt(back);
        int e_place_length = 4;
        int e_days = Integer.parseInt(backdays);
        int e_eve[][] = new int[e_days][e_place_length];



        // go -> 서울 충남 전북 전남 -> 0 2 4 6
        // 서울
        makeEventArr(0,s_eve,makeTartgetURL(1,s_start,s_start+s_days),s_start);
        // 충남
        makeEventArr(1,s_eve,makeTartgetURL(34,s_start,s_start+s_days),s_start);
        // 전북
        makeEventArr(2,s_eve,makeTartgetURL(37,s_start,s_start+s_days),s_start);
        // 전남
        makeEventArr(3,s_eve,makeTartgetURL(38,s_start,s_start+s_days),s_start);

        int[] s_max = new int[1];
        String[] s_realpath = {""};
        int[] s_howmany = {s_days,s_place_length};
        for(int i=0;i<s_place_length;i++){
            if(i == s_place_length-1) {
                for(int k=i;k<s_days;k++) s_max[0]+= s_eve[k][i];
            }else
                s_max[0] += s_eve[i][i];
        }

        int s_result = travel(s_max,0,0,0,s_eve,"",s_realpath,s_howmany);

        String[] s_real_result = s_realpath[0].split(">");
        if(s_real_result.length != s_days){
            for(int i=Integer.parseInt(s_real_result[s_real_result.length-1].split(",")[0]) ; i < s_days;i++)
                s_realpath[0] = s_real_result[0].concat((i+1) +","+s_real_result[s_real_result.length-1].split(",")[1] + ">");
        }
        for(int i=0;i<s_real_result.length;i++){
            String[] temp = s_real_result[i].split(",");
            if(temp[1].equals("0")) System.out.print((s_start+i)+" 서울 -> ");
            else if(temp[1].equals("1")) System.out.print((s_start+i)+" 충북 -> ");
            else if(temp[1].equals("2")) System.out.print((s_start+i)+" 전북 -> ");
            else System.out.print((s_start+i)+" 전남 -> ");
        }

        System.out.print("midpoint 경남 -> ");

        // back -> 경북 충북 강원도 서울 -> 5 3 1 0
        // 35 33 32 1
        makeEventArr(0,e_eve,makeTartgetURL(35,e_start,e_start+e_days),e_start);
        makeEventArr(1,e_eve,makeTartgetURL(33,e_start,e_start+e_days),e_start);
        makeEventArr(2,e_eve,makeTartgetURL(32,e_start,e_start+e_days),e_start);
        makeEventArr(3,e_eve,makeTartgetURL(1,e_start,e_start+e_days),e_start);

        int[] e_max = new int[1];
        String[] e_realpath = {""};
        int[] e_howmany = {e_days,e_place_length};
        for(int i=0;i<e_place_length;i++){
            if(i == e_place_length-1) {
                for(int k=i;k<e_days;k++) e_max[0]+= e_eve[k][i];
            }else
                e_max[0] += e_eve[i][i];
        }

        // 처음 max가 최대일 수 있다.
        int e_result = travel(e_max,0,0,0,e_eve,"",e_realpath,e_howmany);
        if(e_result == 0){
            for(int i=0;i<e_place_length;i++){
                if(i == e_place_length-1) {
                    for(int k=i;k<e_days;k++) {
                        e_realpath[0] = e_realpath[0].concat(k+","+i+">");
//                        if(k != e_days-1)
//                            e_realpath[0] = e_realpath[0].concat(k+","+i+">");
//                        else e_realpath[0] = e_realpath[0].concat(k+","+i);

                    }
                }else
                    e_realpath[0] = e_realpath[0].concat(i+","+i+">");
            }
        }


        String[] e_real_result = e_realpath[0].split(">");
        for(int i=0;i<e_real_result.length;i++){
            String[] temp = e_real_result[i].split(",");
            if(temp[1].equals("0")) System.out.print((e_start+i)+" 경북 -> ");
            else if(temp[1].equals("1")) System.out.print((e_start+i)+" 충북 -> ");
            else if(temp[1].equals("2")) System.out.print((e_start+i)+" 강원도 -> ");
            else System.out.print((e_start+i)+" 서울 -> ");
        }

        System.out.println();
        return "good";


    }

    public static int writePath(String hamPath , String go,String godays,String back,String backdays){

        return 0;
    }


    public static String makeTartgetURL(int areacode , int startDate, int endDate){
        return "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?ServiceKey="
                + "key"
                +"&eventStartDate=" + startDate
                +"&eventEndDate=" + endDate
                +"&areaCode=" + areacode
                +"&sigunguCode=&cat1=&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1";  // &_type=json
    }

    public static int idleEvent( int day , int place , int eve[][] ,int howMany[]){
        int max = 0;
        int howMD = howMany[0];
        int howMP = howMany[1];
        int where = place;

        for(int d = day;d<howMD;d++){
            int subMax = 0;
            for(int p = where;p<howMP;p++){
                if( eve[d][p] > subMax) subMax = eve[d][p];
            }
            max += subMax;
        }
        return max;
    }

    public static int travel( int[] max , int nowscore , int day ,int place , int[][] eve ,String paths ,String[] realpath , int howMany[]){
        int howMD = howMany[0];
        int howMP = howMany[1];
        paths = paths.concat(day+","+place+">");
        nowscore += eve[day][place];

        if(place == howMP-1 ){ // 마지막 지역으로 왔다면

            for(int d=day+1; d < howMD;d++){
                nowscore += eve[d][place];
            }
            if(max[0] < nowscore){
                max[0] = nowscore;
                realpath[0] = paths;
            }
            return nowscore;

        }else if (day == howMD-1){// 못가는 경우
            return 0;
        }else{ // 진행하는 경우
            int stay_idle = nowscore + idleEvent(day+1 , place  , eve , howMany);
            int across_idle = nowscore + idleEvent(day+1 ,place+1 , eve , howMany);

            if( stay_idle > max[0] && across_idle > max[0] ) { // 둘다 유망하다면
                return Math.max(
                        travel(max,nowscore , day+1 , place  , eve , paths , realpath , howMany) ,  // stay
                        travel(max,nowscore , day+1 , place+1 , eve , paths , realpath , howMany)    // across
                );
            }else if (stay_idle > max[0])
                return travel(max,nowscore , day+1 , place  , eve , paths , realpath , howMany);
            else if (across_idle > max[0])
                return travel(max,nowscore , day+1 , place+1  , eve, paths , realpath , howMany);
            else
                return 0;

        }

    }

    public static void makeEventArr( int evePlace , int eve[][] , String targetURL , int startdate) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        int days = eve.length;

        Document document = Jsoup.connect(targetURL).get();
        Elements elements = document.select("item");
        int eventLength = elements.size();
        for(int i=0;i<eventLength;i++){
            for(int day =0;day<days;day++){
                if(
                    format.parse(String.valueOf(startdate+day)).after(format.parse(elements.get(i).select("eventstartdate").text()))&& // 시작이 포함되는지
                    format.parse(String.valueOf(startdate+day)).before(format.parse(elements.get(i).select("eventenddate").text())) // 끝이 포함되는지
                 ){
                    eve[day][evePlace] ++;
                }

            }
        }
    }

    // Hamiltoniancode
    // 0 서울 1 강원도 2 충남 3 충북 4 전북 5 경북 6 전남 7 경남
    // areacode
    // 1 서울 , 32 강원도 , 33 충북 , 34 충남 , 35 경북 , 36 경남 , 37 전북 , 38 전남
    public static int hamToArea(int hamilCode){
        int result = 0;
        switch (hamilCode){
            case 0:
                result = 1;
                break;
            case 1:
                result = 32;
                break;
            case 2:
                result = 34;
                break;
            case 3:
                result = 33;
                break;
            case 4:
                result = 37;
                break;
            case 5:
                result = 35;
                break;
            case 6:
                result = 38;
                break;
            case 7:
            default:
                result = 36;
            break;
        }
        return result;
    }

    public static String[] hamiltonian(){
        int[][] w = new int[8][8];
        w[0][1] = 1;
        w[0][2] = 1;
        w[0][3] = 1;
        w[1][3] = 1;
        w[1][5] = 1;
        w[2][3] = 1;
        w[2][4] = 1;
        w[3][5] = 1;
        w[4][6] = 1;
        w[5][7] = 1;
        w[6][7] = 1;
        int[] path = new int[8];


        for(int i=0;i<8;i++){
            for(int k=0;k<8;k++){
                if(w[i][k] == 1 && (i != k))
                    w[k][i] = 1;
            }
        }

        String result = "";
        ArrayList<String> arrlist = new ArrayList<>();


        ham(0,w,path , result , arrlist);
        System.out.println(Arrays.toString(arrlist.toArray()));
        String[] returnResult = new String[arrlist.size()];
        for(int i=0;i<returnResult.length;i++){
            returnResult[i] = arrlist.get(i);
        }
        return returnResult;
    }

    public static void ham(int idx_i ,int[][] w, int[] path , String result ,ArrayList arrayList){

        int idx_j;
        int n = 8;

        if(promising(idx_i,w,path))
            if(idx_i == n-1){
                for(int num : path) result = result.concat(num+" ");
                result = result.concat("0");
                arrayList.add(result);
            }

            else
                for(idx_j = 1;idx_j < n ;idx_j++){
                    path[idx_i+1] = idx_j;
                    ham(idx_i+1,w , path , result ,arrayList);
                }
    }

    public static boolean promising(int idx_i , int[][] w , int[] path){

        int idx_j = 1;
        int n = 8;
        boolean result = true;

        if(idx_i == n-1 && !( w[path[n-1]][0] == 1) )
            result = false; // 처음 마지막 이어져있어야함
        else if( idx_i > 0 && !(w[path[idx_i-1]][path[idx_i]] == 1))
            result = false; // 이전 노드랑 이웃해야함
        else{ // 노드가 이미 선택되면 안됨
            while((idx_j < idx_i) && result){
                if(path[idx_i] == path[idx_j])
                    result = false;
                idx_j++;
            }
        }
        return result;
    }



}
