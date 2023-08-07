package com.polovyi.ivan.tutorials.dto.request;

import java.net.InetAddress;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorEventRequest {

    private String accountName;
    private UUID deviceId;
    private LocalDate eventDate;
    private UUID eventId;
    private Set<InetAddress> closestDevicesIp;
    private List<Integer> temperatures;
    private Map<String, String> tags;
    private Float latitude;
    private Float longitude;
    private Integer humidity;
    private Integer pressure;
    private LocalTime eventTime;

}
