package frontend_traffic.services.implementation;

import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import frontend_traffic.dto.traffic_speed_dto;
import frontend_traffic.models.traffic_speed_entity;
import frontend_traffic.models.common_type_entity.Traffic_level;
import frontend_traffic.repository.traffic_speed_repository;
import frontend_traffic.services.interfaces.traffic_speed_inter_service;
import lombok.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class traffic_speed_service implements traffic_speed_inter_service {
    private final traffic_speed_repository trafficSpeedRepository;

    private static final Logger log = LoggerFactory.getLogger(traffic_speed_service.class);
    @Value("${tomtom.api.key}")
    private String key;

    private final RestClient restClient = RestClient.builder()
            .baseUrl("https://api.tomtom.com")
            .build();

    public traffic_speed_entity getTrafficSpeed(double lat, double lng) {
        try {

            traffic_speed_entity data = restClient.get()
                    .uri("/traffic/services/4/flowSegmentData/absolute/10/json" +
                            "?point={lat},{lng}&key={key}",
                            lat, lng, key)
                    .retrieve()
                    .body(traffic_speed_entity.class);
            traffic_speed_entity entity = new traffic_speed_entity();
            entity.setFrc(data.getFrc());
            entity.setCurrentSpeed(data.getCurrentSpeed());
            entity.setCurrentTravelTime(data.getCurrentTravelTime());
            entity.setFreeFlowSpeed(data.getFreeFlowSpeed());
            entity.setFreeFlowTravelTime(data.getFreeFlowTravelTime());
            entity.setConfidence(data.getConfidence());
            entity.setRoadClosure(data.getRoadClosure());
            entity.setTrafficLevel(handleRatio(data));
            return trafficSpeedRepository.save(entity);
        } catch (

        Exception e) {
            log.error("Cannot get traffic data for {}, {}", lat, lng, e);
            return null;
        }

    }

    public Traffic_level handleRatio(traffic_speed_entity request) {
        return handleRatio(request.getCurrentSpeed(), request.getFreeFlowSpeed());
    }

    public Traffic_level handleRatio(traffic_speed_dto request) {
        return handleRatio(request.getCurrentSpeed(), request.getFreeFlowSpeed());
    }

    private Traffic_level handleRatio(Double currentSpeed, Double freeFlowSpeed) {
        Double ratio = currentSpeed / freeFlowSpeed;

        if (freeFlowSpeed == null || freeFlowSpeed <= 0) {
            return Traffic_level.HIGH;
        }

        if (ratio >= 0.8) {
            return Traffic_level.LOW;
        }

        if (ratio >= 0.5) {
            return Traffic_level.MEDIUM;
        }

        return Traffic_level.HIGH;
    }

    // get all / Lấy tất cả dữ liệu
    @Override
    public Page<traffic_speed_entity> getAllTrafficSpeed(Pageable page) {
        Page<traffic_speed_entity> result = trafficSpeedRepository.findAll(page);
        return result;
    }

    // get by id / tìm id là trả về dữ liệu của id đó
    @Override
    public traffic_speed_entity getByIdTrafficSpeed(UUID id) {
        traffic_speed_entity result = trafficSpeedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Traffic Speed ID doesn't exit!"));
        return result;
    }

    // post / gửi dữ liệu thông tin trạng thái, mật độ lên
    @Override
    public traffic_speed_entity postTrafficSpeed(traffic_speed_dto request) {
        traffic_speed_entity entity = new traffic_speed_entity();
        entity.setFrc(request.getFrc());
        entity.setCurrentSpeed(request.getCurrentSpeed());
        entity.setCurrentTravelTime(request.getCurrentTravelTime());
        entity.setFreeFlowSpeed(request.getFreeFlowSpeed());
        entity.setFreeFlowTravelTime(request.getFreeFlowTravelTime());
        entity.setConfidence(request.getConfidence());
        entity.setRoadClosure(request.getRoadClosure());
        entity.setTrafficLevel(handleRatio(request));
        return trafficSpeedRepository.save(entity);
    }
}
