package org.faktorzehn.dojo.battleshipsvalidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class BattleField {
    private static int[][] field;
    private static int rows;
    private static int cols;

    // 1. Wie viele von was?
    // OOOO x 1 Schlachtschiff
    // OOO  x 2 Kreuzer
    // OO   x 3 Zerstörer
    // O    x 4 Uboote
    private static ArrayList<Ships> ships = new ArrayList<>(){{
        add(Ships.BATTLESHIP);

        add(Ships.CRUISER);
        add(Ships.CRUISER);

        add(Ships.DESTROYER);
        add(Ships.DESTROYER);
        add(Ships.DESTROYER);

        add(Ships.SUBMARINE);
        add(Ships.SUBMARINE);
        add(Ships.SUBMARINE);
        add(Ships.SUBMARINE);
    }};

    public static boolean fieldValidator(int[][] requestField) {
        field = requestField;
        if( field == null ) return false;

        rows = field.length;
        cols = field[0].length;

        // [
        //     [ 0,0,0,0,0,0,0,0,0,0 ],
        //     [ 0,0,1,1,0,0,0,1,0,0 ],
        //     [ 1,0,0,0,0,0,0,0,0,0 ],
        //     [ 1,1,0,0,1,0,0,0,0,0 ],
        //     [ 1,0,0,0,1,0,0,0,0,0 ],
        //     [ 1,0,0,0,1,0,0,0,0,0 ],
        //     [ 0,0,0,0,0,0,0,0,0,0 ],
        //     [ 0,0,0,0,0,0,1,0,0,0 ],
        //     [ 1,0,0,1,1,1,1,0,1,0 ],
        //     [ 0,0,0,0,1,1,0,0,0,0 ]
        // ]


        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) { // out of bounce
                int element = field[x][y];
                if(element == 1)
                {
                    if(isUboot(x,y)){
                        if(ships.contains(Ships.SUBMARINE))
                        {
                            ships.remove(Ships.SUBMARINE);
                        }
                        else{
                            //Mehr als 4 Uboote gefunden
                            return false;
                        }

                    }
                    if(getDirection(x,y) == Direction.V){
                        if(!evaluateVertical(x,y)){
                            return false;
                        }
                    }
                    if(getDirection(x,y) == Direction.H){
                        if(!evaluateHorizontal(x,y)){
                            return false;
                        }

                    }
                }
                System.out.print(element + " ");
            }
            System.out.println();
        }

        return true;
    }

    private static boolean isUboot(int x, int y) {
        if(!(x >= field.length-1) && field[x+1][y] == 1){
             return false;
        }
        if(x >= 1 && field[x-1][y] == 1){
             return false;
        }
        if(!(y >= field[x].length-1) && field[x][y+1] == 1){
             return false;
        }
        if(y >= 1 && field[x][y-1] == 1){
             return false;
        }
        return true;
    }



    private static boolean evaluateHorizontal(int x, int y) {
       int counter = 0;

        for( int yy = y; yy < cols; yy++ ){
            if( field[yy][x] == 1){
                counter++;
            }else{
                break;
            }

            if( counter > 4 ) return false;
        }
        return true;
    }

    public static boolean evaluateVertical(int x, int y) {
        int counter = 0;

        for( int yy = y; yy < rows; yy++ ){
            if( field[x][yy] == 1){
                counter++;
            }else{
                break;
            }

            if( counter > 4 ) return false;
        }

        int finalCounter = counter;
        Optional<Ships> foundShip = Arrays.stream(Ships.values()).filter(s -> s.size == finalCounter).findFirst();

        // ship is not allowed anymore.
        if( foundShip.isPresent() )
            if( !ships.contains(foundShip.get()) ) {
                return false;
            } else {
                ships.remove(foundShip.get());
            }

        return true;
    }


    /**
     *
     * @param x coordinate of the Ship
     * @param y coordinate of the Ship
     * @return {@link Direction}
     */
    private static Direction getDirection(int x, int y) {
        System.out.println(field[x][y]);

        if ( field[x][y] == 1 ) {
            if ((y == 0 || field[x][y - 1] == 0) && (x == 0 || field[x - 1][y] == 0)) {
                if (y < cols - 1 && field[x][y + 1] == 1)
                    return Direction.H;

                if (x < rows - 1 && field[x + 1][y] == 1)
                    return Direction.V;
            }
        }
        return Direction.I; // Invalid or not the start of a ship
    }

    private enum Direction {
        V,
        H,
        I
    }

    private enum Ships {
        CRUISER(3),
        DESTROYER(2),
        SUBMARINE(1),
        BATTLESHIP(4);

        final int size;
        Ships(int size){
            this.size = size;
        }
    }
}