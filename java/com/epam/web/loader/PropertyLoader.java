package com.epam.web.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    public Properties load(String filename) throws IOException {
        Properties properties = new Properties();

        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(filename)) {

            properties.load(inputStream);
        }
        return properties;
    }

}
