package net.javaguides.eats_hub_catalog.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * ErrorConstants
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 08/11/2026 - 12:30
 * @since 1.25
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorConstants {

    public static final String ERROR_TYPE_VALIDATION = "VALIDATION_ERROR";
    public static final String ERROR_TYPE_BUSINESS = "BUSINESS_ERROR";
    public static final String ERROR_MESSAGE_RESTAURANT_NOT_FOUND = "Restaurant not found";
    public static final String ERROR_TYPE_NOT_FOUND = "NOT_FOUND";
    public static final String ERROR_MESSAGE_UNEXPECTED = "An unexpected error occurred";
    public static final String ERROR_TYPE_INTERNAL = "INTERNAL_ERROR";

    public static final String RESPONSE_KEY_TIMESTAMP = "timestamp";
    public static final String RESPONSE_KEY_PATH = "path";
    public static final String RESPONSE_KEY_STATUS = "status";
    public static final String RESPONSE_KEY_ERROR = "error";
    public static final String RESPONSE_KEY_MESSAGE = "message";
    public static final String RESPONSE_KEY_ERROR_TYPE = "errorType";

}
