package frontend_traffic.services.implementation;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class traffic_api_tomtom_service {

    private final RestClient restClient;

    public traffic_api_tomtom_service() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.tomtom.com")
                .build();
    }

    public String getTraffic(double lat, double lng) {

        String key = "dRrxO1yXAGX2V9Ee1RSxqG6qT0Gb64j3";

        String response = restClient.get()
                .uri("/traffic/services/4/flowSegmentData/absolute/10/json" +
                        "?point={lat},{lng}&key={key}",
                        lat, lng, key)
                .retrieve()
                .body(String.class);

        System.out.println("Traffic Response: " + response);
        return response;
    }
}
