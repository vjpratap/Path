package com.Paths;
import java.io.*;
/**
 * Created by vijayps on 3/7/2015.
 */
public class MyFileReader {
    public static String readingFile(String file) throws IOException {
        File thisFile = new File("src/"+file);
        FileReader fr = null;
        try {
            fr = new FileReader(thisFile);
        } catch(Exception e) {
            throw new IOException("No database named " +  file + " found.");
         }

        BufferedReader br=new BufferedReader(fr);
        int length = (int)thisFile.length();
        char cbuf[] = new char[length];
        br.read(cbuf, 0, length);
        return new String(cbuf);
    }
}
