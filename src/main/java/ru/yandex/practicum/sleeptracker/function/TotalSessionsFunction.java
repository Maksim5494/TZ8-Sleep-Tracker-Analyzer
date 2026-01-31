package ru.yandex.practicum.sleeptracker.function;

import java.util.List;

public class TotalSessionsFunction implements SleepAnalysisFunction {
    public SleepAnalysisResult<Integer> analyze(List<SleepingSession> sessions) {
        return new SleepAnalysisResult<>("Общее количество сессий сна", sessions.size());
    }
}
