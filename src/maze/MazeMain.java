package maze;

import com.maze.service.MazeService;
import com.maze.util.Config;
import com.maze.util.Messages;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Pedro de Deus
 */
public class MazeMain {

    public static void main(String[] args) {
        try {
            Messages.getInstance().messageLanguage();

            run(Messages.getInstance().messageOptionMaze());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param messageOptionMaze
     */
    private static void run(int messageOptionMaze) {
        runReadingFile(runPath(), messageOptionMaze);
    }

    /**
     *
     * @return
     */
    private static String runPath() {

        String path = "";

        String runningJarName = new MazeMain().getRunningJarName();
        if (runningJarName != null) {
            path = "./";
        } else {
//            final File f = new File(MazeMain.class.getProtectionDomain().getCodeSource().getLocation().getPath());
//        path = f.getPath() + File.separator;

            System.out.println(Messages.getInstance().getMybundle().getString("filePath"));
            Scanner scanner = new Scanner(System.in);
            path = scanner.nextLine() + File.separator;
        }

        return path;
    }

    /**
     *
     * @param path
     * @param i
     */
    private static void runReadingFile(String path, int i) {
        try {
            MazeService mazeService = new MazeService();
            Messages.lineSeparator();
            switch (i) {
                case 0:
                    System.out.println(Config.INPUT);
                    mazeService.readingFile(path + Config.INPUT + Config.extension);
                    break;
                case 1:
                    System.out.println(Config.MEDIUM_INPUT);
                    mazeService.readingFile(path + Config.MEDIUM_INPUT + Config.extension);
                    break;
                case 2:
                    System.out.println(Config.LARGE_INPUT);
                    mazeService.readingFile(path + Config.LARGE_INPUT + Config.extension);
                    break;
                case 3:
                    System.out.println(Config.SPARSE_MEDIUM);
                    mazeService.readingFile(path + Config.SPARSE_MEDIUM + Config.extension);
                    break;
                case 4:
                    System.out.println(Config.SMALL);
                    mazeService.readingFile(path + Config.SMALL + Config.extension);
                    break;
                case 5:
                    System.out.println(Config.EXAMPLE);
                    mazeService.readingFile(path + Config.exampleFile + Config.extension);
                    break;
                case 6:
                    runAll(path);
                    break;
                case 7:
                    System.out.println(Messages.getInstance().getMybundle().getString("fileName"));
                    Scanner scanner = new Scanner(System.in);
                    String name = scanner.nextLine();
                    mazeService.readingFile(path + name);
                    break;

                default:
                    throw new Exception(Messages.getInstance().errorMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param path
     */
    private static void runAll(String path) {
        for (int val = 0; val <= 5; val++) {
            runReadingFile(path, val);
        }
    }

    /**
     * Just to know if it's running by jar
     * @return 
     */
    private String getRunningJarName() {
        String className = this.getClass().getName().replace('.', '/');
        String classJar
                = this.getClass().getResource("/" + className + ".class").toString();
        if (classJar.startsWith("jar:")) {
            String vals[] = classJar.split("/");
            for (String val : vals) {
                if (val.contains("!")) {
                    return val.substring(0, val.length() - 1);
                }
            }
        }
        return null;
    }

}
