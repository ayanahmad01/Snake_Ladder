package com.example.snake_ladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class Player {
    private Circle coin;
    private int currPosition;
    private String name;

    private static Board gameBoard=new Board();

    public Player(int tileSize, Color coinColor, String playerName){
        coin= new Circle(tileSize/2);
        coin.setFill(coinColor);
        currPosition=0;
        movePlayer(1);
        name=playerName;
    }

    public void movePlayer(int diceVal){
        if(currPosition+diceVal<=100){
            currPosition+=diceVal;

            TranslateTransition firstMove=translateAnimation(diceVal), secondMove=null;
            translateAnimation(diceVal);

            int newPos= gameBoard.getNewPos(currPosition);
            if(newPos!=currPosition&&newPos!=-1){
                currPosition=newPos;
                secondMove=translateAnimation(6);
            }

            if(secondMove==null) firstMove.play();
            else{
                SequentialTransition sequentialTransition =new SequentialTransition(firstMove,
                        new PauseTransition(Duration.millis(200)), secondMove);
                sequentialTransition.play();
            }
        }
    }

    private TranslateTransition translateAnimation(int diceVal){
        TranslateTransition animate=new TranslateTransition(Duration.millis(150*diceVal),coin);
        animate.setToX(gameBoard.getXCoordinates(currPosition));
        animate.setToY(gameBoard.getYCoordinates(currPosition));
        animate.setAutoReverse(false);
        return animate;
    }

    boolean isWinner(){
        if(currPosition==100) return true;
        return false;
    }

    public void startingPos(){
        currPosition=0;
        movePlayer(1);
    }

    public Circle getCoin() {
        return coin;
    }

    public int getCurrPosition() {
        return currPosition;
    }

    public String getName() {
        return name;
    }
}
