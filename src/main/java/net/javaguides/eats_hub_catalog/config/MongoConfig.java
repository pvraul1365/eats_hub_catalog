package net.javaguides.eats_hub_catalog.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import java.util.concurrent.TimeUnit;
import org.bson.UuidRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * MongoConfig
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 17/10/2026 - 09:29
 * @since 1.25
 */
@Configuration
@EnableReactiveMongoRepositories("net.javaguides.eats_hub_catalog")
@PropertySource("classpath:mongo-connection.properties")
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Value("${mongodb.host}")
    private String host;

    @Value("${mongodb.port}")
    private Integer port;

    @Value("${mongodb.username}")
    private String username;

    @Value("${mongodb.password}")
    private String password;

    @Value("${mongodb.database}")
    private String database;

    @Value("${mongodb.authentication-database}")
    private String authSource;

    @Value("${mongodb.maxPoolSize}")
    private Integer maxPoolSize;

    @Value("${mongodb.minPoolSize}")
    private Integer minPoolSize;

    @Value("${mongodb.maxConnectionLifeTime}")
    private Long maxConnectionLifeTime;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Bean
    @Override
    public MongoClient reactiveMongoClient() {
        final ConnectionString connectionString = new ConnectionString(String.format("mongodb://%s:%d/%s", host, port, database));
        final MongoCredential credential = MongoCredential.createCredential(username, authSource, password.toCharArray());

        final MongoClientSettings settings = MongoClientSettings.builder()
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .applyConnectionString(connectionString)
                .credential(credential)
                .applyToConnectionPoolSettings(builder -> {
                    builder.maxSize(maxPoolSize);
                    builder.minSize(minPoolSize);
                    builder.maxConnectionLifeTime(maxConnectionLifeTime, TimeUnit.MILLISECONDS);
                })
                .build();

        return MongoClients.create(settings);
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }
}
