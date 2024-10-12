package com.ShopManager.user_service.entity;

public enum Gender {
    MALE, FEMALE, OTHER;

    public static Gender toEnum(String genderStr) {
        if (genderStr == null) {
            throw new IllegalArgumentException("Gender value cannot be null");
        }

        switch (genderStr.toUpperCase()) {
            case "MALE":
                return MALE;
            case "FEMALE":
                return FEMALE;
            case "OTHER":
                return OTHER;
            default:
                throw new IllegalArgumentException("Unknown gender: " + genderStr);
        }
    }
}
