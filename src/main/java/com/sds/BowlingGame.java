package com.sds;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;

public class BowlingGame {

    private ArrayList<Integer>[] round_scores = new ArrayList[10];
    private ArrayList<Integer> round_total_score = new ArrayList<Integer>(10);
    private Integer round;
    private Integer total_scores;
    private Deque<Integer> spare_bonus_round;
    private Deque<Integer> strike_bonus_round;

    private Integer last_count;

    BowlingGame() {
        round = 0;
        spare_bonus_round = new ArrayDeque<Integer>();
        strike_bonus_round = new ArrayDeque<Integer>();

        for (int i = 0; i < 10; i++) {
            round_scores[i] = new ArrayList<Integer>(3);
            round_total_score.add(0);
        }
        this.total_scores = 0;
    }

    public void roll(int pins) {
        if (round>9){
            throw new IllegalArgumentException("");
        }
        // round 결과 입력
        round_scores[round].add(pins);

        // 이전 프레임에 보너스 점수 계산

        while (!spare_bonus_round.isEmpty()){
            Integer round_for_get_bonus = spare_bonus_round.removeFirst();
            round_total_score.set(round_for_get_bonus, round_total_score.get(round_for_get_bonus) + pins);
        }

        while (!strike_bonus_round.isEmpty()){
            spare_bonus_round.add(strike_bonus_round.removeFirst());
        }


        round_total_score.set(round,round_total_score.get(round)+pins);


        if(round<9 && round_total_score.get(round) > 10 ){
            throw new IllegalStateException("exceed 10 points");
        }
        else if(round==9 && round_total_score.get(round) >30){
            throw new IllegalStateException("exceed 30 points");

        }

        // round 변경 구문
        // 2개 던지고 변겅
        if(round < 9){
            if(round_scores[round].size()>1){
                if(round_scores[round].get(0) + round_scores[round].get(1) == 10){
                    spare_bonus_round.add((round));
                }
                this.round++;
            } else if (pins==10) { // Strike시 변경
                spare_bonus_round.addFirst(round);
                strike_bonus_round.addFirst(round);
                this.round++;
            }
        }

        else if(round==9){
            if (round_scores[round].size()>2) {
                this.round++;
            }
            else if(round_scores[round].size()>1){
                if(round_scores[round].get(0) + round_scores[round].get(1) > 10){
                    if(round_scores[round].get(0) != 10){
                        throw new IllegalStateException("exceed 10 points");
                    }
                }
                else{
                    if(round_scores[round].get(0) + round_scores[round].get(1) != 10){
                        this.round++;
                    }
                }
            }
        }
    }

    public int score() {
        for (int i = 0; i < 10; i++) {
            total_scores+= round_total_score.get(i);
        }
        return total_scores;
    }



    


}