package net.thumbtack.shipcompany;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

public abstract class AbstractTest {
    public static final String BASE_URL = "/shipcompany/v1";

    protected ObjectMapper objectMapper = new ObjectMapper();

    protected <T> T getObjectFromJsonFile(String fileName, Class<T> clazz) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try {
            String s = IOUtils.toString(classLoader.getResourceAsStream(fileName), "UTF-8");
            return objectMapper.readValue(s, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected String getJson(String fileName) throws IOException {
        return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(fileName), "UTF-8");
    }
}
