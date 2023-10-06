package org.example.model;

public class TollBooth {
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
}
