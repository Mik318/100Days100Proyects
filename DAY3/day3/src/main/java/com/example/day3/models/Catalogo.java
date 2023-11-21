package com.example.day3.models;

public class Catalogo {
    private Integer id;
    private String name;

    //constructor
    public Catalogo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;  // Esto es lo que se mostrará en el ComboBox
    }
}
