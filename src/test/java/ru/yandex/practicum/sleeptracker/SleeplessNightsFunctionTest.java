package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.SleepQuality;
import ru.yandex.practicum.sleeptracker.function.SleepingSession;
import ru.yandex.practicum.sleeptracker.function.SleeplessNightsFunction;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleeplessNightsFunctionTest {
    @Test
    void sleeplessNights_none() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 0),
                        LocalDateTime.of(2025, 10, 2, 6, 0),
                        SleepQuality.GOOD
                )
        );

        SleeplessNightsFunction f = new SleeplessNightsFunction();
        assertEquals(0L, f.analyze(sessions).getValue());
    }

    @Test
    void sleeplessNights_oneNight() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 7, 0),
                        LocalDateTime.of(2025, 10, 1, 9, 0),
                        SleepQuality.NORMAL
                )
        );

        SleeplessNightsFunction f = new SleeplessNightsFunction();
        assertEquals(1L, f.analyze(sessions).getValue());
    }

    @Test
    void sleeplessNights_crossingNight() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 2, 0),
                        LocalDateTime.of(2025, 10, 1, 7, 0),
                        SleepQuality.GOOD
                )
        );

        SleeplessNightsFunction f = new SleeplessNightsFunction();
        assertEquals(0L, f.analyze(sessions).getValue());
    }
}
