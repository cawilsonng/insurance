package com.wilson.insurance.constant;

import java.util.Map;
import java.util.TreeMap;

public class CarInsuranceConstants {
    public static TreeMap<Integer, Double> ageFactorMap = new TreeMap<>();
    public static TreeMap<Integer, Double> drivingExpFactorMap = new TreeMap<>();
    public static TreeMap<Integer, Double> driverRecordFactorMap = new TreeMap<>();
    public static TreeMap<Integer, Double> claimRecordFactorMap = new TreeMap<>();
    public static TreeMap<Integer, Double> carValueFactorMap = new TreeMap<>();
    public static TreeMap<Integer, Double> mileageFactorMap = new TreeMap<>();
    public static TreeMap<Integer, Double> insuranceHistoryFactorMap = new TreeMap<>();

    public static final String BASE_PREMIUM_JSON_URL = "https://storage.googleapis.com/connex-th/insurance_assignment/base_premium.json";
    public static final String BASE_PREMIUM_FIELD_NAME = "base_premium";

    static {
        ageFactorMap.put(1, 1.3);
        ageFactorMap.put(25, 1.0);
        ageFactorMap.put(40, 0.9);
        ageFactorMap.put(70, null);

        drivingExpFactorMap.put(0, 1.5);
        drivingExpFactorMap.put(2, 1.3);
        drivingExpFactorMap.put(5, 1.0);
        drivingExpFactorMap.put(10, null);

        driverRecordFactorMap.put(0, 1.0);
        driverRecordFactorMap.put(1, 1.1);
        driverRecordFactorMap.put(2, 1.3);
        driverRecordFactorMap.put(4, null);

        claimRecordFactorMap.put(0, 0.9);
        claimRecordFactorMap.put(1, 1.2);
        claimRecordFactorMap.put(2, 1.5);
        claimRecordFactorMap.put(4, null);

        carValueFactorMap.put(0, 0.8);
        carValueFactorMap.put(30000, 1.0);
        carValueFactorMap.put(60000, 1.2);
        carValueFactorMap.put(100000, 1.5);
        carValueFactorMap.put(150000, 2.0);
        carValueFactorMap.put(200000, null);

        mileageFactorMap.put(0, 0.9);
        mileageFactorMap.put(20000, 1.0);
        mileageFactorMap.put(30000, 1.1);
        mileageFactorMap.put(50000, null);

        insuranceHistoryFactorMap.put(0, 1.2);
        insuranceHistoryFactorMap.put(2, 1.1);
        insuranceHistoryFactorMap.put(3, null);
    }

     public static <K, V> V mappedValue(TreeMap<K, V> map, K key) {
        Map.Entry<K, V> e = map.floorEntry(key);
        if (e != null && e.getValue() == null) {
            e = map.lowerEntry(key);
        }
        return e == null ? null : e.getValue();
    }
}
