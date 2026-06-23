package frontend_traffic.services.implementation;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import frontend_traffic.dto.traffic_chart_dto;
import frontend_traffic.dto.traffic_speed_dto;
import frontend_traffic.models.traffic_speed_entity;
import frontend_traffic.models.common_type_entity.Traffic_level;
import frontend_traffic.models.traffic_info_entity;
import frontend_traffic.repository.traffic_speed_repository;
import frontend_traffic.services.interfaces.traffic_speed_inter_service;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class traffic_speed_service implements traffic_speed_inter_service {
    private final traffic_speed_repository trafficSpeedRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${tomtom.api.key}")
    private String key;

    private final RestClient restClient = RestClient.builder()
            .baseUrl("https://api.tomtom.com")
            .build();

    public traffic_speed_dto getTraffic(double lat, double lng) {
        try {
            String response = restClient.get()
                    .uri("/traffic/services/4/flowSegmentData/absolute/10/json" +
                            "?point={lat},{lng}&key={key}",
                            lat, lng, key)
                    .retrieve()
                    .body(String.class);

            if (response == null || response.isBlank()) {
                log.warn("TomTom response is empty");
                return null;
            }

            JsonNode root = objectMapper.readTree(response);

            JsonNode flow = root.get("flowSegmentData");

            if (flow == null || flow.isNull()) {
                log.warn("No flowSegmentData in TomTom response: {}", response);
                return null;
            }

            traffic_speed_dto data = objectMapper.treeToValue(flow,
                    traffic_speed_dto.class);
            return data;

        } catch (Exception e) {
            log.error("Cannot call TomTom API for lat={}, lng={}", lat, lng, e);
            return null;
        }
    }

    public traffic_speed_entity getTrafficSpeed(traffic_info_entity trafficInfo) {
        try {
            Double lat = trafficInfo.getLat();
            Double lng = trafficInfo.getLng();
            traffic_speed_dto data = getTraffic(lat, lng);

            if (data == null) {
                log.warn("No TomTom traffic data for traffic_id={}, lat={}, lng={}",
                        trafficInfo.getTrafficId(), lat, lng);
                return null;
            }

            traffic_speed_entity entity = new traffic_speed_entity();

            entity.setTrafficInfos(trafficInfo);
            entity.setFrc(data.getFrc());
            entity.setCurrentSpeed(data.getCurrentSpeed());
            entity.setCurrentTravelTime(data.getCurrentTravelTime());
            entity.setFreeFlowSpeed(data.getFreeFlowSpeed());
            entity.setFreeFlowTravelTime(data.getFreeFlowTravelTime());
            entity.setConfidence(data.getConfidence());
            entity.setRoadClosure(data.getRoadClosure());
            entity.setTrafficLevel(handleRatio(data.getCurrentSpeed(), data.getFreeFlowSpeed()));

            return trafficSpeedRepository.save(entity);

        } catch (Exception e) {
            log.error("Cannot get traffic data for traffic_id={}", trafficInfo.getTrafficId(), e);
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
    public List<traffic_speed_entity> getAllTrafficSpeed() {
        return trafficSpeedRepository.findAll();
    }

    @Override
    public List<traffic_chart_dto> getTrafficChart() {
        Map<String, List<traffic_speed_entity>> grouped = trafficSpeedRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        item -> item.getTrafficInfos().getAddress()));

        return grouped.entrySet()
                .stream()
                .map(entry -> new traffic_chart_dto(
                        entry.getKey(),
                        entry.getValue()
                                .stream()
                                .map(item -> new traffic_chart_dto.TrafficPoint(
                                        item.getCurrentSpeed(),
                                        item.getCreatedAt()))
                                .toList()))
                .toList();
    }

    // lấy dữ liệu theo id cua traffic_info
    @Override
    public Page<traffic_speed_entity> getByIdTrafficInfo(UUID id, Pageable page) {
        Page<traffic_speed_entity> result = trafficSpeedRepository.findByTrafficInfos_TrafficId(id, page);
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
