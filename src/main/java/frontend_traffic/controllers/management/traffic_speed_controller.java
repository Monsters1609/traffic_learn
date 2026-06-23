package frontend_traffic.controllers.management;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import lombok.*;

import org.springframework.web.bind.annotation.*;

import frontend_traffic.dto.traffic_chart_dto;
import frontend_traffic.models.traffic_speed_entity;
import frontend_traffic.services.implementation.traffic_speed_service;

@Controller
@AllArgsConstructor
/*
 * nếu trong trường hợp ko khai báo annotation "@AllArgsConstructor" này thì
 * phải sử dụng hàm
 * traffic_speed_controller như dưới.
 */
public class traffic_speed_controller {
    private final traffic_speed_service trafficSpeedService;

    // public traffic_speed_controller(traffic_speed_service trafficSpeedService) {
    // this.trafficSpeedService = trafficSpeedService;
    // }
    @GetMapping("/get-all-traffic-speed")
    @ResponseBody
    public List<traffic_speed_entity> getAllTrafficSpeed(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return trafficSpeedService.getAllTrafficSpeed();
    }

    @GetMapping("/get-traffic-info/{id}")
    @ResponseBody
    public Page<traffic_speed_entity> getTrafficInfoId(@PathVariable UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return trafficSpeedService.getByIdTrafficInfo(id,
                PageRequest.of(page, size));
    }

    @GetMapping("/get-chart-traffic-speed")
    @ResponseBody
    public List<traffic_chart_dto> getChart() {
        return trafficSpeedService.getTrafficChart();
    }
}
