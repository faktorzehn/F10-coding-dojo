package bots;

import controller.Bot;
import controller.Move;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class StatBotRandom implements Bot {

    private static final int WARMUP = 500;
    private static Random random = new Random();

    // our announcement -> enemy announcement -> enemey play -> count
    private Map<Move, Map<Move, Map<Move, AtomicInteger>>> enemyReactions = new HashMap<>();
    // our last move -> enemy anouncement -> count
    private Map<Move, Map<Move, AtomicInteger>> nextEnemyAnnouncement = new HashMap<>();

    private Move myLastMove = Move.ROCK;
    private Move myLastAnnouncement;
    private Move enemysLastAnnouncement;
    private int rounds;

    @Override
    public Move announce() {
        if(rounds<WARMUP) {
            return myLastAnnouncement = random();
        }else{
            Move expectedEnemyAnnouncement = nextEnemyAnnouncement.computeIfAbsent(myLastMove, $ -> new HashMap<>()).entrySet().stream().sorted(Comparator.comparing((Map.Entry<Move, AtomicInteger> e) -> e.getValue().get()).reversed()).findFirst().map(Map.Entry::getKey).orElse(random());
            return beat(expectedEnemyAnnouncement);
        }
    }

    @Override
    public Move play(Move enemyAnnouncement) {
        nextEnemyAnnouncement.computeIfAbsent(myLastMove, $->new HashMap<>()).computeIfAbsent(enemyAnnouncement, $->new AtomicInteger(0)).incrementAndGet();
        enemysLastAnnouncement = enemyAnnouncement;
        rounds++;
        if (rounds < WARMUP) {
            return random();
        } else {
            Map<Move, Map<Move, AtomicInteger>> whenIAnnounced = enemyReactions.computeIfAbsent(myLastAnnouncement, $ -> new HashMap<>());
            Map<Move, AtomicInteger> andTheEnemyAnnounced = whenIAnnounced.computeIfAbsent(enemysLastAnnouncement, $ -> new HashMap<>());
            Move leastLikelyEnemyMove = andTheEnemyAnnounced.entrySet().stream().sorted(Comparator.comparing(e -> e.getValue().get())).findFirst().map(Map.Entry::getKey).orElse(random());
            return beat(leastLikelyEnemyMove);
        }
    }

    @Override
    public void actual(Move enemyMove) {
        Map<Move, Map<Move, AtomicInteger>> reactionsToMyAnnouncements = enemyReactions.computeIfAbsent(myLastAnnouncement, $ -> new HashMap<>());
        Map<Move, AtomicInteger> changesFromEnemiesAnnouncement = reactionsToMyAnnouncements.computeIfAbsent(enemysLastAnnouncement, $ -> new HashMap<>());
        AtomicInteger count = changesFromEnemiesAnnouncement.computeIfAbsent(enemyMove, $ -> new AtomicInteger(0));
        count.incrementAndGet();
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
