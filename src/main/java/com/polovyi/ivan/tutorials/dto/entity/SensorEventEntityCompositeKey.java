package com.polovyi.ivan.tutorials.dto.entity;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyClass
public class SensorEventEntityCompositeKey {

    @PrimaryKeyColumn(name = "account_name", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String accountName;

    @PrimaryKeyColumn(name = "device_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private UUID deviceId;

    @PrimaryKeyColumn(name = "event_date", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private LocalDate eventDate;

    @PrimaryKeyColumn(name = "event_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private UUID eventId;

}
