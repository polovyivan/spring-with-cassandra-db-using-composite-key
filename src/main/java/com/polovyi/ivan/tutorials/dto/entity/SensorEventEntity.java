package com.polovyi.ivan.tutorials.dto.entity;

import com.polovyi.ivan.tutorials.dto.request.SensorEventRequest;
import java.net.InetAddress;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("sensor_events")
public class SensorEventEntity {

    @PrimaryKey
    private SensorEventEntityCompositeKey key;

    @Column(value = "closest_devices_ip")
    private Set<InetAddress> closestDevicesIp;

    @Column(value = "temperatures")
    private List<Integer> temperatures;

    @Column(value = "tags")
    private Map<String, String> tags;

    @Column(value = "latitude")
    private Float latitude;

    @Column(value = "longitude")
    private Float longitude;

    @Column(value = "humidity")
    private Integer humidity;

    @Column(value = "pressure")
    private Integer pressure;

    @Column(value = "event_time")
    private LocalTime eventTime;

    public static SensorEventEntity valueOf(SensorEventRequest request) {
        SensorEventEntityCompositeKey sensorEventEntityCompositeKey = SensorEventEntityCompositeKey.builder()
                .accountName(request.getAccountName())
                .deviceId(request.getDeviceId())
                .eventId(request.getEventId())
                .eventDate(request.getEventDate())
                .build();

        return builder()
                .key(sensorEventEntityCompositeKey)
                .closestDevicesIp(request.getClosestDevicesIp())
                .temperatures(request.getTemperatures())
                .tags(request.getTags())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .humidity(request.getHumidity())
                .pressure(request.getPressure())
                .eventTime(request.getEventTime())
                .build();
    }
}
