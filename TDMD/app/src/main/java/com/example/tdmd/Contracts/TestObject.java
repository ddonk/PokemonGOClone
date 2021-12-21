package com.example.tdmd.Contracts;

import java.io.Serializable;

public class TestObject implements Serializable {
    private String name;
    private String type;

    public TestObject(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
