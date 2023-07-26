package com.study.mockitostudy.human.vo;

import lombok.Getter;

@Getter
public class Name {

    private String value;

    public Name(String name) {
        this.value = name;
    }
}
