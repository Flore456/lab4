package ru.ssau.esa.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(schema = "public", name = "person")



public class Person {
    private String id;
    private String name;
    private String first_name;
    private String city;
    private Date birthday;
    private Bank bank;
    private Car car;


    public Person(){

    }

    @Id
    @Column(name = "id", nullable = false,length = 60)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name; }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "first_name", nullable = false,length = 50)
    public String getFirst_name() {
        return first_name; }

    public void setFirst_name(String  first_name) {
        this.first_name = first_name;
    }

    @Column(name = "city", nullable = false,length = 50)
    public String getCity() {
        return city; }

    public void setCity(String  city) {
        this.city = city;
    }

    @Column(name = "birthday", nullable = false)
    public Date getBirthday() {
        return birthday; }

    public void setBirthday(Date  birthday) {
        this.birthday = birthday;
    }


    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.ALL})
    @JoinColumn(name = "account_num")
    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.ALL})
    @JoinColumn(name = "car_id")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(name, person.name) &&
                Objects.equals(first_name, person.first_name) &&
                Objects.equals(city,person.city)&&
                Objects.equals(birthday,person.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name,first_name,city,birthday);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Animal{").append("\n\t");
        sb.append("id=").append(id).append(",\n\t");
        sb.append("bank='").append(bank.getAccount_year()).append(' ').append(bank.getNumber_card()).append("',\n\t").append(bank.getManager_name()).append("',\n\t");
        sb.append("car='").append(car.getBrand()).append("',\n\t").append(car.getColor()).append("',\n\t");
        sb.append("name=").append(first_name).append(",\n\t");
        sb.append("first_name=").append(name).append(",\n\t");
        sb.append("city=").append(city).append(",\n\t");
        sb.append("birthday='").append(birthday).append('\'').append("\n");
        sb.append('}');
        return sb.toString();

    }

}
