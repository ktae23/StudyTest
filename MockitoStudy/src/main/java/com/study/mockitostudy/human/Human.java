package com.study.mockitostudy.human;

import com.study.mockitostudy.food.Food;
import com.study.mockitostudy.food.Rice;
import com.study.mockitostudy.human.vo.*;
import lombok.Getter;

import java.util.List;

public class Human {
    private Name name;

    private Age age;
    @Getter
    private boolean alive;

    @Getter
    private int countOfRise;

    @Getter
    private Mouse mouse;

    @Getter
    private Throat throat;

    @Getter
    private Stomach stomach;

    public Human() {
    }

    public Human(String name) {
        this.name = new Name(name);
        this.age = new Age(0);
        this.mouse = new Mouse();
        this.throat = new Throat();
        this.stomach = new Stomach();
    }

    public String getName() {
        return name.getValue();
    }

    public int getAge() {
        return age.getValue();
    }


    public void old() {
        if (isAlive()) {
            this.age.increaseOneYear();
        }
    }

    public void die() {
        this.alive = false;
    }

    public void rise() {
        if (getCountOfRise() < 2) {
            this.countOfRise++;
            alive();
        }
    }

    public void alive() {
        this.alive = true;
    }

    public void eat(Rice rice) throws InterruptedException {
        Food mouseSwallowedFood = this.mouse.swallow(rice);
        Food throatSwallowedFood = this.throat.swallow(mouseSwallowedFood);
        this.stomach.arrive(throatSwallowedFood);
    }

    public BodyPart foodIsWhere() {
        List<BodyPart> bodyParts = getBodyParts();
        return bodyParts.stream().filter(BodyPart::hasFood).findFirst().orElse(null);
    }

    private List<BodyPart> getBodyParts() {
        return List.of(this.mouse, this.throat, this.stomach);
    }
}
