package com.study.mockitostudy.human.vo;

import lombok.Getter;

@Getter
public class Age {
    private int value;

    public Age(int value) {
        this.value = value;
    }

    public void increaseOneYear() {
        this.value++;
    }
}
