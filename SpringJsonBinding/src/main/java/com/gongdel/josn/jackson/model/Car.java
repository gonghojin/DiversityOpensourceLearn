package com.gongdel.josn.jackson.model;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private String name;
    private String model;
    private long cost;
    private List<String> colors = new ArrayList<String>();
    private CarEngine engine = new CarEngine();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public CarEngine getEngine() {
        return engine;
    }

    public void setEngine(CarEngine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", cost=" + cost +
                ", colors=" + colors +
                ", engine=" + engine +
                '}';
    }
}
