package configuration.gongdel.josn.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gongdel.josn.jackson.model.Car;
import com.gongdel.josn.jackson.model.CarEngine;
import com.gongdel.josn.jackson.model.CarFleet;
import configuration.gongdel.josn.jackson.model.Car;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppMain {

    public static void main(String[] args) {
        CarFleet carFleet = getCarFleet();

        ObjectMapper mapper = new ObjectMapper();
        // 객체를 file에 쓰기
        try {
            mapper.writeValue(new File("result.json"), carFleet); // 기본 JSON - {"cars":[{"name":"Audi","model":"2010","cost":30000,~~~~]}
            //mapper. writerWithDefaultPrettyPrinter().writeValue(new File("result.json"), carFleet); // 꾸며진 JSON
            /** 위의 출력은 가독성이 좋지 않기 때문에 writeWithDefaultPrettyPrint를 사용하여 깔끔한 JSON 출력이 가능
                   {
                 "cars" : [ {
                 "name" : "Audi",
                 "model" : "2010",
                 "cost" : 30000,
                 "colors" : [ "Grey", "white" ],
                 "engine" : {
                 "type" : "Diesel",
                 "power" : "88KWH"
                 }
                 }, {
                 "name" : "Jaguar",
                 "model" : "2013",
                 "cost" : 60000,
                 "colors" : [ "Grey", "white" ],
                 "engine" : {
                 "type" : "Diesel",
                 "power" : "120KWH"
                 }
                 } ]
                 }
             */
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 파일로부터 객체 읽기
        CarFleet value = null;
        try {
            value = mapper.readValue(new File("result.json"), CarFleet.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(value);
    }

    public static CarFleet getCarFleet() {
        Car car1 = new Car();
        car1.setName("Audi");
        car1.setModel("2010");
        car1.setCost(30000);


        CarEngine engine1 = new CarEngine();
        engine1.setPower("88KWH");
        engine1.setType("Diesel");
        car1.setEngine(engine1);

        Car car2 = new Car();
        car2.setName("Jaguar");
        car2.setModel("2013");
        car2.setCost(60000);

        CarEngine engine2 = new CarEngine();
        engine2.setPower("120KWH");
        engine2.setType("Diesel");
        car2.setEngine(engine2);

        List<String> colors = new ArrayList<String>();
        colors.add("Grey");
        colors.add("white");
        car1.setColors(colors);
        car2.setColors(colors);

        CarFleet carFleet = new CarFleet();
        carFleet.getCars().add(car1);
        carFleet.getCars().add(car2);

        return carFleet;
    }
}
