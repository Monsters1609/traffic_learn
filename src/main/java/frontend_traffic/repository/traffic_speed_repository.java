package frontend_traffic.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import frontend_traffic.models.traffic_speed_entity;

public interface traffic_speed_repository extends JpaRepository<traffic_speed_entity, UUID> {

}
