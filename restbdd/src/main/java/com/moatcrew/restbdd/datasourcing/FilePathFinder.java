package com.moatcrew.restbdd.datasourcing;

import com.moatcrew.restbdd.jbehave.DemoTestExecutor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by tensh on 31/07/2016.
 */
public class FilePathFinder {

    /**
     * Class logger
     */
    private static Log logger = LogFactory.getLog(FilePathFinder.class);

    /**
     * @return the name of the class
     */
    public static String getMyClassName() {
        return new Exception().getStackTrace()[1].getClassName();
    }


    /**
     * Gets the absolute path of a resource file
     *
     * @param fileName
     * @return the absolute path of the file
     */
    public static String getResourcePath(String fileName) {
        URL url = (DemoTestExecutor.class).getResource("/" + fileName);
        if (url == null) {
            logger.error("File '" + fileName
                    + "' not found in resources folder");
            return null;
        }

        String filePath = null;
        try {
            URI uri = new URI(url.getFile());
            filePath = uri.getPath();
        } catch (URISyntaxException e) {
            logger.error("Error getting the URI of '" + url.getFile() + "': "
                    + e.getMessage());
            return null;
        }

        // Change file separator in Windows
        if (File.separator.equals("\\")) {
            filePath = filePath.substring(1).replace("/", "\\");
        }
        logger.debug("Resource file path: " + filePath);

        return filePath;
    }
}
