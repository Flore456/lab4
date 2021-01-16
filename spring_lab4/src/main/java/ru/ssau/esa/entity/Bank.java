package ru.ssau.esa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "public", name = "bank")

public class Bank {
    private String account_num;
    private int account_year;
    private int number_card;
    private String manager_name;
    private String email;
    private List<Person> persons;

    public Bank() {

    }

    @Id
    @Column(name = "account_num", nullable = false, length = 60)
    public String getAccount_num() {
        return account_num;
    }

    public void setAccount_num(String account_num) {
        this.account_num = account_num;
    }

    @Column(name = "account_year", nullable = false)
    public int getAccount_year() {
        return account_year;
    }

    public void setAccount_year(int account_year) {
        this.account_year = account_year;
    }

    @Column(name = "number_card", nullable = false)
    public int getNumber_card() {
        return number_card;
    }

    public void setNumber_card(int number_card) {
        this.number_card = number_card;
    }

    @Column(name = "manager_name", nullable = false, length = 20)
    public String getManager_name() {
        return manager_name;
    }

    public void setManager_name(String manager_name) {
        this.manager_name = manager_name;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @JsonIgnore
    @OneToMany(mappedBy = "bank", cascade = {CascadeType.ALL})
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return account_num == bank.account_num &&
                Objects.equals(account_year, bank.account_year) &&
                Objects.equals(number_card, bank.number_card) &&
                Objects.equals(manager_name, bank.manager_name)&&
                Objects.equals(email, bank.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account_num, account_year, number_card, manager_name,email);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bank{").append("\n\t");
        sb.append("account_num=").append(account_num).append(",\n\t");
        sb.append("account_year='").append(account_year).append('\'').append(",\n\t");
        sb.append("number_card='").append(number_card).append('\'').append(",\n\t");
        sb.append("manager_name='").append(manager_name).append('\'').append(",\n\t");
        sb.append("email='").append(email).append('\'').append("\n");
        sb.append('}');
        return sb.toString();
    }
}


