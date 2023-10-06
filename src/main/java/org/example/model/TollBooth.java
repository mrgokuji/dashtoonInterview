package org.example.model;

import org.example.model.vehicle.Vehicle;

public class TollBooth implements Comparable<TollBooth>{
    Integer tollBoothId;
    String tollBoothName;
    Integer numberOfTwoWheelerPassed;
    Integer numberOfFourWheelerPassed;
    Double amountCollected;

    public TollBooth(Integer tollBoothId,String tollBoothName) {
        this.tollBoothId = tollBoothId;
        this.amountCollected = 0.0;
        this.numberOfFourWheelerPassed = 0;
        this.numberOfTwoWheelerPassed = 0;
        this.tollBoothName = tollBoothName;
    }

    public Integer getTollBoothId() {
        return tollBoothId;
    }

    public Integer getNumberOfTwoWheelerPassed() {
        return numberOfTwoWheelerPassed;
    }

    public Integer getNumberOfFourWheelerPassed() {
        return numberOfFourWheelerPassed;
    }

    public Double getAmountCollected() {
        return amountCollected;
    }

    public void addAmount(double amt){
        this.amountCollected += amt;
    }

    public void vehiclePassed(Vehicle vehicle){
        switch (vehicle.getVehicleType()){
            case FOUR -> this.numberOfFourWheelerPassed++;
            case TWO -> this.numberOfTwoWheelerPassed++;
        }
    }

    @Override
    public String toString() {
        return "TollBooth{" +
                "tollBoothId=" + tollBoothId +
                ", tollBoothName='" + tollBoothName + '\'' +
                ", numberOfTwoWheelerPassed=" + numberOfTwoWheelerPassed +
                ", numberOfFourWheelerPassed=" + numberOfFourWheelerPassed +
                ", amountCollected=" + amountCollected +
                '}';
    }

    @Override
    public int compareTo(TollBooth o) {
        if( o.amountCollected > this.amountCollected)
            return 1;
        else if (o.amountCollected == this.amountCollected) return 0;

        return -1;
    }
}
