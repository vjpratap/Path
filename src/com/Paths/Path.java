package com.Paths;

public class Path{
    public static void main(String[] args)throws Exception {
        String fileName = "file";
        if(args.length > 2)
            fileName = args[3];
        String[] arguments = {args[0],args[1],fileName};
        try{
            Root root = new Root(arguments);
            System.out.println(root.givePath());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}