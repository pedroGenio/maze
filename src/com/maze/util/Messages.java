package com.maze.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author Pedro de Deus
 */
public class Messages {

    private static final Messages MESSAGES = new Messages();
    private static final Scanner scanner = new Scanner(System.in);
    private ResourceBundle mybundle;

    public static final Messages getInstance() {
        return MESSAGES;
    }

    private void setLocale(int i) throws Exception {
        try {
            switch (i) {
                case 1:
                    Locale.setDefault(new Locale("en", "NZ"));
                    break;
                case 2:
                    Locale.setDefault(new Locale("pt", "BR"));
                    break;
                default:
                    throw new Exception();
            }
            setMybundle(ResourceBundle.getBundle("com.maze.i18n/Bundle", Locale.getDefault()));
        } catch (Exception e) {
            throw new Exception(errorMessage());
        }
    }

    public void messageLanguage() throws Exception {
        Integer val = 0;

        if (Messages.availableLocale()) {
            setMybundle(ResourceBundle.getBundle("com.maze.i18n/Bundle", new Locale("en", "NZ")));
            System.out.println(MessageFormat.format(getMybundle().getString("language1"), getMybundle().getString("languageEN"), getMybundle().getString("languagePT")));
            try {
                val = Integer.valueOf(scanner.nextLine());
            } catch (NumberFormatException nfe) {
                throw new Exception(errorMessage());
            }
        } else {
            val = 1;
        }

        setLocale(val);
    }

    public void messageGreetings() {
        System.out.println(getMybundle().getString("message1"));
    }

    public void noSolution() {
        System.out.println(getMybundle().getString("noSolution"));
    }

    /**
     * 
     * @return 
     */
    public Integer messageOptionMaze() {
        Integer val = 0;

        lineSeparator();
        System.out.println(getMybundle().getString("message2"));
        for (int i = 0; i < Config.fileList.size(); i++) {
            System.out.println(i + " - " + Config.fileList.get(i));
        }
        
//        System.out.println("1 - " + Config.INPUT);
//        System.out.println("2 - " + Config.MEDIUM_INPUT);
//        System.out.println("3 - " + Config.LARGE_INPUT);
//        System.out.println("4 - " + Config.SPARSE_MEDIUM);
//        System.out.println("5 - " + Config.SMALL);
//        System.out.println("6 - " + Config.EXAMPLE);
//        System.out.println("7 - " + getMybundle().getString("all"));
//        System.out.println("8 - " + getMybundle().getString("other"));
        

        try {
            val = Integer.valueOf(scanner.nextLine());
        } catch (NumberFormatException nfe) {
        }

        return val;
    }

    /**
     * seeing if it's possible multiple language
     * @return 
     */
    private static boolean availableLocale() {
        Locale[] availableLocale = Locale.getAvailableLocales();

        boolean langBR = false;
        boolean langENG = false;
        for (Locale aLocale : availableLocale) {

            if (aLocale.getLanguage().equals("pt") && aLocale.getCountry().equals("BR")) {
                langBR = true;
            }

            if (aLocale.getLanguage().equals("en") && (aLocale.getCountry().equals("NZ") || aLocale.getCountry().equals("GB")
                    || aLocale.getCountry().equals("IN") || aLocale.getCountry().equals("AU") || aLocale.getCountry().equals("US"))) {
                langENG = true;
            }
        }

        return langBR && langENG;
    }

    public ResourceBundle getMybundle() {
        return mybundle;
    }

    public void setMybundle(ResourceBundle mybundle) {
        this.mybundle = mybundle;
    }

    public static void lineSeparator() {
        System.out.println(System.getProperty("line.separator"));
    }

    public String errorMessage() {
        return getMybundle().getString("error");
    }

}
