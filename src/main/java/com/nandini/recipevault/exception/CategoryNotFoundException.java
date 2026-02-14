package com.nandini.recipevault.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id) {

        super("Cateory not found "+ id);
    }
}
