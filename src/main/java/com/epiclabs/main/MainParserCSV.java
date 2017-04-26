package com.epiclabs.main;

import com.epiclabs.test.ParserCSV;

/**
 * Created by samu on 26/04/17.
 */
public class MainParserCSV {

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
