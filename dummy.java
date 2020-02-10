package mancala_game;

import java.io.*;
import java.util.*;

public class dummy {
    
    // returns random integer between minimum and maximum range
    public static int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }
    
    public static void printNextMove(int player, int player1Mancala, int player1Marbles[], int player2Mancala, int player2Marbles[]){
        
        int hole, index;
        ArrayList<Integer> holes = new ArrayList<Integer>();//list to store potential hole numbers
        ArrayList<Integer> scores = new ArrayList<Integer>();//list to store the possibles scores of the potential hole numbers
        
        //case random 
        hole = getRandomInteger(6,1);//selects a random integer from 1 to 6
        index = hole - 1;
        while(player1Marbles[hole] == 0 || index > 5 || index < 0){//check if the random hole is valid
            hole = getRandomInteger(6,1);
            index = hole - 1;
        }
        if(case_manc(player1Marbles, index, hole) && !check_empty(player1Marbles,index)) {//checks if you can land past mancala
            holes.add(hole);//holding the value for the hole number for prev case
            scores.add(player1Mancala + 1);//holding the potential score if hole is picked
        } else if(!check_empty(player1Marbles,index)){
            holes.add(hole);//holding the value for the hole number for prev case
            scores.add(player1Mancala);//holding the potential score if hole is picked
        }
        
        if(!scores.isEmpty()) {//checks if no case held
            int max_index = scores.indexOf(Collections.max(scores));//get the index of the max score
            if(!check_empty(player1Marbles,max_index)) {
                System.out.print(holes.get(max_index));//picks the hole of that max score
                return;
            }
        } 
        
           for(int i=0; i<6;i++) {//picks randomly
               if(!(check_empty(player1Marbles,i))) {
                   System.out.print(i+1);
                   return;
               }
        }
    }
    
    public static Boolean case_manc(int playerMarbles[], int index, int hole) {
        Boolean answ = false;
        if(playerMarbles[index] > 7-hole) {
            answ = true;
        }
        return answ;
    }
    
    public static Boolean check_empty(int playerMarbles[], int index){
        Boolean answe = false;
        if(playerMarbles[index] == 0) {
            answe = true;
        }
        return answe;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
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