package com.study.mockitostudy.human.vo;

import com.study.mockitostudy.food.Food;

public class Stomach implements BodyPart {
    private InnerPlace innerPlace = new InnerPlace();

    public void arrive(Food food) {
        innerPlace.put(food);
    }

    @Override
    public boolean hasFood() {
        return this.innerPlace.isFull();
    }
}
