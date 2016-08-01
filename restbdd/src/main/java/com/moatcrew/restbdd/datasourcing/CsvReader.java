package com.moatcrew.restbdd.datasourcing;

import au.com.bytecode.opencsv.CSVReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by tensh on 01/08/2016.
 */
public class CsvReader {

    private static Log logger = LogFactory.getLog(CsvReader.class);

    /**
     * Reads a csv file and returns a matrix with the cell values
     *
     * @param filePath  File path
     * @param header    true if the first row is the header and has no data
     * @param separator the delimiter to use for separating entries
     * @param quotechar the character to use for quoted elements
     * @return the matrix with the csv data
     */
    public ArrayList<ArrayList<String>> getCsvData(String filePath,
                                                           boolean header, char separator, char quotechar) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        logger.debug("Getting data from csv '" + filePath + "'");

        try {
            char escape = '\\';
            int line = header ? 1 : 0;

            CSVReader reader =
                    new CSVReader(new FileReader(filePath), separator,
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
}
