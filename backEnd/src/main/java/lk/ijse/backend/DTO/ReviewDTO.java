package lk.ijse.backend.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ReviewDTO {
    private long reviewId;
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "Invalid date")
    private String reviewDate;
    @Pattern(regexp = "^[0-9]{2}:[0-9]{2}:[0-9]{2}$", message = "Invalid time")
    private String reviewTime;
    @NotBlank(message = "Review description is required")
    private String reviewDescription;
    @Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "Invalid rating")
    private String reviewRating;
    private UserDTO userDTO;
    private ShopDTO shopDTO;
    private ServiceDTO serviceDTO;

    public ReviewDTO() {
    }

    public ReviewDTO(long reviewId, String reviewDate, String reviewTime, String reviewDescription, String reviewRating, UserDTO userDTO, ShopDTO shopDTO, ServiceDTO serviceDTO) {
        this.reviewId = reviewId;
        this.reviewDate = reviewDate;
        this.reviewTime = reviewTime;
        this.reviewDescription = reviewDescription;
        this.reviewRating = reviewRating;
        this.userDTO = userDTO;
        this.shopDTO = shopDTO;
        this.serviceDTO = serviceDTO;
    }

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public String getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(String reviewRating) {
        this.reviewRating = reviewRating;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public ShopDTO getShopDTO() {
        return shopDTO;
    }

    public void setShopDTO(ShopDTO shopDTO) {
        this.shopDTO = shopDTO;
    }

    public ServiceDTO getServiceDTO() {
        return serviceDTO;
    }

    public void setServiceDTO(ServiceDTO serviceDTO) {
        this.serviceDTO = serviceDTO;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "reviewId=" + reviewId +
                ", reviewDate='" + reviewDate + '\'' +
                ", reviewTime='" + reviewTime + '\'' +
                ", reviewDescription='" + reviewDescription + '\'' +
                ", reviewRating='" + reviewRating + '\'' +
                ", userDTO=" + userDTO +
                ", shopDTO=" + shopDTO +
                ", serviceDTO=" + serviceDTO +
                '}';
    }
}
