package com.musala.drone.timer;

import com.musala.drone.model.Drone;
import com.musala.drone.repositories.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.DecimalFormat;
import java.util.List;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ScheduledCheckBattery {

    @Autowired
    private DroneRepository droneRepository;

    static final Logger log = LoggerFactory.getLogger(ScheduledCheckBattery.class);
    @Scheduled(fixedRate = 6000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {

        List<Drone> drones = droneRepository.findAll();

        DecimalFormat format = new DecimalFormat("##.#%");
        drones.stream().forEach(drone->{
            log.info("serial number : "+drone.getSerialNumber()+" with battery capacity : "+format.format(drone.getBatteryCapacity()));
        });
        Thread.sleep(6000);

    }

}
