package lk.ijse.backend.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.client.RestTemplate;

public class RequestUtil {
    public static String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    public static String getLocationFromIP(String ip) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://ipapi.co/" + ip + "/city/";
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return "Unknown";
        }
    }
}
