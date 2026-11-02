package net.javaguides.eats_hub_catalog.config;

import jakarta.annotation.PreDestroy;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Objects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ValidationConfig
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 02/11/2026 - 10:52
 * @since 1.25
 */
@Configuration
public class ValidatorConfig {

    private ValidatorFactory validatorFactory;

    @Bean
    public Validator validator() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();

        return this.validatorFactory.getValidator();
    }

    @PreDestroy
    public void destroy() {
        if (Objects.nonNull(this.validatorFactory)) {
            this.validatorFactory.close();
        }
    }
}
