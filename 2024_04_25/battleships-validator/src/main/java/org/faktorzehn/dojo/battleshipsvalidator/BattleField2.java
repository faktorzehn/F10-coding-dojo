package org.faktorzehn.dojo.battleshipsvalidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class BattleField2 {

    public static boolean fieldValidator(int[][] field) {
        Map<Coordinate, Ship> coordinateCandidateMap = new HashMap<>();
        List<Integer> ships = new ArrayList<>(List.of(4, 3, 3, 2, 2, 2, 1, 1, 1, 1));

        for (int d = 0; d < 19; d++) {
            int startRow = Math.max(0, d - 9);
            int endRow = Math.min(9, d);

            for (int row = startRow; row <= endRow; row++) {
                int col = d - row;
                try {
                    if (field[row][col] == 1) {
                        Ship ship = null;
                        if ((hasShipUp(row, col, field) && hasShipLeft(row, col, field)) || hasShipDiagonal(row, col, field)) {
                            throw new IllegalStateException();
                        }

                        //check left
                        if (hasShipLeft(row, col, field)) {
                            ship = coordinateCandidateMap.get(new Coordinate(row, col - 1));
                            if (Direction.VERTICAL.equals(ship.direction()) || ship.length() == 4) {
                                throw new IllegalStateException();
                            }
                            ship.setDirection(Direction.HORIZONTAL);
                            ship.length += 1;
                        }

                        //check up
                        if (hasShipUp(row, col, field)) {
                            ship = coordinateCandidateMap.get(new Coordinate(row - 1, col));
                            if (Direction.HORIZONTAL.equals(ship.direction()) || ship.length() == 4) {
                                throw new IllegalStateException();
                            }
                            ship.setDirection(Direction.VERTICAL);
                            ship.length += 1;
                        }
                        coordinateCandidateMap.put(new Coordinate(row, col), Optional.ofNullable(ship).orElseGet(Ship::new));
                    } else {
                        //no hit
                        //check left
                        int finalRow = row;
                        Optional<Integer> i = Optional.of(new Coordinate(row, col - 1))
                                .filter($ -> hasShipLeft(finalRow, col, field))
                                .map(coordinateCandidateMap::get)
                                // when checking left also remove submarines in the last row
                                .filter(s -> s.direction.equals(Direction.HORIZONTAL) || (finalRow == 9))
                                .map(Ship::length);
                        if (i.isPresent()) {
                            Integer length = i.get();
                            if (!ships.contains(length)) {
                                throw new IllegalStateException();
                            }
                            ships.remove(length);
                        }

                        //check up
                        Optional<Integer> lengthOfFinishedShipUp = Optional.of(new Coordinate(row - 1, col))
                                .filter($ -> hasShipUp(finalRow, col, field))
                                .map(coordinateCandidateMap::get)
                                .filter(s -> !s.direction.equals(Direction.HORIZONTAL))
                                .map(Ship::length);
                        if (lengthOfFinishedShipUp.isPresent()) {
                            Integer length = lengthOfFinishedShipUp.get();
                            if (!ships.contains(length)) {
                                throw new IllegalStateException();
                            }
                            ships.remove(length);
                        }

                    }

                } catch (IllegalStateException e) {
                    return false;
                }
            }
        }
        //in case the last submarine is in the corner
        boolean submarineInCorner = Optional.ofNullable(coordinateCandidateMap.get(new Coordinate(9, 9))).filter(s -> s.length == 1).map(Ship::length).isPresent();
        return submarineInCorner ? ships.size() == 1 && ships.contains(1) : ships.isEmpty();
    }


    private static boolean hasShipDiagonal(int row, int col, int[][] field) {
        return col - 1 >= 0 && hasShipUp(row, col - 1, field);
    }

    private static boolean hasShipLeft(int row, int col, int[][] field) {
        return col - 1 >= 0 && field[row][col - 1] == 1;
    }

    private static boolean hasShipUp(int row, int col, int[][] field) {
        return row - 1 >= 0 && field[row - 1][col] == 1;
    }

    record Coordinate(int x, int y) {

    }

    static final class Ship {
        private int length;
        private Direction direction;

        Ship(int length, Direction direction) {
            this.length = length;
            this.direction = direction;
        }

        Ship() {
            this(1, Direction.UNKNOWN);
        }

        public int length() {
            return length;
        }

        public Direction direction() {
            return direction;
        }

        public void setLength(int length) {
            this.length = length;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass())
                return false;
            var that = (Ship) obj;
            return this.length == that.length &&
                    Objects.equals(this.direction, that.direction);
        }

        @Override
        public int hashCode() {
            return Objects.hash(length, direction);
        }

        @Override
        public String toString() {
            return "Ship[" +
                    "length=" + length + ", " +
                    "direction=" + direction + ']';
        }


        public void setDirection(Direction direction) {
            this.direction = direction;
        }

    }
}

enum Direction {
    HORIZONTAL,
    VERTICAL,
    UNKNOWN
}
