package com.sds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {


    @Test
    public void whenPlaysGutterGameThenPointsZero(){

        //given
        BowlingGame game = new BowlingGame();

        //when
        for(int i=0;i<10;i++){
            game.roll(0);
            game.roll(0);
        }

        //then
        assertEquals(0,game.score());
    }


    @Test
    public void whenPlaysOnepinThenPointsOne(){
        BowlingGame game = new BowlingGame();


        game.roll(1);
        game.roll(0);


        //when
        for(int i=1;i<10;i++){
            game.roll(0);
            game.roll(0);
        }

        //then
        assertEquals(1,game.score());
    }

    @Test
    public void whenPlayRollThreBallOneRoundThenGetError(){


        assertThrows(IllegalArgumentException.class, ()->{
            BowlingGame game = new BowlingGame();

            game.roll(1);
            game.roll(0);
            game.roll(0);

            //when
            for(int i=1;i<10;i++){
                game.roll(0);
                game.roll(0);
            }
        });
    }


    @Test
    public void whenPlaySpareThenGetBonus(){

        BowlingGame game = new BowlingGame();


        game.roll(7);
        game.roll(3);
        game.roll(1);
        game.roll(0);


        //when
        for(int i=2;i<10;i++){
            game.roll(0);
            game.roll(0);
        }

        //then
        assertEquals(12,game.score());

    }

    @Test
    public void whenPlayStrikeThenGetTwoBonus(){
        BowlingGame game = new BowlingGame();

        //when
        game.roll(10);

        game.roll(1);
        game.roll(1);

        for(int i=2;i<10;i++){
            game.roll(0);
            game.roll(0);
        }

        //then
        assertEquals(14,game.score());

    }

    @Test
    public void whenPlayOver10PointsThenThrowError(){

        BowlingGame game = new BowlingGame();
        assertThrows(IllegalStateException.class, ()->{
            game.roll(4);
            game.roll(7);

            for(int i=1 ;i<10;i++){
                game.roll(0);
                game.roll(0);
            }
        });

    }

    @Test
    public void whenPerfectGamethen300Points(){
        BowlingGame game = new BowlingGame();

        //when
        for(int i=0;i<12;i++){
            game.roll(10);
        }
        assertEquals(300,game.score());
    }

    @Test
    public void whenSpareLastGameThenGet3Chance(){
        BowlingGame game = new BowlingGame();
        for(int i=0;i<9;i++){
            game.roll(0);
            game.roll(0);
        }

        game.roll(10);
        game.roll(3);
        game.roll(0);

        assertEquals(13,game.score());

    }

    @Test
    public void whenRealGamethen188Points(){
        BowlingGame game = new BowlingGame();

        //when
        game.roll(10);
        game.roll(9);
        game.roll(1);
        game.roll(7);
        game.roll(0);
        game.roll(9);
        game.roll(1);
        game.roll(10);
        game.roll(10);
        game.roll(8);
        game.roll(2);
        game.roll(10);
        game.roll(9);
        game.roll(1);
        game.roll(9);
        game.roll(1);
        game.roll(7);

        assertEquals(188,game.score());
    }

    @Test
    public void whenRealGameOver10roundthenThrowError(){


        assertThrows(IllegalArgumentException.class, ()->{
            BowlingGame game = new BowlingGame();

            //when
            game.roll(10);
            game.roll(9);
            game.roll(1);
            game.roll(7);
            game.roll(0);
            game.roll(9);
            game.roll(1);
            game.roll(10);
            game.roll(10);
            game.roll(8);
            game.roll(2);
            game.roll(10);
            game.roll(9);
            game.roll(1);
            game.roll(9);
            game.roll(1);
            game.roll(7);
            game.roll(3);
        });

    }





}
