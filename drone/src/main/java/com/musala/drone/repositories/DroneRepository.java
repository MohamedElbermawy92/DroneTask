package com.musala.drone.repositories;

import com.musala.drone.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface DroneRepository extends JpaRepository<Drone,String> {


   List<Drone> findAllByState(String state);
   Optional<Drone> findBySerialNumber(String serialNumberl);

   @Modifying
   @Query(value = "update Drone d set d.state = :state where  d.serialNumber= :serialno")
   void setUpdateState (@Param("state") String status, @Param("serialno") String serialNo);


}
