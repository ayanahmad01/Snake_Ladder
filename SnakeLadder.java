package com.example.snake_ladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize=40, width=10, height=10;
    public static final int buttonLine=height*tileSize+50, infoLine=buttonLine-30;

    private static Dice dice=new Dice();
    private Player player1, player2;
    private boolean gameStarted=false, player1Turn=false, player2Turn=false;
    private Pane createContent(){
        Pane root=new Pane();
        root.setPrefSize(width*tileSize, height*tileSize+100);

        for(int i=0;i<height;i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().addAll(tile);
            }
        }

        Image img= new Image("C:\\Users\\khalid\\IdeaProjects\\Snake_Ladder\\src\\main\\image.jpg");
        ImageView board= new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);


        Button player1Button=new Button("Black");
        Button player2Button=new Button("White");
        Button startButton=new Button("Start");

        player1Button.setTranslateY(buttonLine);
        player1Button.setTranslateX(30);
        player1Button.setDisable(true);
        player2Button.setTranslateY(buttonLine);
        player2Button.setTranslateX(315);
        player2Button.setDisable(true);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(180);

        Label player1Label=new Label("");
        Label player2Label=new Label("");
        Label diceLabel=new Label("Start the Game");

        player1Label.setTranslateY(infoLine);
        player1Label.setTranslateX(20);
        player2Label.setTranslateY(infoLine);
        player2Label.setTranslateX(300);
        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(160);

        player1=new Player(tileSize, Color.BLACK, "Black");
        player2=new Player(tileSize-8,Color.WHITE,"White");

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted=true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);
                player1Turn=true;
                player1Label.setText("Your Turn "+player1.getName());
                player1Button.setDisable(false);
                player1.startingPos();

                player2Turn=false;
                player2Label.setText("");
                player2Button.setDisable(true);
                player2.startingPos();
            }
        });

        player1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(player1Turn){
                        int diceVal= dice.getRolledDiceVal();
                        diceLabel.setText("Dice Value: "+diceVal);
                        player1.movePlayer(diceVal);

                        if(player1.isWinner()){
                            diceLabel.setText("  "+player1.getName()+" Wins!!");
                            player1Turn=false;
                            player1Button.setDisable(true);
                            player1Label.setText("");

                            player2Turn=false;
                            player2Button.setDisable(true);
                            player2Label.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStarted=false;
                        }
                        else{
                            player1Turn=false;
                            player1Button.setDisable(true);
                            player1Label.setText("");

                            player2Turn=true;
                            player2Button.setDisable(false);
                            player2Label.setText("Your Turn "+player2.getName());
                        }
                    }
                }
            }
        });

        player2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(player2Turn){
                        int diceVal= dice.getRolledDiceVal();
                        diceLabel.setText("Dice Value: "+diceVal);
                        player2.movePlayer(diceVal);

                        if(player2.isWinner()){
                            diceLabel.setText("  "+player2.getName()+" Wins!!");
                            player1Turn=false;
                            player1Button.setDisable(true);
                            player1Label.setText("");

                            player2Turn=false;
                            player2Button.setDisable(true);
                            player2Label.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStarted=false;
                        }
                        else{
                            player1Turn=true;
                            player1Button.setDisable(false);
                            player1Label.setText("Your Turn "+player1.getName());

                            player2Turn=false;
                            player2Button.setDisable(true);
                            player2Label.setText("");
                        }
                    }
                }
            }
        });


        root.getChildren().addAll(board,player1Button,player2Button,startButton,player1Label,player2Label,
                diceLabel,player1.getCoin(),player2.getCoin());


        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}