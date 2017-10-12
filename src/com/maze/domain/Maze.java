package com.maze.domain;

/**
 *
 * @author Pedro de Deus
 */
public class Maze {

    private Integer WIDTH;
    private Integer HEIGHT;
    private Integer START_X;
    private Integer START_Y;
    private Integer END_X;
    private Integer END_Y;
    private String maze[][];

    public Integer getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(Integer WIDTH) {
        this.WIDTH = WIDTH;
    }

    public Integer getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(Integer HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public Integer getSTART_X() {
        return START_X;
    }

    public void setSTART_X(Integer START_X) {
        this.START_X = START_X;
    }

    public Integer getSTART_Y() {
        return START_Y;
    }

    public void setSTART_Y(Integer START_Y) {
        this.START_Y = START_Y;
    }

    public Integer getEND_X() {
        return END_X;
    }

    public void setEND_X(Integer END_X) {
        this.END_X = END_X;
    }

    public Integer getEND_Y() {
        return END_Y;
    }

    public void setEND_Y(Integer END_Y) {
        this.END_Y = END_Y;
    }

    public String[][] getMaze() {
        return maze;
    }

    public void setMaze(String[][] maze) {
        this.maze = maze;
    }

}
