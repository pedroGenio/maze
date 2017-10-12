package com.maze.util;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Pedro de Deus
 */
public class Config {

    public final static String INPUT = "input";
    public final static String LARGE_INPUT = "large_input";
    public final static String MEDIUM_INPUT = "medium_input";
    public final static String SMALL = "small";
    public final static String SPARSE_MEDIUM = "sparse_medium";
    public final static String exampleFile = "example";
    public final static String EXAMPLE = Messages.getInstance().getMybundle().getString("example");
    public final static String ALL = Messages.getInstance().getMybundle().getString("all");
    public final static String OTHER = Messages.getInstance().getMybundle().getString("other");
    public final static List<String> fileList = Arrays.asList(INPUT, MEDIUM_INPUT, LARGE_INPUT, SPARSE_MEDIUM, SMALL, EXAMPLE, ALL, OTHER);
    public final static String extension = ".txt";


}
