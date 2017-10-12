package com.maze.service;

import com.maze.domain.Maze;
import com.maze.util.Messages;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Pedro de Deus
 */
public class MazeService {

    private Maze maze;

    public MazeService() {
        this.maze = new Maze();
    }
    
    /**
     * Method to read files
     * @param fileName
     */
    public void readingFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            handleLine(maze, br);
        } catch (FileNotFoundException ex) {
            System.out.println(Messages.getInstance().getMybundle().getString("fileNotFound"));
        } catch (IOException ex) {
        }
    }

    /**
     * Reading each line and solving the maze
     *
     * @param maze
     * @param br
     * @throws IOException
     */
    private void handleLine(Maze maze, BufferedReader br) throws IOException {
        String line;
        int counter = 0;
        int rowNumber = 0;
        while ((line = br.readLine()) != null) {
            counter++;

            switch (counter) {
                case 1:
                    setMeasure(line);
                    break;
                case 2:
                    setStart(line);
                    break;
                case 3:
                    setEnd(line);
                    break;
                default:
                    if (maze.getMaze() == null) {
                        this.maze.setMaze(new String[maze.getHEIGHT()][maze.getWIDTH()]);
                    }

                    buildingMaze(line, rowNumber);
                    rowNumber++;
                    break;
            }
        }

        if (!findPath(maze.getEND_Y(), maze.getEND_X())) {
            Messages.getInstance().noSolution();
        } 
        printMaze();
        startEnd();

    }

    /**
     * 
     */
    private void printMaze() {
        for (String[] maze1 : maze.getMaze()) {
            final List<String> itemList = Arrays.asList(maze1);
            final String commaSeparated = String.join("", itemList);
            System.out.println(commaSeparated.replace("1", "#").replace("0", " "));
        }
    }

    /**
     *
     * @param line
     * @param counter2
     */
    private void buildingMaze(String line, int rowNumber) {
        Stream<Character> characterStream = returnStreamCharacter(line);
        List<String> result = returnListCharacter(characterStream);
        for (int i = 0; i < result.size(); i++) {
            maze.getMaze()[rowNumber][i] = result.get(i);
        }
    }

    /**
     *
     * @param line
     * @return
     */
    private Stream<Character> returnStreamCharacter(String line) {
        Stream<Character> characterStream = line.chars().mapToObj(c -> (char) c);
        return characterStream;
    }

    /**
     *
     * @param characterStream
     * @return
     */
    private List<String> returnListCharacter(Stream<Character> characterStream) {
        List<Character> result = characterStream.filter(x -> !Character.isWhitespace(x)).collect(Collectors.toList());
        List<String> listString = new ArrayList<>();
        result.forEach((c) -> {
            listString.add(Character.toString(c));
        });
        return listString;
    }

    /**
     * Main method, where everything happens
     *
     * @param y
     * @param x
     * @return
     */
    private boolean findPath(int y, int x) {

        maze.getMaze()[y][x] = "X";
        if (checkWin(y, x)) {
            return true;
        }

        // Up
        if (maze.getMaze()[y + 1][x].equals("0") && findPath(y + 1, x)) {
            return true;
        }
        // Down
        if (maze.getMaze()[y - 1][x].equals("0") && findPath(y - 1, x)) {
            return true;
        }
        // Left
        if (maze.getMaze()[y][x - 1].equals("0") && findPath(y, x - 1)) {
            return true;
        }
        // Right
        if (maze.getMaze()[y][x + 1].equals("0") && findPath(y, x + 1)) {
            return true;
        }

        maze.getMaze()[y][x] = " ";
        return false;
    }

    /**
     *
     * @param y
     * @param x
     * @return
     */
    private boolean checkWin(int y, int x) {
        return x == maze.getSTART_X() && y == maze.getSTART_Y();
    }

    /**
     * Start and End position
     */
    private void startEnd() {
        this.maze.getMaze()[maze.getSTART_Y()][maze.getSTART_X()] = "S";
        this.maze.getMaze()[maze.getEND_Y()][maze.getEND_X()] = "E";
    }

    /**
     *
     * @param line
     */
    private void setMeasure(String line) {
        maze.setWIDTH(Integer.valueOf(line.split(" ")[0]));
        this.maze.setHEIGHT(Integer.valueOf(line.split(" ")[1]));
    }

    /**
     *
     * @param line
     */
    private void setStart(String line) {
        maze.setSTART_X(Integer.valueOf(line.split(" ")[0]));
        this.maze.setSTART_Y(Integer.valueOf(line.split(" ")[1]));
    }

    /**
     *
     * @param line
     */
    private void setEnd(String line) {
        this.maze.setEND_X(Integer.valueOf(line.split(" ")[0]));
        this.maze.setEND_Y(Integer.valueOf(line.split(" ")[1]));
    }

}
