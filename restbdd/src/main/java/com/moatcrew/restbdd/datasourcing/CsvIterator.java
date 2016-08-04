package com.moatcrew.restbdd.datasourcing;

import com.moatcrew.restbdd.jbehave.AbstractSteps;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by tensh on 31/07/2016.
 */
public class CsvIterator {

    private static Log logger = LogFactory.getLog(CsvIterator.class);


    /**
     * Invoke a test method several times with the data of the csv/excel file
     *
     * @param testClass
     * @param methodName
     * @param filePath
     * @param sheetNumber [excel] the sheet number
     * @param header      true if the first row is the header and has no data
     * @param separator   [csv] the delimiter to use for separating entries
     * @param quotechar   [csv] the character to use for quoted elements
     * @return an array list with the error messages
     */
    public static ArrayList<String> fileTestExecution(AbstractSteps testClass,
                                                      String methodName, String filePath, int sheetNumber,
                                                      boolean header, char separator, char quotechar) {
        ArrayList<String> errors = new ArrayList<String>();
        ArrayList<ArrayList<String>> data =
                getFileData(filePath, header, separator, quotechar);
        if (data == null) {
            String errorMessage = "Error reading the file '" + filePath + "'";
            errors.add(errorMessage);
            return errors;
        }
        logger.info("Iterator test: " + data.size() + " values");
        if (data.size() <= 0) {
            String errorMessage =
                    "There are no data in the sheet " + sheetNumber
                            + " of the file '" + filePath + "'";
            logger.error(errorMessage);
            errors.add(errorMessage);
            return errors;
        }

        int itrNumber = 1;
        Iterator<ArrayList<String>> iter = data.iterator();
        while (iter.hasNext()) {
            ArrayList<String> rowData = iter.next();
            int paramsSize = rowData.size();
            logger.info("Iteration " + itrNumber + " of " + data.size());

            Method test;
            try {
                Class<?>[] paramTypes = new Class[paramsSize];
                for (int i = 0; i < paramsSize; i++) {
                    paramTypes[i] = String.class;
                }

                test = testClass.getClass().getMethod(methodName, paramTypes);
            } catch (SecurityException e) {
                String errorMessage =
                        "Security error searching the method '" + methodName
                                + "'";
                logger.error(errorMessage + ": " + e.getMessage());
                errors.add(errorMessage);
                return errors;
            } catch (NoSuchMethodException e) {
                String errorMessage =
                        "The method name (" + methodName
                                + ") or the number of paramaters ("
                                + paramsSize + ") are wrong";
                logger.error(errorMessage + ": " + e.getMessage());
                errors.add(errorMessage);
                return errors;
            }

            try {
                Object[] params = new Object[paramsSize];
                for (int i = 0; i < paramsSize; i++) {
                    params[i] = rowData.get(i);
                }

                test.invoke(testClass, params);
            } catch (IllegalArgumentException e) {
                String errorMessage =
                        "Illegal argument invoking the method '" + methodName
                                + "'";
                logger.error(errorMessage + ": " + e.getMessage());
                errors.add(errorMessage);
                return errors;
            } catch (IllegalAccessException e) {
                String errorMessage =
                        "Illegal access invoking the method '" + methodName
                                + "'";
                logger.error(errorMessage + ": " + e.getMessage());
                errors.add(errorMessage);
                return errors;
            } catch (InvocationTargetException e) {
                // Saving the original exception
                Throwable cause = e.getCause();
                logger.warn("Error in row " + itrNumber + " of " + data.size()
                        + ": " + cause.getMessage());
                errors.add("Error in row " + itrNumber + " of " + data.size()
                        + ": " + cause.getMessage());
            }

            itrNumber++;
        }

        return errors;
    }



    public static ArrayList<ArrayList<String>> getFileData(String filePath,
                                                           boolean header, char separator, char quotechar) {
//        ArrayList<ArrayList<String>> csvReader = new CsvReader().getCsvData(filePath ,header, separator, quotechar);
//        if  (filePath.endsWith(".csv")) {
//            return csvReader;
//        } else {
//            logger.error("Error reading the file '" + filePath
//                    + "': the file format must be csv, xls or xlsx");
            return null;
//        }
    }


}
