package com.maze.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Greetings {

    private static final LocalTime MORNING = LocalTime.of(0, 0, 0);
    private static final LocalTime AFTER_NOON = LocalTime.of(12, 0, 0);
    private static final LocalTime EVENING = LocalTime.of(16, 0, 0);
    private static final LocalTime NIGHT = LocalTime.of(21, 0, 0);
    private final DateTimeFormatter formatTime = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(new Locale("en", "nz"));
    private final DateTimeFormatter formatDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(new Locale("en", "nz"));

    private LocalTime now;

    public Greetings(LocalTime now) {
        this.now = now;
    }

    public String printTimeOfDay() {
        if (between(MORNING, AFTER_NOON)) {
            return Messages.getInstance().getMybundle().getString("good_morning");
        } else if (between(AFTER_NOON, EVENING)) {
            return Messages.getInstance().getMybundle().getString("good_afternoon");
        } else if (between(EVENING, NIGHT)) {
            return Messages.getInstance().getMybundle().getString("good_evening");
        } else {
            return Messages.getInstance().getMybundle().getString("good_night");
        }
    }

    public String timeLocale() {
        ZoneId zone1 = ZoneId.of("NZ");

        LocalTime now1 = LocalTime.now(zone1);

        return now1.format(formatTime);
    }

    public String dateLocale() {
        LocalDate dateTime = LocalDate.now();
        return dateTime.format(formatDate);
    }

    private boolean between(LocalTime start, LocalTime end) {
        return (!now.isBefore(start)) && now.isBefore(end);
    }

}
