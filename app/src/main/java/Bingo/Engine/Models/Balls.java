package Bingo.Engine.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Balls {
    private final Set<Integer> balls;
    private final List<Integer> drawnBalls;

    public Balls() {
        this.balls = IntStream.range(1, 76).boxed().collect(Collectors.toSet());
        this.drawnBalls = new ArrayList<>();
    }

    public List<Integer> getDrawnBalls() {
        return drawnBalls;
    }

    public Set<Integer> getBalls() {
        return balls;
    }

    public int drawBall() {
        int randomNum;
        do {
            randomNum = (int) Math.floor(Math.random() * 75 + 1);
        } while (!this.balls.contains(randomNum));

        this.balls.remove(randomNum);
        this.drawnBalls.add(randomNum);
        return randomNum;
    }
}
