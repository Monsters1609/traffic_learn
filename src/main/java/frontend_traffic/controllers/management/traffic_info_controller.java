package frontend_traffic.controllers.management;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import frontend_traffic.dto.traffic_info_dto;
import frontend_traffic.helpers.errors.success_handle;
import frontend_traffic.models.traffic_info_entity;
import frontend_traffic.services.implementation.traffic_info_service;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class traffic_info_controller {
    private final traffic_info_service trafficInfoService;

    @GetMapping("/traffic")
    public String pageTraffic(Model model, HttpServletRequest request, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        String result = request.getRequestURI();
        String pathBreadcrumb = result.substring(1); // xóa bỏ ký tự đầu tiên
        model.addAttribute("breadcrumb", pathBreadcrumb);
        model.addAttribute("traffic_info", new traffic_info_entity());
        model.addAttribute("responseAllTraffic", trafficInfoService.getAllTraffic(PageRequest.of(page, size)));
        return "modules/frontend/pages/traffic";
    }

    @PostMapping("/add-traffic")
    public String postAddTrafficInfo(
            @ModelAttribute traffic_info_dto request) {
        trafficInfoService.addTraffic(request);
        // return ResponseEntity.ok(new success_handle<>(HttpStatus.NO_CONTENT, "Add
        // traffic info successfully!"));
        return "redirect:/traffic";
    }

}
