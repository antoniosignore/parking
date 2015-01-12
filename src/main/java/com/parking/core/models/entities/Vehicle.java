package com.parking.core.models.entities;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Size(min = 2, max = 14)
    private String licensePlate;

    @NotNull
    @OneToOne
    private Account owner;

    public Vehicle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;

        Vehicle vehicle = (Vehicle) o;

        if (!id.equals(vehicle.id)) return false;
        if (!licensePlate.equals(vehicle.licensePlate)) return false;
        if (!name.equals(vehicle.name)) return false;
        if (!owner.equals(vehicle.owner)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + licensePlate.hashCode();
        result = 31 * result + owner.hashCode();
        return result;
    }
}
