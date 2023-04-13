package com.example.snakeandladder;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board {
    // Map Co-ordinated to the number
    ArrayList<Pair<Integer, Integer>> positionCoordinates;
    ArrayList<Integer> DragonLadderPosition;


    public Board(){
        positionCoordinates = new ArrayList<>();
        populatePositionCoordinates();
        populateDragonLadder();
    }

    // Adding values into the number (1-100)
    private void populatePositionCoordinates(){
        //Putting dummy values of 0th index
        positionCoordinates.add(new Pair<>(0,0));
        for (int i = 0; i < DragonLadder.height; i++) {
            for (int j = 0; j < DragonLadder.width; j++) {
                // Create X coordinates
                int xCord = 0;
                //int i1 = DragonLadder.tileSize * DragonLadder.height - (i * DragonLadder.tileSize) - DragonLadder.tileSize / 2;
                if(i % 2 == 0){
                    xCord = j * DragonLadder.tileSize + DragonLadder .tileSize/2;
                }
                else{
                    xCord = DragonLadder.tileSize * DragonLadder.height - (j * DragonLadder.tileSize) - DragonLadder.tileSize / 2;
                }
                // Create Y coordinates
                int yCord = DragonLadder.tileSize * DragonLadder.height - (i * DragonLadder.tileSize) - DragonLadder.tileSize / 2;
                positionCoordinates.add(new Pair<>(xCord,yCord));
            }
        }
    }

    private void populateDragonLadder(){
        DragonLadderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            // Mapped each index with same value
            DragonLadderPosition.add(i);
        }

        // Mapping Dragon and ladder
        DragonLadderPosition.set(2,23);
        DragonLadderPosition.set(6,45);
        DragonLadderPosition.set(20,59);
        DragonLadderPosition.set(30,16);
        DragonLadderPosition.set(37,3);
        DragonLadderPosition.set(52,72);
        DragonLadderPosition.set(56,8);
        DragonLadderPosition.set(57,96);
        DragonLadderPosition.set(74,94);
        DragonLadderPosition.set(78,98);
        DragonLadderPosition.set(84,64);
        DragonLadderPosition.set(87,31);
        DragonLadderPosition.set(98,40);
    }

    public int getNewPosition(int currentPosition){
        if(currentPosition > 0 && currentPosition <= 100){
            return DragonLadderPosition.get(currentPosition);
        }
        return -1;
    }

    // Get Co-ordinates to move the position of player
    int getXCoordinates(int position){
        if(position>=1 && position<=100)
            return positionCoordinates.get(position).getKey();
        return -1;
    }

    int getYCoordinates(int position){
        if(position>=1 && position<=100)
            return positionCoordinates.get(position).getValue();
        return -1;
    }


    public static void main(String[] args) {
        Board board = new Board();
        for (int i = 0; i < board.positionCoordinates.size(); i++) {
            System.out.println(i + "  $ x : " + board.positionCoordinates.get(i).getKey() +
                    " y : " + board.positionCoordinates.get(i).getValue()
            );
        }
    }
}
