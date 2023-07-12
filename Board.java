package com.example.snake_ladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    public ArrayList<Pair<Integer,Integer>> posCoordinates;
    public ArrayList<Integer> snakeLadderPos;
    public Board(){
        posCoordinates=new ArrayList<>();
        populatePosCoordinates();
        populateSnakeLadder();
    }

    private void populatePosCoordinates(){
        posCoordinates.add(new Pair<>(0,0));
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                int xCoord=0;
                if(i%2==0) xCoord=j*SnakeLadder.tileSize + SnakeLadder.tileSize/2;
                else xCoord=SnakeLadder.tileSize*SnakeLadder.height - (j*SnakeLadder.tileSize) - SnakeLadder.tileSize/2;

                int yCoord=SnakeLadder.tileSize*SnakeLadder.height - (i*SnakeLadder.tileSize) - SnakeLadder.tileSize/2;
                posCoordinates.add(new Pair<>(xCoord,yCoord));
            }
        }
    }

    private void populateSnakeLadder(){
        snakeLadderPos=new ArrayList<>();
        for (int i = 0; i < 101; i++) snakeLadderPos.add(i);

        snakeLadderPos.set(4,25);
        snakeLadderPos.set(13,46);
        snakeLadderPos.set(27,5);
        snakeLadderPos.set(33,49);
        snakeLadderPos.set(40,3);
        snakeLadderPos.set(42,63);
        snakeLadderPos.set(43,18);
        snakeLadderPos.set(50,69);
        snakeLadderPos.set(54,31);
        snakeLadderPos.set(62,81);
        snakeLadderPos.set(66,45);
        snakeLadderPos.set(74,92);
        snakeLadderPos.set(76,58);
        snakeLadderPos.set(89,53);
        snakeLadderPos.set(99,41);
    }

    public int getNewPos(int currentPos){
        if(currentPos>0&&currentPos<=100) return snakeLadderPos.get(currentPos);
        return -1;
    }

    int getXCoordinates(int position){
        if(position>=1&&position<=100) return posCoordinates.get(position).getKey();
        return -1;
    }
    int getYCoordinates(int position){
        if(position>=1&&position<=100) return posCoordinates.get(position).getValue();
        return -1;
    }

    public static void main(String[] args) {
        Board board=new Board();
        for (int i = 0; i < board.posCoordinates.size(); i++) {
            System.out.println(i +"$ x:"+ board.posCoordinates.get(i).getKey()+" y:"+ board.posCoordinates.get(i).getValue());
        }
    }
}
