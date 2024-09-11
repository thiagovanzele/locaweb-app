package br.com.localweb.app.exceptions;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Class<?> classType, UUID uuid) {
        super("Resource not Found. Class: " + classType.getSimpleName() + ". ID: " + uuid);
    }
}
