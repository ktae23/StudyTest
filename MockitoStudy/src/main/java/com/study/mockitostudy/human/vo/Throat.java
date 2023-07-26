package com.study.mockitostudy.human.vo;

import com.study.mockitostudy.food.Food;

public class Throat implements BodyPart {

    private InnerPlace innerPlace = new InnerPlace();

    public Food swallow(Food food) throws InterruptedException {
        innerPlace.put(food);
        Thread.sleep(5);
        Food swallowedFood = innerPlace.pop();
        innerPlace.clean();
        return swallowedFood;
    }

    @Override
    public boolean hasFood() {
        return this.innerPlace.isFull();
    }
}
