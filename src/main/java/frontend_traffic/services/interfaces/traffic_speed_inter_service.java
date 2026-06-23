package frontend_traffic.services.interfaces;

import java.util.*;

import org.springframework.data.domain.*;

import frontend_traffic.dto.traffic_chart_dto;
import frontend_traffic.dto.traffic_speed_dto;
import frontend_traffic.models.traffic_speed_entity;

public interface traffic_speed_inter_service {
    List<traffic_speed_entity> getAllTrafficSpeed();

    traffic_speed_entity getByIdTrafficSpeed(UUID id);

    Page<traffic_speed_entity> getByIdTrafficInfo(UUID id, Pageable page);

    traffic_speed_entity postTrafficSpeed(traffic_speed_dto request);

    List<traffic_chart_dto> getTrafficChart();
    // traffic_speed_entity updateTrafficSpeed(UUID id, traffic_speed_dto request);

    // traffic_speed_entity deleteTrafficSpeed(UUID id);

    // traffic_speed_entity deleteAllTrafficSpeed();
}
