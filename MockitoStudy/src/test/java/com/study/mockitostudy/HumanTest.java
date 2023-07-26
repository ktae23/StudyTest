package com.study.mockitostudy;

import com.study.mockitostudy.food.Rice;
import com.study.mockitostudy.human.Human;
import com.study.mockitostudy.human.vo.Mouse;
import com.study.mockitostudy.human.vo.Stomach;
import com.study.mockitostudy.human.vo.Throat;
import com.study.mockitostudy.marker.MockUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;


@MockUnitTest
class HumanTest {

    @Test
    void 사람은_태어남과_동시에_이름이_생기며_0살이다() {
        String name = "김창식";
        Human human = new Human(name);

        assertThat(human.getName()).isEqualTo(name);
        assertThat(human.getAge()).isEqualTo(0);
    }

    @Test
    void 내가_모킹한_사람은_태어남과_동시에_이름이_생기며_999살이다() {
        String name = "김창식";
        int age = 999;
        Human human = Mockito.mock(Human.class);
        Mockito.when(human.getName()).thenReturn(name);
        Mockito.when(human.getAge()).thenReturn(age);

        assertThat(human.getName()).isEqualTo(name);
        assertThat(human.getAge()).isEqualTo(age);
    }

    @Test
    void 사람은_나이가_한살씩_많아진다() {
        String name = "김창식";
        Human human = new Human(name);

        human.old();

        assertThat(human.getName()).isEqualTo(name);
        assertThat(human.getAge()).isEqualTo(1);
    }

    @Test
    void 사람은_죽으면_나이가_들지_않는다() {
        String name = "김창식";
        Human human = new Human(name);

        human.die();
        human.old();

        assertThat(human.getName()).isEqualTo(name);
        assertThat(human.getAge()).isEqualTo(0);
    }

    @Test
    void 내가_조작한_사람은_죽어도_죽지_못해_나이가_든다() {
        String name = "김창식";
        Human human = spy(new Human(name));
        Mockito.when(human.isAlive()).thenReturn(true);
        assertThat(human.getAge()).isEqualTo(0);

        human.die();
        human.old();

        assertThat(human.getName()).isEqualTo(name);
        assertThat(human.getAge()).isEqualTo(1);
    }

    @Test
    void 신의_은총으로_사람은_최대_2번_부활이_가능하다() {
        String name = "김창식";
        Human human = new Human(name);

        human.die();
        assertThat(human.isAlive()).isFalse();
        human.rise();
        assertThat(human.isAlive()).isTrue();

        human.die();
        assertThat(human.isAlive()).isFalse();
        human.rise();
        assertThat(human.isAlive()).isTrue();

        human.die();
        assertThat(human.isAlive()).isFalse();
        human.rise();
        assertThat(human.isAlive()).isFalse();
    }

    @Test
    void 진짜로_2번만_부활되는지_확인() {
        String name = "김창식";
        Human human = spy(new Human(name));

        IntStream.range(0, 100)
                .forEach(i -> {
                    human.die();
                    human.rise();
                });

        Mockito.verify(human, Mockito.times(2)).alive();
    }

    @Test
    void 사람은_밥을_먹으면_입_목_배_순서로_음식이_이동한다() throws InterruptedException {
        String name = "김창식";
        Human human = new Human(name);

        Rice rice = new Rice();
        human.eat(rice);

        assertThat(human.foodIsWhere()).isEqualTo(human.getStomach());
    }


    @Test
    void 정말로_사람은_밥을_먹으면_입_목_배_순서로_음식이_이동할까() throws InterruptedException {
        String name = "김창식";
        Mouse mouse = Mockito.mock(Mouse.class);
        Throat throat = Mockito.mock(Throat.class);
        Stomach stomach = Mockito.mock(Stomach.class);
        Rice rice = Mockito.mock(Rice.class);

        Human human = new Human(name);
        ReflectionTestUtils.setField(human, "mouse", mouse);
        ReflectionTestUtils.setField(human, "throat", throat);
        ReflectionTestUtils.setField(human, "stomach", stomach);

        Mockito.when(mouse.swallow(rice)).thenReturn(rice);
        Mockito.when(throat.swallow(rice)).thenReturn(rice);


        human.eat(rice);

        InOrder eatOrder = Mockito.inOrder(mouse, throat, stomach);
        eatOrder.verify(mouse).swallow(rice);
        eatOrder.verify(throat).swallow(rice);
        eatOrder.verify(stomach).arrive(rice);
    }
}
