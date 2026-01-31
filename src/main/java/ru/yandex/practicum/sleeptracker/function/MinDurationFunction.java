package ru.yandex.practicum.sleeptracker.function;

import java.util.List;

public class MinDurationFunction implements SleepAnalysisFunction {
    public SleepAnalysisResult<Long> analyze(List<SleepingSession> sessions) {
        return new SleepAnalysisResult<>(
                "Минимальная продолжительность сна (минуты)",
                sessions.stream().mapToLong(SleepingSession::getDurationMinutes).min().orElse(0)
        );
    }
}
