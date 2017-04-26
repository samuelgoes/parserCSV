package com.epiclabs.test;

import com.epiclabs.io.FileUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by samu on 25/04/17.
 */
public class ParserCSV {

    /**
     * main method.
     * @param origin path where is the file with de information we want to parse
     * @param csv path where we want write the new file with the information deciphered
     */
    public void parse(String origin, String csv) {
        List<String []> file;
        StringBuilder sb;
        FileUtils utils;

        utils = new FileUtils();

        try{
            file = utils.readFile(origin);
        }catch(IOException ioe) {
            System.out.println("No se ha podido leer el fichero");
            return;
        }

        sb = transformFile(file);

        try {
            utils.writeFile(sb, csv);
        }catch(IOException ioe) {
            System.out.println("No se ha podido escribir el fichero");
            return;
        }

        System.out.println("CONSEGUIDO");
    }


    /**
     * Read each row of the file and transform it to the "clean" data
     * @return String builder with the information deciphered to save in a file
     */
    private StringBuilder transformFile(List<String []> file) {
        Iterator<String []> it;
        StringBuilder sb;

        sb = new StringBuilder();

        it = file.iterator();
        while(it.hasNext()){
            String [] line = it.next();
            sb.append(line[0]).append(",").append(getScore(line[1], line[2])).append("\n");
        }

        return sb;
    }


    /**
     * Read the information ciphered and deciphered using a simple mathematical algorithm
     * @param system
     * @param scoreHack
     * @return
     */
    private int getScore(String system, String scoreHack) {
        byte[] sco = scoreHack.getBytes();
        int score = 0;

        for(int i = 0 ; i < sco.length ; i++) {
            score += system.indexOf(sco[sco.length - 1 - i]) * Math.pow(system.length(), i);
        }

        //System.out.println(score);

        return score;
    }

}
