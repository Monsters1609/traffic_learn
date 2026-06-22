package frontend_traffic.controllers.management;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import lombok.*;

import org.springframework.web.bind.annotation.*;

import frontend_traffic.models.traffic_info_entity;
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
    // @GetMapping("/get-all-traffic-speed")
    // public String getAllTrafficSpeed(Model model, @RequestParam(defaultValue =
    // "0") int page,
    // @RequestParam(defaultValue = "5") int size) {
    // model.addAttribute("get_all_traffic_speed",
    // trafficSpeedService.getAllTrafficSpeed(PageRequest.of(page, size)));
    // return "modules/frontend/pages/traffic";
    // }

    @GetMapping("/get-traffic-info/{id}")
    @ResponseBody
    public Page<traffic_speed_entity> getTrafficInfoId(@PathVariable UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return trafficSpeedService.getByIdTrafficInfo(id,
                PageRequest.of(page, size));
    }
}
