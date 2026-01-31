package ru.yandex.practicum.sleeptracker.function;

import java.util.List;

@FunctionalInterface
public interface SleepAnalysisFunction {
    SleepAnalysisResult<?> analyze(List<SleepingSession> sessions);
}
