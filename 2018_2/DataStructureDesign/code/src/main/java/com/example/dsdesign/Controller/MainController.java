package com.example.dsdesign.Controller;

import com.example.dsdesign.DTO.ItemsTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @RequestMapping(value = "/hello" , method = RequestMethod.GET)
    public String test(){
        return "hello world";
    }

    // TODO
    // return type and check type
//    @RequestMapping(value = "/test" , method = RequestMethod.GET)
//    public List<Map<String,String>> test2(){
//        String targeURL;
//        targeURL ="http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?ServiceKey=@@@@@@@@@@@@@@@@@@@@@@@@@&eventStartDate=20180918&eventEndDate=20180922&areaCode=1&sigunguCode=&cat1=&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1";
//        try {
//            Document document = Jsoup.connect(targeURL).get();
//            Elements elements = document.select("item");
//            Map<Integer,String> result = new HashMap<>();
//
//            for(int i=0;i<elements.size();i++){
//                result.put( i , elements.get(i).toString());
//            }
//            return result;
//
//        }catch (IOException e){
//            System.out.println("Error !!");
//            e.printStackTrace();
//        }
//        return null;
//    }

//    @RequestMapping(value = "/test" , method = RequestMethod.GET)
//    public Map<Integer,String> test2(){
//        String targeURL;
//        targeURL ="http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?ServiceKey=@@@@@@@@@@@@@@@@@&eventStartDate=20180918&eventEndDate=20180922&areaCode=1&sigunguCode=&cat1=&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1";
//        try {
//            Document document = Jsoup.connect(targeURL).get();
//            Elements elements = document.select("item");
//            Map<Integer,String> result = new HashMap<>();
//
//            for(int i=0;i<elements.size();i++){
//                result.put( i , elements.get(i).toString());
//            }
//            return result;
//
//        }catch (IOException e){
//            System.out.println("Error !!");
//            e.printStackTrace();
//        }
//        return null;
//    }

    @RequestMapping(value = "/testjson" , method = RequestMethod.GET)
    public List<ItemsTest> testjson(){
        List<ItemsTest> result = new ArrayList<>();
        for(int i=0;i<10;i++){
            ItemsTest test = new ItemsTest();
            test.setItemInfo(1);
            result.add(test);
        }
        return result;
    }

}
