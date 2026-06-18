package frontend_traffic.services.implementation;

import java.util.*;

import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import frontend_traffic.dto.traffic_info_dto;
import frontend_traffic.models.traffic_info_entity;
import frontend_traffic.repository.traffic_info_repository;
import frontend_traffic.services.interfaces.traffic_info_inter_service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class traffic_info_service implements traffic_info_inter_service {
    private final traffic_info_repository trafficInfoRepository;
    private final traffic_speed_service trafficSpeedService;

    @Override
    public Page<traffic_info_entity> getAllTraffic(Pageable pageable) {
        Page<traffic_info_entity> result = trafficInfoRepository.findAll(pageable);
        return result;
    }

    @Scheduled(fixedRate = 5000) // 5 phút
    public void syncTraffic() {
        List<traffic_info_entity> infos = trafficInfoRepository.findAll();
        for (traffic_info_entity info : infos) {
            trafficSpeedService.getTrafficSpeed(info.getLat(), info.getLng());
        }
    }

    @Override
    public traffic_info_entity getByIdTraffic(UUID id) {
        traffic_info_entity entity = trafficInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Traffic info does not exit!"));
        return entity;
    }

    @Override
    public traffic_info_entity addTraffic(traffic_info_dto request) {
        traffic_info_entity entity = new traffic_info_entity();
        entity.setLat(request.getLat());
        entity.setLng(request.getLng());
        entity.setAddress(request.getAddress());
        return trafficInfoRepository.save(entity);
    }

    @Override
    public traffic_info_entity updateTraffic(UUID id, traffic_info_dto request) {
        traffic_info_entity entity = getByIdTraffic(id);
        entity.setLat(request.getLat());
        entity.setLng(request.getLng());
        entity.setAddress(request.getAddress());
        return trafficInfoRepository.save(entity);
    }

    @Override
    public traffic_info_entity deleteByIdTraffic(UUID id) {
        traffic_info_entity entity = getByIdTraffic(id);
        trafficInfoRepository.delete(entity);
        return entity;
    }
}
