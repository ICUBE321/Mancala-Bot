package mancala_game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
2
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
        ArrayList<Integer> holes = new ArrayList<Integer>();//list to store potential hole numbers
        ArrayList<Integer> scores = new ArrayList<Integer>();//list to store the possibles scores of the potential hole numbers
        
        
        hole = 1; //hole number
        index = hole - 1; //corresponding array index
        //case win and case manc
        while(index < 6) {
            if(case_win(player1Marbles, index, hole) && !check_empty(player1Marbles,index)) {//checks if you land a mancala and get a free turn
            	System.out.println(hole);//select hole
            	return;//exit function
            } else if(case_manc(player1Marbles, index, hole) && !check_empty(player1Marbles,index)) {//checks if you can land past mancala
                holes.add(hole);//holding the value for the hole number for prev case
                scores.add(player1Mancala + 1);//holding the potential score if the hole is picked
            } 
                hole++;//updates hole number to the next hole
                index++;//updates index number to the next index
        }
        
        //changing the starting hole for the next case
        hole = 1;
        index = hole - 1;  
        //case 4
        while(index < 6) {
            if(player2Marbles[index] == 13 && !check_empty(player1Marbles, (7 - hole)-1)) {//checks if the opponent has 13 marbles and your corresponding hole is not empty
                System.out.println(7-hole);//select hole
                return;//exit function
            } 
                hole++;//updates hole number to the next hole
                index++;//updates index number to the next index
        }
        
        //changing the starting hole for the next case
        hole = 2; 
        index = hole - 1;
        //case 1
        int target;//for holding the empty hole's hole number
        while(index < 6) {    
            if(player1Marbles[index] == 0 && player2Marbles[5-index] > 0) {//checks if you have an empty hole and opponents corresponding hole is not empty
                target = hole;//stores your empty hole's number as target
                //checking holes before the target to see if any can land in the target
                for(int i=index-1; i>=0; i--) {
                    //if a hole has enough seeds to land in the target
                    if(player1Marbles[i] == target-(i+1) && !check_empty(player1Marbles,i)) {
                        holes.add(i+1);//holding the value for the hole number for prev case
                        scores.add(player1Mancala + player1Marbles[target - 1] + player2Marbles[6 - target]);//holding the potential score if hole is picked
                    }
                }
            }
                hole++;//updates hole number to the next hole
                index++;//updates index number to the next index
        }
        
        //changing the starting hole for the next case
        hole = 1;
        index = hole - 1;  
        //case 3
        while(index < 6) {
            if(player1Marbles[index] == 13 && !check_empty(player1Marbles,index)) {//checks if you have 13 marbles 
            	System.out.println(hole);//select hole
                return;//exit function
                 //holes.add(hole);//holding the value for the hole number for prev case
                 //scores.add(player1Mancala);//holding the potential score if hole is picked
            } 
                hole++;//updates hole number to the next hole
                index++;//updates index number to the next index
        }
        
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
       		if(!check_empty(player1Marbles,i)) {
       			System.out.print(i+1);
       			return;
       		}
        }
    }
    
    //function to check if you land a mancala and get a free turn
    public static Boolean case_win(int playerMarbles[], int index, int hole) {
        Boolean ans = false;
        if(playerMarbles[index] == 7-hole) {
            ans = true;
        }
        return ans;
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
