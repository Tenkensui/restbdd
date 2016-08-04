package com.moatcrew.restbdd.datasourcing;

import au.com.bytecode.opencsv.CSVReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by tensh on 01/08/2016.
 */
public class CsvReader {

    private static Log logger = LogFactory.getLog(CsvReader.class);
    private boolean hasHeader;
    private char separator;
    private char quotechar;

    /**
     * Reads a csv file and returns a matrix with the cell values
     *
     * @param filePath  File path
     * @return the matrix with the csv data
     */
    public ArrayList<ArrayList<String>> getCsvData(String filePath) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Resource resource = new ClassPathResource(filePath);
        logger.debug("Getting data from csv '" + filePath + "'");

        try {
            char escape = '\\';
            int line = 1;

            CSVReader reader =
                    new CSVReader(new FileReader(resource.getFile().getAbsolutePath()), separator,
                            quotechar, escape, line);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                ArrayList<String> rowData = new ArrayList<String>();
                for (String col : nextLine) {
                    rowData.add(col);
                }
                data.add(rowData);
            }
            reader.close();
        } catch (Exception e) {
            logger.error("Error reading the csv file '" + filePath + "': " + e);
            return null;
        }

        return data;
    }

    public CsvReader(boolean hasHeader, char separator, char quotechar) {
        this.hasHeader = hasHeader;
        this.separator = separator;
        this.quotechar = quotechar;
    }


    public String[] getCsvHeader(String filePath) throws IOException {
        Resource resource = new ClassPathResource(filePath);
        CSVReader reader = new CSVReader(new FileReader(resource.getFile().getAbsolutePath()));
        Assert.assertNotNull(reader);
        String[] headerLine = reader.readNext();

        return headerLine[0].split("\\|");
    }
}
