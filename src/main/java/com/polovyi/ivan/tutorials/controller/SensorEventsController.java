package com.polovyi.ivan.tutorials.controller;

import com.polovyi.ivan.tutorials.dto.request.SensorEventRequest;
import com.polovyi.ivan.tutorials.dto.response.SensorEventResponse;
import com.polovyi.ivan.tutorials.service.SensorEventsService;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/sensor-events")
public class SensorEventsController {

    private final SensorEventsService sensorEventsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@RequestBody SensorEventRequest event) {
        sensorEventsService.createEvent(event);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SensorEventResponse> getAll() {
        return sensorEventsService.getAll();
    }

    @GetMapping("/with-filters")
    @ResponseStatus(HttpStatus.OK)
    public List<SensorEventResponse> getEventsWithFilters(@RequestParam String accountName,
            @RequestParam UUID deviceId, @RequestParam UUID eventId, @RequestParam LocalDate eventDate) {
        return sensorEventsService.getWithFilters(accountName, deviceId,
                eventId, eventDate);
    }

    @GetMapping("/{accountName}/device/{deviceId}")
    @ResponseStatus(HttpStatus.OK)
    public List<SensorEventResponse> getEventsByDeviceId(@PathVariable String accountName,
            @PathVariable UUID deviceId) {
        return sensorEventsService.getEventsByDeviceId(accountName, deviceId);
    }

    @GetMapping("/{accountName}/device/{deviceId}/eventDate/{eventDate}/event/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public SensorEventResponse getEventById(@PathVariable String accountName,
            @PathVariable UUID deviceId, @PathVariable LocalDate eventDate, @PathVariable UUID eventId) {
        return sensorEventsService.getEventsByDeviceId(accountName, deviceId,
                eventDate, eventId);
    }

    @DeleteMapping("/{accountName}/device/{deviceId}/eventDate/{eventDate}/event/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEventById(@PathVariable String accountName,
            @PathVariable UUID deviceId, @PathVariable LocalDate eventDate, @PathVariable UUID eventId) {
        sensorEventsService.deleteEventsByDeviceId(accountName, deviceId,
                eventDate, eventId);
    }

}
