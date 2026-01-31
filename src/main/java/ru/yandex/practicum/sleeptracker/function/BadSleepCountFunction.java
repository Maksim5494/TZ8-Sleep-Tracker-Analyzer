package ru.yandex.practicum.sleeptracker.function;

import java.util.List;

public class BadSleepCountFunction implements SleepAnalysisFunction {
    public SleepAnalysisResult<Long> analyze(List<SleepingSession> sessions) {
        return new SleepAnalysisResult<>(
                "Количество сессий с плохим сном",
                sessions.stream().filter(s -> s.getQuality() == SleepQuality.BAD).count()
        );
    }
}
