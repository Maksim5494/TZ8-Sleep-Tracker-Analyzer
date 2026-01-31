package ru.yandex.practicum.sleeptracker.main;

import ru.yandex.practicum.sleeptracker.function.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class SleepTrackerApp {
    private List<SleepAnalysisFunction> functions = new ArrayList<>();

    public SleepTrackerApp() {

        functions.add(new TotalSessionsFunction());
        functions.add(new MinDurationFunction());
        functions.add(new MaxDurationFunction());
        functions.add(new AvgDurationFunction());
        functions.add(new BadSleepCountFunction());
        functions.add(new SleeplessNightsFunction());
        functions.add(new ChronotypeFunction());
    }

    public static void main(String[] args) throws IOException {



        if (args.length == 0) {
            System.out.println("Укажите путь к файлу с логом сна");
            return;
        }

        SleepTrackerApp app = new SleepTrackerApp();
        List<SleepingSession> sessions = app.readSleepLog(args[0]);

        app.functions.forEach(f -> {
            SleepAnalysisResult<?> result = f.analyze(sessions);
            System.out.println(result);
        });
    }

    public List<SleepingSession> readSleepLog(String filepath) throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            return reader.lines()
                    .filter(line -> !line.isBlank())
                    .map(line -> {
                        try {
                            String[] parts = line.split(";");
                            if (parts.length < 3) return null;

                            return new SleepingSession(
                                    LocalDateTime.parse(parts[0].trim(), formatter),
                                    LocalDateTime.parse(parts[1].trim(), formatter),
                                    SleepQuality.valueOf(parts[2].trim())
                            );
                        } catch (Exception e) {

                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
    }
}