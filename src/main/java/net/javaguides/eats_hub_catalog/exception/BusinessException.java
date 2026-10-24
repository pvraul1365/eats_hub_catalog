package net.javaguides.eats_hub_catalog.exception;

/**
 * BusinessException
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - raul.perez.vicente@gmail.com
 * @version 19/10/2026 - 15:02
 * @since 1.25
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
