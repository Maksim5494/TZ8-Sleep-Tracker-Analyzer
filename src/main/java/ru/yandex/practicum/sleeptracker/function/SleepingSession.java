package ru.yandex.practicum.sleeptracker.function;

import java.time.LocalDateTime;

public class SleepingSession {
    private LocalDateTime start;
    private LocalDateTime end;
    private SleepQuality quality;

    public SleepingSession(LocalDateTime start, LocalDateTime end, SleepQuality quality) {
        this.start = start;
        this.end = end;
        this.quality = quality;
    }

    public LocalDateTime getStart() {
        return start;
    }
    public LocalDateTime getEnd() {
        return end;
    }

    public SleepQuality getQuality() {
        return quality;
    }

    public long getDurationMinutes() {
        return java.time.Duration.between(start, end).toMinutes();
    }
}
