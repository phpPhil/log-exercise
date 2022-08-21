package com.digio.logexercise.service;

import com.digio.logexercise.LogExerciseApplicationTests;
import com.digio.logexercise.model.HttpRequestLogSummary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HttpRequestLogServiceTest extends LogExerciseApplicationTests {

    @Test
    public void testRead() {
        HttpRequestLogService logService = new HttpRequestLogService();
        HttpRequestLogSummary logSummary = logService.read("test-data.log");

        Assertions.assertEquals(3, logSummary.getTopActiveIps().size());
        Assertions.assertEquals("168.41.191.40", logSummary.getTopActiveIps().get(0));

        Assertions.assertEquals(3, logSummary.getTopVisitedUrls().size());
        Assertions.assertEquals("/faq/", logSummary.getTopVisitedUrls().get(0));

        Assertions.assertEquals(11, logSummary.getAmountUniqueIp());
    }

    @Test
    public void testReadExceptionNotFound() {
        HttpRequestLogService logService = new HttpRequestLogService();
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            logService.read("missing-test-data-file.log");
        });
        String expectedMessage = "Log file does not exist.";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testReadExceptionInvalid() {
        HttpRequestLogService logService = new HttpRequestLogService();
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            logService.read("test-data-invalid.log");
        });
        String expectedMessage = "Invalid log entry.";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
