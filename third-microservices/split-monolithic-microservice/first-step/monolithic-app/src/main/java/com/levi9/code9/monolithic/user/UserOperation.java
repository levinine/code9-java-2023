package com.levi9.code9.monolithic.user;

import lombok.Getter;

@Getter
public enum UserOperation {

    CREATE("created"),
    UPDATE("updated");

    private String text;


    UserOperation(String text) {
        this.text = text;
    }
}
