package com.moatcrew.restbdd.datasourcing;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by E-JENN on 02/08/2016.
 */
public class CsvReaderTest {

    private boolean hasHeader;
    private char separator ;
    private char quotechar;

     CsvReader reader = new CsvReader(hasHeader,separator,quotechar);


    @Test
    public void getCsvHeader() throws Exception {
        System.out.println(reader.getCsvHeader("testData.csv"));

    }
}