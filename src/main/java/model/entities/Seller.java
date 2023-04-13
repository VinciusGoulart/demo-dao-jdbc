package model.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Seller {

    private Integer id;
    private String name;
    private String email;
    private LocalDate birthday;
    private Double baseSalary;

    public Seller(){

    }
    public Seller(Integer id, String name, String email, LocalDate birthday, Double baseSalary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.baseSalary = baseSalary;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seller seller)) return false;
        return getId().equals(seller.getId()) && Objects.equals(getName(), seller.getName()) && Objects.equals(getEmail(), seller.getEmail()) && Objects.equals(getBirthday(), seller.getBirthday()) && Objects.equals(getBaseSalary(), seller.getBaseSalary());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getBirthday(), getBaseSalary());
    }
}
