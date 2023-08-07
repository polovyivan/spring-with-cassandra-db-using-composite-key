package com.polovyi.ivan.tutorials.repository;

import com.polovyi.ivan.tutorials.dto.entity.SensorEventEntity;
import com.polovyi.ivan.tutorials.dto.entity.SensorEventEntityCompositeKey;
import java.util.List;
import java.util.UUID;
import org.springframework.data.cassandra.repository.MapIdCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapIdSensorEventsRepository extends MapIdCassandraRepository<SensorEventEntity> {
    List<SensorEventEntity> findByKeyAccountNameAndKeyDeviceId(String accountName, UUID deviceId);
}