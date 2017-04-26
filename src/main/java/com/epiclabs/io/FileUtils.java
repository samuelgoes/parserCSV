package com.epiclabs.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samu on 26/04/17.
 */
public class FileUtils {

    /**
     * Read file and store it in a list with arrays, to manage information easily
     * @param filePath path where is the file ciphered
     * @return List where each row is an array with the information tokenized using as separator ","
     * @throws IOException If there is no file to read
     */
    public List<String []> readFile(String filePath) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        List<String []> list;

        try {
            String line = br.readLine();
            list = new ArrayList<String []>();

            while (line != null) {
                String [] token = line.split(",");
                list.add(token);
                line = br.readLine();
            }
        } finally {
            br.close();
        }

        return list;
    }



    /**
     * Write the information in a determined path
     * @param sb data to write
     * @param file path where we want to save the file
     * @throws IOException If there is a problem to write the file (permissions, ...)
     */
    public void writeFile(StringBuilder sb, String file) throws IOException{
        FileOutputStream out = new FileOutputStream(file);
        out.write(sb.toString().getBytes());
        out.close();
    }

}
