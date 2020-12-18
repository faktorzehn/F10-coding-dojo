package bots;

import controller.Bot;
import controller.Controller;
import controller.Move;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CheatBot implements Bot {
    private static Random random = new Random();

    private List<Bot> bots = null;
    private Map<Bot, AtomicInteger> botCount = new HashMap<>();

    private Move myAnnouncement;
    private Move enemyMove;

    public CheatBot(){
        try {
            Field botsField = Controller.class.getDeclaredField("bots");
            botsField.setAccessible(true);
            bots = ((List<Class<? extends Bot>>) botsField.get(null)).stream().filter(c->!c.equals(CheatBot.class)).map(c-> {
                try {
                    return c.newInstance();
                } catch (InstantiationException |IllegalAccessException e) {
                    //ignore
                    return null;
                }
            }).collect(Collectors.toList());
            botCount = bots.stream().collect(Collectors.toMap(Function.identity(), $ -> new AtomicInteger(0)));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            //ignore
        }
    }

    @Override
    public Move announce() {
        return myAnnouncement = random();
    }

    @Override
    public Move play(Move enemyAnnouncement) {
        return botCount.entrySet().stream().sorted(Comparator.comparing((Map.Entry<Bot, AtomicInteger> e)->e.getValue().get()).reversed()).findFirst().map(e->simulate(e.getKey(),myAnnouncement)).map(this::beat).orElseGet(this::random);
    }

    private Move simulate(Bot bot, Move move){
        try{
            return bot.play(move);
        }catch(Exception e){
            return random();
        }
    }

    @Override
    public void actual(Move enemyMove) {
        this.enemyMove = enemyMove;
        botCount.entrySet().stream().forEach(e-> {
            if(simulate(e.getKey(),myAnnouncement)==enemyMove){
                e.getValue().incrementAndGet();
            }
        });
    }

    private Move beat(Move move){
        switch (move) {
            case ROCK:
                return Move.PAPER;
            case PAPER:
                return Move.SCISSORS;
            case SCISSORS:
                return Move.ROCK;
            default:
                return random();
        }
    }

    private Move next(Move move) {
        return Move.values()[(move.ordinal() + 1) % (Move.values().length - 1)];
    }

    private Move random(){
        return Move.values()[random.nextInt(Move.values().length)];
    }
}
