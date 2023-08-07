package com.polovyi.ivan.tutorials.service;

import com.polovyi.ivan.tutorials.dto.entity.SensorEventEntity;
import com.polovyi.ivan.tutorials.dto.entity.SensorEventEntityCompositeKey;
import com.polovyi.ivan.tutorials.dto.request.SensorEventRequest;
import com.polovyi.ivan.tutorials.dto.response.SensorEventResponse;
import com.polovyi.ivan.tutorials.repository.MapIdSensorEventsRepository;
import com.polovyi.ivan.tutorials.repository.SensorEventsRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.cassandra.core.mapping.BasicMapId;
import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorEventsService {

    private final SensorEventsRepository sensorEventsRepository;
    private final MapIdSensorEventsRepository mapIdSensorEventsRepository;

    public void createEvent(SensorEventRequest sensorEventRequest) {
        log.info("Saving request {}", sensorEventRequest);
        sensorEventsRepository.save(SensorEventEntity.valueOf(sensorEventRequest));
    }

    public List<SensorEventResponse> getAll() {
        log.info("Retreating all events...");
        return sensorEventsRepository.findAll()
                .stream()
                .map(SensorEventResponse::valueOf)
                .collect(Collectors.toList());
    }

    public List<SensorEventResponse> getWithFilters(String accountName, UUID deviceId, UUID eventId, LocalDate eventDate) {

        return sensorEventsRepository.findByKeyAccountNameAndKeyDeviceId(
                        accountName, deviceId, eventDate, eventId).stream()
                .map(SensorEventResponse::valueOf)
                .collect(Collectors.toList());
    }

    public List<SensorEventResponse> getEventsByDeviceId(String accountName, UUID deviceId) {
        return mapIdSensorEventsRepository.findByKeyAccountNameAndKeyDeviceId(
                        accountName, deviceId).stream()
                .map(SensorEventResponse::valueOf)
                .collect(Collectors.toList());
    }

    public SensorEventResponse getEventsByDeviceId(String accountName, UUID deviceId, LocalDate eventDate, UUID eventId) {
        MapId mapId = BasicMapId.id()
                .with("accountName", accountName)
                .with("deviceId", deviceId)
                .with("eventDate", eventDate)
                .with("eventId", eventId);
        System.out.println("mapId = " + mapId);
        return mapIdSensorEventsRepository.findById(mapId).map(SensorEventResponse::valueOf).orElse(null);
    }

    public void deleteEventsByDeviceId(String accountName, UUID deviceId, LocalDate eventDate, UUID eventId) {
        MapId mapId = BasicMapId.id()
                .with("accountName", accountName)
                .with("deviceId", deviceId)
                .with("eventDate", eventDate)
                .with("eventId", eventId);
        System.out.println("mapId = " + mapId);
         mapIdSensorEventsRepository.findById(mapId).ifPresent(e->mapIdSensorEventsRepository.deleteById(mapId));
    }
}
