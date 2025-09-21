package lk.ijse.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
@AllArgsConstructor
@NoArgsConstructor
@Data
*/

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;
    private String reviewDate;
    private String reviewTime;
    private String reviewDescription;
    private String reviewRating;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "shopId" , referencedColumnName = "shopId" )
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "serviceId" , referencedColumnName = "serviceId" )
    private Services services;

    public Review() {
    }

    public Review(long reviewId, String reviewDate, String reviewTime, String reviewDescription, String reviewRating, User user, Shop shop, Services services) {
        this.reviewId = reviewId;
        this.reviewDate = reviewDate;
        this.reviewTime = reviewTime;
        this.reviewDescription = reviewDescription;
        this.reviewRating = reviewRating;
        this.user = user;
        this.shop = shop;
        this.services = services;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }
}
