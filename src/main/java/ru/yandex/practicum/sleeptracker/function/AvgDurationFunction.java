package ru.yandex.practicum.sleeptracker.function;

import java.util.List;

public class AvgDurationFunction implements SleepAnalysisFunction {
    public SleepAnalysisResult<Double> analyze(List<SleepingSession> sessions) {
        return new SleepAnalysisResult<>(
                "Средняя продолжительность сна (минуты)",
                sessions.stream().mapToLong(SleepingSession::getDurationMinutes).average().orElse(0.0)
        );
    }
}
