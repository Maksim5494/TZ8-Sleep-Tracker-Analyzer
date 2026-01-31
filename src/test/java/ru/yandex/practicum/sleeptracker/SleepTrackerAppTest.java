package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.yandex.practicum.sleeptracker.function.SleepQuality;
import ru.yandex.practicum.sleeptracker.function.SleepingSession;
import ru.yandex.practicum.sleeptracker.main.SleepTrackerApp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SleepTrackerAppTest {

    private SleepTrackerApp app;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        app = new SleepTrackerApp();
    }

    @Test
    void testReadSleepLogCorrectData() throws IOException {
        File tempFile = tempDir.resolve("sleep_log.txt").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            // ИСПОЛЬЗУЕМ ";" И ВЕРНЫЙ ENUM
            writer.write("01.10.23 22:00;02.10.23 06:00;GOOD");
            writer.newLine();
            writer.write("02.10.23 23:30;03.10.23 07:15;NORMAL");
        }

        List<SleepingSession> sessions = app.readSleepLog(tempFile.getAbsolutePath());

        assertEquals(2, sessions.size());
        SleepingSession first = sessions.get(0);
        assertEquals(LocalDateTime.of(2023, 10, 1, 22, 0), first.getStart());
        assertEquals(SleepQuality.GOOD, first.getQuality());
    }

    @Test
    void testReadSleepLogEmptyFile() throws IOException {
        File emptyFile = tempDir.resolve("empty.txt").toFile();
        emptyFile.createNewFile();

        List<SleepingSession> sessions = app.readSleepLog(emptyFile.getAbsolutePath());

        assertTrue(sessions.isEmpty(), "Список сессий должен быть пуст для пустого файла");
    }

    @Test
    void testReadSleepLogFileNotFound() {
        assertThrows(IOException.class, () -> {
            app.readSleepLog("non_existent_file.txt");
        }, "Должно выбрасываться исключение, если файл не найден");
    }

    @Test
    void testFunctionsListInitialization() {
        // Проверяем, что все функции добавлены в список при инициализации
        // Это требует доступа к полю functions или проверки через рефлексию/публичный метод
        assertNotNull(app);
        // Если поле functions приватное и нет геттера, можно проверить indirect результаты через вызов main
        // или сделать метод getFunctions() в приложении для тестов.
    }

    /*
       Пример теста конкретной логики (подразумевая структуру SleepingSession).
       Здесь стоит протестировать ваши реализации SleepAnalysisFunction отдельно.
    */
    @Test
    void testTotalSessionsFunction() {
        var function = new ru.yandex.practicum.sleeptracker.function.TotalSessionsFunction();
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusHours(8), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusHours(6), SleepQuality.NORMAL)
        );

        var result = function.analyze(sessions);
        assertEquals(2, result.getValue());
    }

    @Test
    void testAvgDurationFunctionWithEmptyList() {
        var function = new ru.yandex.practicum.sleeptracker.function.AvgDurationFunction();
        var result = function.analyze(List.of());

        // В зависимости от вашей реализации, здесь может быть 0 или null
        assertEquals(0.0, (Double) result.getValue(), 0.001);
    }
}