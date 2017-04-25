package com.epiclabs.parserCSV;

import java.io.*;
import java.util.ArrayList;
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
    void parse(String origin, String csv) {
        List<String []> file;
        StringBuilder sb;

        try{
            file = readFile(origin);
        }catch(IOException ioe) {
            System.out.println("No se ha podido leer el fichero");
            return;
        }

        sb = transformFile(file);

        try {
            writeFile(sb, csv);
        }catch(IOException ioe) {
            System.out.println("No se ha podido escribir el fichero");
            return;
        }

        System.out.println("CONSEGUIDO");
    }

    /**
     * Read file and store it in a list with arrays, to manage information easily
     * @param filePath path where is the file ciphered
     * @return List where each row is an array with the information tokenized using as separator ","
     * @throws IOException If there is no file to read
     */
    private List<String []> readFile(String filePath) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        List<String []> list;

        list = null;

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

        System.out.println(score);

        return score;
    }


    /**
     * Write the information in a determined path
     * @param sb data to write
     * @param file path where we want to save the file
     * @throws IOException If there is a problem to write the file (permissions, ...)
     */
    private void writeFile(StringBuilder sb, String file) throws IOException{
        FileOutputStream out = new FileOutputStream(file);
        out.write(sb.toString().getBytes());
        out.close();
    }




    /**
     * MAIN
     * @param args
     */
    public static void main (String [] args) {
        ParserCSV parser = new ParserCSV();

        if(args.length == 2)
            parser.parse(args[0], args[1]);
        else
            System.out.println("El número de parametros deben ser 2");
    }

}
