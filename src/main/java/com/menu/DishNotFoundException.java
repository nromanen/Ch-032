package com.menu;

public class DishNotFoundException
        extends RuntimeException {
    private String name;

    public DishNotFoundException(String name) {
        this.name = name;
    }
}