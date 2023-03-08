package com.example.demo;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Canvas {
    @Getter
    private String[][] colorGrid;
    @Getter
    private final int width = 3;
    @Getter
    private final int height = 3;
    private String currentTurn;
    @Getter
    private boolean hasWon;
    @Getter
    private String won;

    public Canvas() {
        colorGrid = new String[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                colorGrid[y][x] = "";
            }
        }
        currentTurn = "X";
        hasWon = false;
        won = "";
    }

    public Canvas paint(PaintMessage paintMessage) {
        int posX = paintMessage.getPosX();
        int posY = paintMessage.getPosY();
        String color = paintMessage.getColor();
        if(!hasWon){
            if (posX >= 0 && posX < width && posY >= 0 && posY < height && !color.equals("")) {
                if (color.equals(currentTurn) && colorGrid[posY][posX].equals("")){
                    colorGrid[posY][posX] = color;
                    checkWon();
                    changeTurn();
                }

            }
        }
        System.out.println(Arrays.deepToString(colorGrid));
        return this;
    }

    private void changeTurn(){
        if (currentTurn.equals("X")){
            currentTurn = "O";
        } else {
            currentTurn = "X";
        }
    }

    private void checkWon(){
        for(int i=0; i<3; i++){
            if(checkRow(i) || checkCol(i)){
                hasWon = true;
                return;
            }
        }
        hasWon = checkCross();
    }

    private boolean checkRow(int row){
        if (colorGrid[row][0].equals(colorGrid[row][1]) && colorGrid[row][0].equals(colorGrid[row][2])){
            if(!colorGrid[row][0].equals("")){
                won = colorGrid[row][0];
                return true;
            }
        }
        return false;
    }

    private  boolean checkCol(int col){
        if(colorGrid[0][col].equals(colorGrid[1][col]) && colorGrid[0][col].equals(colorGrid[2][col])){
            if(!colorGrid[0][col].equals("")){
                won = colorGrid[0][col];
                return true;
            }
        }
        return false;
    }

    private boolean checkCross(){
        if(colorGrid[0][0].equals(colorGrid[1][1]) && colorGrid[0][0].equals(colorGrid[2][2])){
            if(!colorGrid[1][1].equals("")){
                won = colorGrid[1][1];
                return true;
            }
        } else if (colorGrid[0][2].equals(colorGrid[1][1]) && colorGrid[0][2].equals(colorGrid[2][0])){
            if(!colorGrid[1][1].equals("")){
                won = colorGrid[1][1];
                return true;
            }
        }
        return false;
    }

    public Canvas newGame(){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                colorGrid[y][x] = "";
            }
        }
        currentTurn = "X";
        hasWon = false;
        won = "";
        return this;
    }
}
