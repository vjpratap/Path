package com.Paths;

import java.io.IOException;
import java.util.*;

public class PathDB{


    public static Map<String, Set<String>> putValues(String fileName) throws IOException {
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        String fileContent = MyFileReader.readingFile(fileName);
        String[] devideByLines = fileContent.split("\r\n");
        for (String line : devideByLines) {
            String[] sourceDestination = line.split(",");
            if (map.containsKey(sourceDestination[0])) {
                map.get(sourceDestination[0]).add(sourceDestination[1]);
            } else {
                Set<String> list = new HashSet<String>();
                list.add(sourceDestination[1]);
                map.put(sourceDestination[0], list);
            }
        }
        return map;
    }
}

class countryDB{
//    Map<String, String> withCountry = new HashMap<String, String>();
    public static Map<String, String> putCountries(String fileName) throws IOException {
        Map<String, String> withCountry = new HashMap<String, String>();
        String fileContent = MyFileReader.readingFile(fileName);
        String[] devideByLines = fileContent.split("\r\n");
        for (String line : devideByLines){
            String[] cityAndCountry = line.split(",");
            String country = "[" + cityAndCountry[1] + "]";
            withCountry.put(cityAndCountry[0],country);
        }
        return withCountry;
    }
}