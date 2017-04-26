package com.epiclabs.test;

import com.epiclabs.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by samu on 26/04/17.
 */
public class FileUtilsTest {

    private final static String FILE_ORIGIN_NAME = "test";
    private final static String FILE_TARGET_NAME = "testOut";
    private final static String ORIGIN_DATA = "Ka0s,0123456789,23\nSmoKe,01,01100";


    @Before
    public void setUp() throws Exception {
        FileOutputStream out = new FileOutputStream(FILE_ORIGIN_NAME);

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
    public void readFile() throws Exception {
        FileUtils fu;
        List<String []> data;
        String [] line;

        fu = new FileUtils();
        data = fu.readFile(FILE_ORIGIN_NAME);

        line = data.get(0);
        assertEquals("Ka0s", line[0]);
        assertEquals("0123456789", line[1]);
        assertEquals("23", line[2]);

        line = data.get(1);
        assertEquals("SmoKe", line[0]);
        assertEquals("01", line[1]);
        assertEquals("01100", line[2]);

    }

    @Test
    public void writeFile() throws Exception {
        FileUtils fu;
        StringBuilder sb;
        BufferedReader br;
        String line;

        System.out.println("Line: test");

        fu = new FileUtils();
        sb = new StringBuilder(ORIGIN_DATA);

        fu.writeFile(sb, FILE_TARGET_NAME);

        br = new BufferedReader(new FileReader(FILE_TARGET_NAME));

        assertEquals("Ka0s,0123456789,23", br.readLine());
        assertEquals("SmoKe,01,01100", br.readLine());
    }

}