CREATE KEYSPACE iot WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':1};


CREATE COLUMNFAMILY iot.sensor_events (
                    account_name text,
                    device_id UUID,
                    event_date date,
                    event_id UUID,
                    closest_devices_ip set<inet>,
                    temperatures list<int>,
                    tags map<text,text>,
                    latitude float,
                    longitude float,
                    humidity  int,
                    event_time time,
                    pressure int,
                    PRIMARY KEY((account_name,device_id), event_date, event_id)
);