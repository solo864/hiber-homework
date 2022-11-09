package az.online.shop.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}