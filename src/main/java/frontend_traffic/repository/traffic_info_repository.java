package frontend_traffic.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import frontend_traffic.models.traffic_info_entity;

public interface traffic_info_repository extends JpaRepository<traffic_info_entity, UUID> {

}
