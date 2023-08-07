package com.polovyi.ivan.tutorials.repository;

import com.polovyi.ivan.tutorials.dto.entity.SensorEventEntity;
import com.polovyi.ivan.tutorials.dto.entity.SensorEventEntityCompositeKey;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorEventsRepository extends CassandraRepository<SensorEventEntity, SensorEventEntityCompositeKey> {

    @Query("""
            SELECT * FROM sensor_events WHERE 
            account_name=:#{#accountName} AND 
            device_id=:#{#deviceId} AND
            event_date = :#{#eventDate} AND 
            event_id = :#{#eventId} 
            """)
    List<SensorEventEntity> findByKeyAccountNameAndKeyDeviceId(String accountName, UUID deviceId,  LocalDate eventDate, UUID eventId);
}
