package net.thumbtack.buscompany;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

public abstract class AbstractTest {

  public static final String BASE_URL = "/buscompany/v1";

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
