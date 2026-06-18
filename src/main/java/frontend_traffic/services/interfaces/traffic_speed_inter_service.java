package frontend_traffic.services.interfaces;

import java.util.*;

import org.springframework.data.domain.*;

import frontend_traffic.dto.traffic_speed_dto;
import frontend_traffic.models.traffic_speed_entity;

public interface traffic_speed_inter_service {
    Page<traffic_speed_entity> getAllTrafficSpeed(Pageable page);

    traffic_speed_entity getByIdTrafficSpeed(UUID id);

    traffic_speed_entity postTrafficSpeed(traffic_speed_dto request);

    // traffic_speed_entity updateTrafficSpeed(UUID id, traffic_speed_dto request);

    // traffic_speed_entity deleteTrafficSpeed(UUID id);

    // traffic_speed_entity deleteAllTrafficSpeed();
}
