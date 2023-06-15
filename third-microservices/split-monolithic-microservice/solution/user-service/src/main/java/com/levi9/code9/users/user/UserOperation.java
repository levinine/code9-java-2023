package com.levi9.code9.users.user;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
public enum UserOperation {

    CREATE("created"),
    UPDATE("updated");

    private String text;


    UserOperation(String text) {
        this.text = text;
    }
}
