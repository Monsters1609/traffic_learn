package frontend_traffic.repository;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import frontend_traffic.models.traffic_speed_entity;

@Repository
public interface traffic_speed_repository extends JpaRepository<traffic_speed_entity, UUID> {
    Page<traffic_speed_entity> findByTrafficInfos_TrafficId(UUID trafficId, Pageable pageable);
}
