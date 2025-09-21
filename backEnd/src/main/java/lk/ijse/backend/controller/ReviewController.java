package lk.ijse.backend.controller;

import lk.ijse.backend.DTO.ResponseDTO;
import lk.ijse.backend.DTO.ReviewDTO;
import lk.ijse.backend.service.ReviewService;
import lk.ijse.backend.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
@CrossOrigin
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveReview(@RequestBody ReviewDTO reviewDTO) {
        try {
            int res = reviewService.saveReview(reviewDTO);
           switch (res){
               case VarList.Created -> {
                   return ResponseEntity.status(HttpStatus.CREATED)
                           .body(new ResponseDTO(VarList.Created, "Success", null));
               }
                case VarList.Not_Acceptable -> {
                     return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Review Already Exists", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
           }
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO(500, e.getMessage(), null));
        }
    }
}