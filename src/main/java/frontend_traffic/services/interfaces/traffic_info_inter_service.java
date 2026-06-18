package frontend_traffic.services.interfaces;

import java.util.*;

import org.springframework.data.domain.*;

import frontend_traffic.dto.traffic_info_dto;
import frontend_traffic.models.traffic_info_entity;

public interface traffic_info_inter_service {
    Page<traffic_info_entity> getAllTraffic(Pageable pageable);

    traffic_info_entity getByIdTraffic(UUID id);

    traffic_info_entity addTraffic(traffic_info_dto request);

    traffic_info_entity updateTraffic(UUID id, traffic_info_dto request);

    traffic_info_entity deleteByIdTraffic(UUID id);
}
