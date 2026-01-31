package ru.yandex.practicum.sleeptracker.function;

import java.util.List;

public class MaxDurationFunction implements SleepAnalysisFunction {
    public SleepAnalysisResult<Long> analyze(List<SleepingSession> sessions) {
        return new SleepAnalysisResult<>(
                "Максимальная продолжительность сна (минуты)",
                sessions.stream().mapToLong(SleepingSession::getDurationMinutes).max().orElse(0)
        );
    }
}
