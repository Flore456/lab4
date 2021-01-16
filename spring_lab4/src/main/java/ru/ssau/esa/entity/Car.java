package ru.ssau.esa.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "public", name = "car")

public class Car {
    private String car_id;
    private String brand;
    private String color;
    private List<Person> persons;

    public Car(){

    }

    @Id
    @Column(name = "car_id", nullable = false, length = 60)
    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    @Column(name = "brand", nullable = false, length = 10)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(name = "color", nullable = false, length = 10)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;}

   @JsonIgnore
   @OneToMany(mappedBy = "car", cascade = {CascadeType.ALL})
    public List<Person> getPersons() { return persons; }

    public void setPersons(List<Person> persons) { this.persons = persons; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return car_id == car.car_id &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(color,car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(car_id,brand,color);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{").append("\n\t");
        sb.append("car_id=").append(car_id).append(",\n\t");
        sb.append("brand='").append(brand).append('\'').append(",\n\t");
        sb.append("color='").append(color).append('\'').append("\n");
        sb.append('}');
        return sb.toString();
    }



}
