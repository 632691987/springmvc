package org.vincent.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.vincent.springmvc.system.SystemConstant;

import javax.sql.DataSource;

@Configuration
@Profile(SystemConstant.ProfileTest)
public class DataSourceConfigTest {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setName("testdb")
                .setType(EmbeddedDatabaseType.DERBY)
                .addScript("springpure/database/sql/create-db.sql")
                .build();
        return db;
    }
}
