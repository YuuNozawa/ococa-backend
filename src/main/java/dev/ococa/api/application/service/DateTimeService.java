package dev.ococa.api.application.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

@Service
public class DateTimeService {
    public OffsetDateTime convertToOffsetDateTime(LocalDateTime localDateTime, String timeZone) {
        ZoneId zoneId = ZoneId.of(timeZone);
        return localDateTime.atZone(zoneId).toOffsetDateTime();
    }
}
