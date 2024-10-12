package com.ShopManager.user_service.entity;

public enum Status {
    ACTIVE, INACTIVE, SUSPENDED;

    public static Status toEnum(String statusStr) {
        if (statusStr == null) {
            throw new IllegalArgumentException("Status value cannot be null");
        }

        switch (statusStr.toUpperCase()) {
            case "ACTIVE":
                return ACTIVE;
            case "INACTIVE":
                return INACTIVE;
            case "SUSPENDED":
                return SUSPENDED;
            default:
                throw new IllegalArgumentException("Unknown status: " + statusStr);
        }
    }
}
