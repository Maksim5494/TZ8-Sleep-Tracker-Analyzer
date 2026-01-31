package ru.yandex.practicum.sleeptracker.function;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SleeplessNightsFunction implements SleepAnalysisFunction {
    public SleepAnalysisResult<Long> analyze(List<SleepingSession> sessions) {
        if (sessions == null || sessions.isEmpty()) {
            return new SleepAnalysisResult<>("Бессонные ночи", 0L);
        }

        LocalDate minDate = sessions.stream().map(s -> s.getStart().toLocalDate()).min(LocalDate::compareTo).get();
        LocalDate maxDate = sessions.stream().map(s -> s.getStart().toLocalDate()).max(LocalDate::compareTo).get();
        Set<LocalDate> datesWithNightSleep = sessions.stream()
                .filter(s -> s.getDurationMinutes() >= 180) // Сон более 3 часов
                .filter(s -> s.getStart().getHour() >= 20 || s.getStart().getHour() <= 4) // Начало ночью
                .map(s -> s.getStart().toLocalDate())
                .collect(Collectors.toSet());

        long sleeplessCount = 0;
        for (LocalDate date = minDate; !date.isAfter(maxDate); date = date.plusDays(1)) {
            if (!datesWithNightSleep.contains(date)) {
                sleeplessCount++;
            }
        }

        return new SleepAnalysisResult<>("Бессонные ночи", sleeplessCount);
    }
}