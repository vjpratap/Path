package com.Paths;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by vijayps on 3/10/2015.
 */
class Root{
    private Map<String, Set<String>> db;
//    private <String, Set<String>> db;
    private String error;
    private String[] args;
    public Root(String[] args) throws IOException {
        this.error = "";
        this.args = args;
        this.readDb();
//        this.db = PathDB.putValues(args[3]);
    }
    public void readDb(){
        try {
             db = PathDB.putValues(args[3]);
        } catch (IOException e) {
            error = e.getMessage();
        }
    }

    public List<String> getFlags(){
        List<String> flags = new ArrayList<String>();
        for (String arg : args){
            if(arg.substring(0,1).equals("-"))
                flags.add(arg);
        }
        return flags;
    }


    public boolean checkDestination(String city) {
        for(String s : db.keySet())
            if(db.get(s).contains(city))
                return true;
        return false;
    }

    public boolean checkCities(String city) {
        return (db.containsKey(city) || checkDestination(city));
    }

    public String givePath() {
        if(error.length()!=0)
            return error;
        String[] cities = {args[0], args[1]};
        for (String city : cities) {
            if (!checkCities(city))
                return "no city " + city;
        }
        String root = null;
        try {
            root = (!getFlags().contains("-c")) ? checkRoots(args[0], args[1], args[0]) : checkRootsWithContries(args[0],args[1]);
        } catch (IOException e) {
            return e.getMessage();
        }
        return (!root.equals("false")) ? root : oppositePath(args[1], args[0]);
    }

    public String checkRootsWithContries(String source, String destination) throws IOException {
        Map<String, String> withCountry;
        try{
            withCountry = countryDB.putCountries(args[5]);
        }catch (IOException e){
            throw new IOException(e.getMessage());
        }
        String path = source + withCountry.get(source);
        return fullPathWithCountry(source, destination,path, withCountry);
    }

    public String fullPathWithCountry(String source, String destination,String path ,Map<String, String> withCountry){
        while(db.get(source) != null){
            if ((db.get(source).contains(destination))){
                return path + "->" + destination + withCountry.get(destination);
            }
            for(String newSource : db.get(source)){
                path = path + "->" + newSource + withCountry.get(newSource);
                return fullPathWithCountry(newSource, destination, path, withCountry);
            }
        }
        return "false";
    }

    public String checkRoots(String source, String destination,String path){
        while(db.get(source) != null){
            if ((db.get(source).contains(destination))){
                return path + "->" + destination;
            }
            for(String newSource : db.get(source)){
                path = path + "->" + newSource;
                return checkRoots(newSource, destination,path);
            }
        }
        return "false";
    }

    public String oppositePath(String newSource, String newDestination){
        String root = checkRoots(newSource, newDestination, newSource);
        String[] rootSplit = root.split("->");
        String oppositeRoot = rootSplit[rootSplit.length - 1];
        for (int i = rootSplit.length - 2;i >= 0;i--) {
            oppositeRoot += "->" + rootSplit[i];
        }
        return oppositeRoot;
    }
}
