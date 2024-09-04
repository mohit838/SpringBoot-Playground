package com.mohitul.blog_apps_demo.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceAlreadyExistsException extends RuntimeException {
    String resourceName;
    String fieldName;
    Object fieldValue;

    public ResourceAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s already exists with %s: %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
