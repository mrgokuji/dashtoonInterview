package org.example.service;

import org.example.model.PassType;
import org.example.model.TollBooth;
import org.example.model.vehicle.Vehicle;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TollBoothHandler {
    static final Map<Integer, TollBooth> tollBoothMap = new HashMap<>();
    static final Map<String,PassType> registrationPassMap = new HashMap<>();
    static final Map<String,LocalDate> weeklyPassValidation = new HashMap<>();
    static final Map<String,Integer> twoWayPass = new HashMap<>();
    static final Map<String,Integer> oneWayPass = new HashMap<>();
    static Integer totalTollBooth = 0;

    static Scanner scanner = new Scanner(System.in);


    public Integer registerTollBooth(String tollBoothName){
        totalTollBooth++;
        tollBoothMap.put(totalTollBooth,new TollBooth(totalTollBooth,tollBoothName));
        return totalTollBooth; // this will be ID for the tollboot
    }
    public void isVehiclePermitted(Vehicle vehicle, PassType passType,Integer totalTollBoothId){
        if(registrationPassMap.containsKey(vehicle.getRegNumber())&& validatePass(vehicle,passType) && validatePassType(passType,vehicle) ){
        }else{
            System.out.println("You need to BUY Toll Pass");
            System.out.println("Please select a valid pass for you vehicle");
            buyPass(vehicle,tollBoothMap.get(totalTollBoothId));

        }
    }

    PassType buyPass(Vehicle vehicle,TollBooth tollBooth){
        System.out.println("Press 1 for SINGLE PASS");
        System.out.println("Press 2 for RETURN PASS");
        System.out.println("Press 3 for WEEKLY PASS");
        Integer val = scanner.nextInt();
        PassType pass = null;
        switch (vehicle.getVehicleType()){
            case TWO -> {
                    if(val == 1) {
                        oneWayPass.put(vehicle.getRegNumber(),1);
                        pass = PassType.TWO_SINGLE_PASS;
                        tollBooth.addAmount(20.0);
                    } else if (val == 2) {
                        twoWayPass.put(vehicle.getRegNumber(),2);
                        pass = PassType.TWO_RETURN_PASS;
                        tollBooth.addAmount(30.0);
                    } else if (val == 3) {
                        pass = PassType.TWO_WEEKLY_PASS;
                        weeklyPassValidation.put(vehicle.getRegNumber(), LocalDate.now());
                        tollBooth.addAmount(50.0);
                    }
            }
            case FOUR -> {
                if(val == 1) {
                    oneWayPass.put(vehicle.getRegNumber(),1);
                    pass = PassType.FOUR_SINGLE_PASS;
                    tollBooth.addAmount(20.0);
                } else if (val == 2) {
                    twoWayPass.put(vehicle.getRegNumber(),2);
                    pass = PassType.FOUR_RETURN_PASS;
                    tollBooth.addAmount(30.0);
                } else if (val == 3) {
                    pass = PassType.FOUR_WEEKLY_PASS;
                    weeklyPassValidation.put(vehicle.getRegNumber(), LocalDate.now());
                    tollBooth.addAmount(50.0);
                }
            }
            default -> System.out.println("Vehicle Not found");
        }
        registrationPassMap.put(vehicle.getRegNumber(),pass);
        return pass;
    }

    boolean validatePassType(PassType passType, Vehicle vehicle){
        switch (passType){
            case TWO_SINGLE_PASS:
            case FOUR_SINGLE_PASS: if(oneWayPass.containsKey(vehicle.getRegNumber())){
                oneWayPass.remove(vehicle.getRegNumber());
                return true;
            }else{
                return false;
            }
            case TWO_RETURN_PASS:
            case FOUR_RETURN_PASS:
                if(twoWayPass.containsKey(vehicle.getRegNumber())){
                    int val = twoWayPass.get(vehicle.getRegNumber());
                    if(val==1) twoWayPass.remove(vehicle.getRegNumber());
                    else twoWayPass.put(vehicle.getRegNumber(),1);
                    return true;
                }
                else return false;
            case TWO_WEEKLY_PASS:
            case FOUR_WEEKLY_PASS:
                if(weeklyPassValidation.containsKey(vehicle.getRegNumber())){
                    LocalDate localDate = LocalDate.now();
                    if(weeklyPassValidation.get(vehicle.getRegNumber()).isAfter(localDate)) return false;

                    return true;
                }else  return false;
            default: return false;
        }
    }

    boolean validatePass(Vehicle vehicle, PassType passType){
        switch (vehicle.getVehicleType()){
            case TWO -> {
                return (passType.equals(PassType.TWO_RETURN_PASS) || passType.equals(PassType.TWO_SINGLE_PASS) || passType.equals(PassType.TWO_WEEKLY_PASS));
            }
            case FOUR -> {
                return (passType.equals(PassType.FOUR_RETURN_PASS) || passType.equals(PassType.FOUR_SINGLE_PASS) || passType.equals(PassType.FOUR_WEEKLY_PASS));
            }
            default -> {
                return false;
            }
        }
    }


}
