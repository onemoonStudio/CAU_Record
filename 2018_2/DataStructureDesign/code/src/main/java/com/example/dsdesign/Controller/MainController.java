package com.example.dsdesign.Controller;

import com.example.dsdesign.DTO.ItemsTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/go/{go}/{godays}/{back}/{backdays}")
    public String algorithm(
            @RequestParam(value = "go") String go ,
            @RequestParam(value = "godays") String godays ,
            @RequestParam(value = "back") String back ,
            @RequestParam(value = "backdays") String backdays
    ) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date s_start = format.parse(go);
        int s_days = Integer.parseInt(godays);

        Date e_start = format.parse(back);
        int e_days = Integer.parseInt(backdays);


        // Hamiltoniancode
        // 0 서울 1 강원도 2 충남 3 충북 4 전북 5 경북 6 전남 7 경남
        // areacode
        // 1 서울 , 32 강원도 , 33 충북 , 34 충남 , 35 경북 , 36 경남 , 37 전북 , 38 전남
        // go -> 서울 충남 전북 전남
        // back -> 경북 충북 강원도 서울






        return "good";


    }


    public static String makeTartgetURL(int areacode ){
        return "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?ServiceKey="
                + "key"
                +"&eventStartDate="+"20181015"
                +"&eventEndDate="+"20181018"
                +"&areaCode=" + areacode
                +"&sigunguCode=&cat1=&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1";  // &_type=json
    }

}
