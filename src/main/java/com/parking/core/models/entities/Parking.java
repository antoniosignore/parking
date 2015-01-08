package com.parking.core.models.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Parking {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Account account;

    Date parkingDate;

    @OneToOne
    Account pickedBy;

    @Enumerated(EnumType.STRING)
    private ParkingStatusEnum status;

    Date whenPicked;

    Long latitude;

    Long longitude;

    public Parking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getParkingDate() {
        return parkingDate;
    }

    public void setParkingDate(Date parkingDate) {
        this.parkingDate = parkingDate;
    }

    public ParkingStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ParkingStatusEnum status) {
        this.status = status;
    }

    public Account getPickedBy() {
        return pickedBy;
    }

    public void setPickedBy(Account pickedBy) {
        this.pickedBy = pickedBy;
    }

    public Date getWhenPicked() {
        return whenPicked;
    }

    public void setWhenPicked(Date whenPicked) {
        this.whenPicked = whenPicked;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parking)) return false;

        Parking parking = (Parking) o;

        if (account != null ? !account.equals(parking.account) : parking.account != null) return false;
        if (!id.equals(parking.id)) return false;
        if (latitude != null ? !latitude.equals(parking.latitude) : parking.latitude != null) return false;
        if (longitude != null ? !longitude.equals(parking.longitude) : parking.longitude != null) return false;
        if (parkingDate != null ? !parkingDate.equals(parking.parkingDate) : parking.parkingDate != null) return false;
        if (pickedBy != null ? !pickedBy.equals(parking.pickedBy) : parking.pickedBy != null) return false;
        if (status != parking.status) return false;
        if (whenPicked != null ? !whenPicked.equals(parking.whenPicked) : parking.whenPicked != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (parkingDate != null ? parkingDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (pickedBy != null ? pickedBy.hashCode() : 0);
        result = 31 * result + (whenPicked != null ? whenPicked.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }
}
