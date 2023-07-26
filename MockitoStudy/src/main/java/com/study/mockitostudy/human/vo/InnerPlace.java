package com.study.mockitostudy.human.vo;

import com.study.mockitostudy.food.Food;

public class InnerPlace {

    private Food food;

    public void put(Food food) {
        this.food = food;
    }

    public Food pop() {
        return food;
    }

    public void clean() {
        this.food = null;
    }

    public boolean isFull() {
        return this.food != null;
    }
}
