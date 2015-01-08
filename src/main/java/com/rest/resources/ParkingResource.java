package com.rest.resources;

import com.parking.core.models.entities.Parking;
import com.parking.core.models.entities.ParkingStatusEnum;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

public class ParkingResource extends ResourceSupport {

    ParkingStatusEnum status;

    Date whenPicked;

    Long latitude;

    Long longitude;


    private Long rid;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public ParkingStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ParkingStatusEnum status) {
        this.status = status;
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

    public Parking toParking() {
        Parking parking = new Parking();
        parking.setLatitude(latitude);
        parking.setLongitude(longitude);
        parking.setWhenPicked(whenPicked);
        parking.setStatus(status);
        return parking;
    }
}
