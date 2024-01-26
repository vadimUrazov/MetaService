package net.thumbtack.buscompany.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@TestConfiguration
@PropertySource({"classpath:/application-test.properties"})
public class TestJdbcConfig {

  @Bean
  public EmbeddedDatabase dataSource() {

    return new EmbeddedDatabaseBuilder(new FileSystemResourceLoader())
        .generateUniqueName(false)
        .setScriptEncoding("UTF-8").setName("buscompany")
        .setType(EmbeddedDatabaseType.H2)
        .addScripts("src/test/resources/sql/switch-mode.sql")
        .build();
  }


}

