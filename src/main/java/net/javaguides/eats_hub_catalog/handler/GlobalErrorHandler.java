package net.javaguides.eats_hub_catalog.handler;

import jakarta.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.eats_hub_catalog.exception.BusinessException;
import org.springframework.boot.webflux.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import tools.jackson.databind.ObjectMapper;

import static net.javaguides.eats_hub_catalog.constants.ErrorConstants.*;

/**
 * GlobalErrorHandler
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 08/11/2026 - 12:32
 * @since 1.25
 */
@Configuration
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalErrorHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        log.error("Global error handler caught an exception: {}", ex);

        HttpStatus status;
        String message;
        String errorType;

        if (ex instanceof ValidationException) {
            status = HttpStatus.BAD_REQUEST;
            message = ex.getMessage();
            errorType = ERROR_TYPE_VALIDATION;
        } else if (ex instanceof BusinessException) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;
            message = ex.getMessage();
            errorType = ERROR_TYPE_BUSINESS;
        } else if (Objects.nonNull(ex.getMessage())
                && ex.getMessage().contains(ERROR_MESSAGE_RESTAURANT_NOT_FOUND)) {
            status = HttpStatus.NOT_FOUND;
            message = ERROR_MESSAGE_RESTAURANT_NOT_FOUND;
            errorType = ERROR_TYPE_NOT_FOUND;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = ERROR_MESSAGE_UNEXPECTED;
            errorType = ERROR_TYPE_INTERNAL;
        }

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(RESPONSE_KEY_TIMESTAMP, LocalDateTime.now().toString());
        errorResponse.put(RESPONSE_KEY_PATH, exchange.getRequest().getPath().value());
        errorResponse.put(RESPONSE_KEY_STATUS, status.value());
        errorResponse.put(RESPONSE_KEY_ERROR, status.getReasonPhrase());
        errorResponse.put(RESPONSE_KEY_MESSAGE, message);
        errorResponse.put(RESPONSE_KEY_ERROR_TYPE, errorType);

        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        try {
            final var bytes = objectMapper.writeValueAsBytes(errorResponse);
            final var buffer = exchange.getResponse().bufferFactory().wrap(bytes);

            return exchange.getResponse().writeWith(Mono.just(buffer));
        } catch (Exception e) {
            log.error("Error writing response: {}", e);
            return Mono.error(e);
        }

    }
}
