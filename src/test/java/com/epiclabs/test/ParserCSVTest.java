package com.epiclabs.test;

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

    private final static String FILE_ORIGIN_NAME = "test";
    private final static String FILE_TARGET_NAME = "testOut";
    private final static String ORIGIN_DATA = "Ka0s,0123456789,23";
    private final static String EXPECTED_DATA = "Ka0s,23";


    @Before
    public void setUp() throws Exception {
        FileOutputStream out;

        out = new FileOutputStream(FILE_ORIGIN_NAME);

        out.write(ORIGIN_DATA.getBytes());
        out.close();
    }

    @After
    public void tearDown() throws Exception {
        File file = new File(FILE_ORIGIN_NAME);
        file.delete();
        file = new File(FILE_TARGET_NAME);
        file.delete();
    }

    @Test
    public void parse() throws Exception {
        ParserCSV p = new ParserCSV();
        p.parse(FILE_ORIGIN_NAME, FILE_TARGET_NAME);

        BufferedReader br = new BufferedReader(new FileReader(FILE_TARGET_NAME));
        String line = br.readLine();

        assertEquals(EXPECTED_DATA, line);
    }

}