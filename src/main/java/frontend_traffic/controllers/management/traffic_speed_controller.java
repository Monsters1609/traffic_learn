package frontend_traffic.controllers.management;

import org.springframework.stereotype.Controller;

import frontend_traffic.services.implementation.traffic_speed_service;
import lombok.*;

@Controller
@AllArgsConstructor
/*
 * nếu trong trường hợp ko khai báo annotation "@AllArgsConstructor" này thì
 * phải sử dụng hàm
 * traffic_speed_controller như dưới.
 */
public class traffic_speed_controller {
    // private final traffic_speed_service trafficSpeedService;

    // public traffic_speed_controller(traffic_speed_service trafficSpeedService) {
    // this.trafficSpeedService = trafficSpeedService;
    // }
}
