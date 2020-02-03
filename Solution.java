package mancala_game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
1
32
1 1 3 1 0 0 
9
1 0 0 0 0 0
*/
public class Solution {
       
    // returns random integer between minimum and maximum range
    public static int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }
    
    public static void printNextMove(int player, int player1Mancala, int player1Marbles[], int player2Mancala, int player2Marbles[]){
        
        int hole, index;
        ArrayList<Integer> holes = new ArrayList<Integer>();
        ArrayList<Integer> scores = new ArrayList<Integer>();
        
        //case free turn
        if(player1Marbles[5] == 1) {
            hole = 6;
            System.out.print(hole);
            return;
        }
        
        //changing the starting hole for the next case
        hole = 2; 
        index = hole - 1;
        //case 1
        int target;//for holding the empty hole's hole number
        while(index < 6) {    
            if(player1Marbles[index] == 0 && player2Marbles[5-index] > 0) {
                target = hole;
                //checking holes before the target
                for(int i=index-1; i>=0; i--) {
                    //if a hole has enough seeds to land in the target
                    if(player1Marbles[i] == target-(i+1)) {
                        hole = i + 1; //get the hole number
                        //System.out.println(hole);
                        //return;
                        holes.add(hole);//holding the value for the hole number for prev case
                        scores.add(player1Mancala + player1Marbles[target - 1] + player2Marbles[6 - target]);//holding the potential score if hole is picked
                    }
                }
            }
                hole++;
                index++;
        }
        
        hole = 1; //hole number
        index = hole - 1; //corresponding array index
      //case win and case manc
        while(index < 6) {
            if(case_win(player1Marbles, index, hole)) {
                //System.out.println(hole);
                //return;
                holes.add(hole);//holding the value for the hole number for prev case
                scores.add(player1Mancala + 1);//holding the potential score if hole is picked
            } else if(case_manc(player1Marbles, index, hole)) {
                holes.add(hole);//holding the value for the hole number for prev case
                scores.add(player1Mancala + 1);//holding the potential score if hole is picked
            } 
                hole++;
                index++;
        }
        
        //changing the starting hole for the next case
        hole = 1;
        index = hole - 1;  
        //case 3
        while(index < 6) {
            if(player1Marbles[index] == 13) {
                //System.out.println(hole);
                //return;
                 holes.add(hole);//holding the value for the hole number for prev case
                 scores.add(player1Mancala + player1Marbles[hole - 1] + player2Marbles[6 - hole]);//holding the potential score if hole is picked
            } 
                hole++;
                index++;
        }
        //the random integer to choose which hole to pick
        hole = getRandomInteger(6,1);
        while(player1Marbles[hole] == 0){
            hole = getRandomInteger(6,1);
            index = hole - 1;
        }
        if(case_manc(player1Marbles, index, hole)) {
            holes.add(hole);//holding the value for the hole number for prev case
            scores.add(player1Mancala + 1);//holding the potential score if hole is picked
        } else {
            holes.add(hole);//holding the value for the hole number for prev case
            scores.add(player1Mancala);//holding the potential score if hole is picked
        }
        
        int max_index = scores.indexOf(Collections.max(scores));
        System.out.print(holes.get(max_index));
    }
    
    public static Boolean case_win(int playerMarbles[], int index, int hole) {
        Boolean ans = false;
        if(playerMarbles[index] == 7-hole) {
            ans = true;
        }
        return ans;
    }
    
    public static Boolean case_manc(int playerMarbles[], int index, int hole) {
        Boolean answ = false;
        if(playerMarbles[index] >= 7-hole) {
            answ = true;
        }
        return answ;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int _player;
        _player = in.nextInt();
        
        int _player1Mancala;
        _player1Mancala = in.nextInt();
        
        
        int _player1Marbles_size = 6;
        int[] _player1Marbles = new int[_player1Marbles_size];
        int _player1Marbles_item;
        for(int _player1Marbles_i = 0; _player1Marbles_i < _player1Marbles_size; _player1Marbles_i++) {
            _player1Marbles_item = in.nextInt();
            _player1Marbles[_player1Marbles_i] = _player1Marbles_item;
        }
        
        int _player2Mancala;
        _player2Mancala = in.nextInt();
        
        
        int _player2Marbles_size = 6;
        int[] _player2Marbles = new int[_player2Marbles_size];
        int _player2Marbles_item;
        for(int _player2Marbles_i = 0; _player2Marbles_i < _player2Marbles_size; _player2Marbles_i++) {
            _player2Marbles_item = in.nextInt();
            _player2Marbles[_player2Marbles_i] = _player2Marbles_item;
        }
        
        if(_player == 1) {
            printNextMove(_player, _player1Mancala, _player1Marbles, _player2Mancala, _player2Marbles);
        } else if(_player == 2) {
            printNextMove(_player, _player2Mancala, _player2Marbles,  _player1Mancala,  _player1Marbles);
        }
    }

}
