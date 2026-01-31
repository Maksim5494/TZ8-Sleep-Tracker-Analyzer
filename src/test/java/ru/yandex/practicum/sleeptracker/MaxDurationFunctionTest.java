package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.MaxDurationFunction;
import ru.yandex.practicum.sleeptracker.function.SleepQuality;
import ru.yandex.practicum.sleeptracker.function.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxDurationFunctionTest {

    @Test
    void maxDuration_basicCase() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 0),
                        LocalDateTime.of(2025, 10, 2, 7, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 14, 0),
                        LocalDateTime.of(2025, 10, 2, 15, 30),
                        SleepQuality.NORMAL
                )
        );

        MaxDurationFunction f = new MaxDurationFunction();
        assertEquals(480, f.analyze(sessions).getValue());
    }

    @Test
    void maxDuration_allSameLength() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 22, 0),
                        LocalDateTime.of(2025, 10, 2, 6, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 23, 0),
                        LocalDateTime.of(2025, 10, 3, 7, 0),
                        SleepQuality.NORMAL
                )
        );

        MaxDurationFunction f = new MaxDurationFunction();
        assertEquals(480, f.analyze(sessions).getValue());
    }
}
