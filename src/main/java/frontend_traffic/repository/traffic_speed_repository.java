package frontend_traffic.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import frontend_traffic.models.traffic_speed_entity;
import java.util.List;


public interface traffic_speed_repository extends JpaRepository<traffic_speed_entity, UUID> {
    List<traffic_speed_entity> findByTrafficInfos(UUID trafficInfos);
}
