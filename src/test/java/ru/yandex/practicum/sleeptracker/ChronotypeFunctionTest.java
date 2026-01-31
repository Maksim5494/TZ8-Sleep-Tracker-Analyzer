package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.ChronotypeFunction;
import ru.yandex.practicum.sleeptracker.function.SleepQuality;
import ru.yandex.practicum.sleeptracker.function.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChronotypeFunctionTest {

    @Test
    void chronotype_owl() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 30),
                        LocalDateTime.of(2025, 10, 2, 9, 30),
                        SleepQuality.GOOD
                )
        );

        ChronotypeFunction f = new ChronotypeFunction();
        assertEquals("Сова", f.analyze(sessions).getValue());
    }

    @Test
    void chronotype_lark() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 21, 30),
                        LocalDateTime.of(2025, 10, 2, 6, 30),
                        SleepQuality.GOOD
                )
        );

        ChronotypeFunction f = new ChronotypeFunction();
        assertEquals("Жаворонок", f.analyze(sessions).getValue());
    }

    @Test
    void chronotype_pigeon_onTie() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 30),
                        LocalDateTime.of(2025, 10, 2, 9, 30),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 21, 30),
                        LocalDateTime.of(2025, 10, 3, 6, 30),
                        SleepQuality.GOOD
                )
        );

        ChronotypeFunction f = new ChronotypeFunction();
        assertEquals("Голубь", f.analyze(sessions).getValue());
    }
}
