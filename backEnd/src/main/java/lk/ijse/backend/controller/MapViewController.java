package lk.ijse.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/map")
@CrossOrigin
public class MapViewController {
    @GetMapping("/map")
    public String viewMap() {
        return "map";
    }
}
