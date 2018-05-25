package configuration.gongdel.josn.jackson.model;

import java.util.ArrayList;
import java.util.List;

public class CarFleet {

    private List<Car> cars = new ArrayList<Car>();

    public List<Car> getCars() {
        return cars;
    }

    public void setCar(List<Car> car) {
        this.cars = car;
    }

    @Override
    public String toString() {
        return "CarFleet{" +
                "cars=" + cars +
                '}';
    }
}

