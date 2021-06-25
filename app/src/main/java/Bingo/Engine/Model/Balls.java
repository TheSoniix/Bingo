package Bingo.Engine.Model;

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
        return List.copyOf(drawnBalls);
    }

    public int drawBall() {
        boolean alreadyDrawn = true;
        int randomNum = -1;

        if (this.balls.size() != 0) {
            while (alreadyDrawn) {
                randomNum = (int) Math.floor(Math.random() * (75 - 1 + 1) + 1);
                alreadyDrawn = !this.balls.contains(randomNum);
            }

            this.balls.remove(randomNum);
            this.drawnBalls.add(randomNum);
        }
        return randomNum;
    }
}
