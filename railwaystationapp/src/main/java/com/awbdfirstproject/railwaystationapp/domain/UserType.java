package com.awbdfirstproject.railwaystationapp.domain;

public enum UserType {
    STUDENT("STUDENT"),
    RETIRED("PENSIONAR"),
    NORMAL("NORMAL"),
    SOCIAL("CAZ SOCIAL"),
    MILITARY("CADRU MILITAR");

    private String description;

    UserType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
