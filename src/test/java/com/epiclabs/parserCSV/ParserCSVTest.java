package com.epiclabs.parserCSV;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import static org.junit.Assert.*;

/**
 * Created by samu on 25/04/17.
 */
public class ParserCSVTest {
    @Before
    public void setUp() throws Exception {
        FileOutputStream out = new FileOutputStream("test");
        String file = new String("Ka0s,0123456789,23");

        out.write(file.getBytes());
        out.close();
    }

    @After
    public void tearDown() throws Exception {
        File file = new File("test");
        file.delete();
        file = new File("testOut");
        file.delete();

    }

    @Test
    public void parse() throws Exception {
        ParserCSV p = new ParserCSV();
        p.parse("test", "testOut");

        BufferedReader br = new BufferedReader(new FileReader("testOut"));
        String line = br.readLine();

        assertEquals("Ka0s,23", line);
    }

}