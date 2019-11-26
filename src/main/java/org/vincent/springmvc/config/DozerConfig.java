package org.vincent.springmvc.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.vincent.springmvc.dozer.DozerMapper;
import org.vincent.springmvc.dozer.providers.MappingProvider;

import java.util.Set;
import java.util.function.Consumer;

@Configuration
@ComponentScan(DozerConfig.DOZER_PACKAGE)
public class DozerConfig {

    public static final String DOZER_PACKAGE = "org.vincent.springmvc.dozer";

    @Bean
    public DozerMapper dozerMapper(Set<MappingProvider> mapperConfigurations) {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapperConfigurations.forEach(configureMapper(mapper));

        return new DozerMapper(mapper);
    }

    private Consumer<MappingProvider> configureMapper(DozerBeanMapper mapper) {
        return mapperConfigurationProvider -> mapperConfigurationProvider.getMapperConfigurations()
                .forEach(mapper::addMapping);
    }

}
