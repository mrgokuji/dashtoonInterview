package org.example.model;

public enum PassType {

        TWO_SINGLE_PASS(20.0),
        TWO_RETURN_PASS(30.0),
        TWO_WEEKLY_PASS(500.0),
        FOUR_SINGLE_PASS(20.0),
        FOUR_RETURN_PASS(30.0),
        FOUR_WEEKLY_PASS(500.0);
        Double value;
        PassType(Double val){
            value = val;
        }

}
