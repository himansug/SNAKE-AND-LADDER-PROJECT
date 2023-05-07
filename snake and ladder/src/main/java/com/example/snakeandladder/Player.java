package com.example.snakeandladder;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
   private Circle coin;
   private int currentPosition;

    private String name;

    private static Board gameBoard=new Board();
    public Player(int titleSize,Color coinColor ,String playerName){
        coin =new Circle(titleSize/2);
        coin.setFill(coinColor);
        currentPosition=0;//youc an set0 out 0f board
        movePlayer(1);
        name=playerName;

    }


    public void movePlayer(int diceValue){
        if(currentPosition+diceValue<=100) {
            currentPosition += diceValue;

            TranslateTransition secondMove=null,firstMove= translateAnimation(diceValue);
            //translateAnimation(diceValue);


            int newPosition= gameBoard.getNewPosition(currentPosition);
            if(newPosition!=currentPosition && newPosition!=-1){

               currentPosition =newPosition;
            secondMove=translateAnimation(6);
            }
            if(secondMove==null){
                firstMove.play();
            }
            else{
                SequentialTransition sequentialTransition=new SequentialTransition(firstMove,new PauseTransition(Duration.millis(1000)),secondMove);
                sequentialTransition.play();
            }
        }

      //  int x=gameBoard.getXCoordinate(currentPosition);
       // int y=gameBoard.getYCoordinate(currentPosition);
       // coin.setTranslateX(x);
       // coin.setTranslateY(y);
        //translateAnimation(diceValue);

    }
private TranslateTransition translateAnimation(int diceValue) {
    TranslateTransition animate = new TranslateTransition(Duration.millis(500+diceValue), coin);
    animate.setToX(gameBoard.getXCoordinate(currentPosition));
    animate.setToY(gameBoard.getYCoordinate(currentPosition));
animate.setAutoReverse(false);
return animate;
}


public void startingPosition(){
        currentPosition=0;
        movePlayer(1);


}
boolean isWinner(){
        if(currentPosition==100)return true;

        return false;
}



    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }
}
