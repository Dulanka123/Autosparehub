package lk.ijse.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long shopId;
    private String shopName;
    private String shopAddress;
    //private String shopLocation;
    private String shopContact;
    @Column(unique = true)
    private String shopEmail;
    private String shopOwner;
    private String shopOwnerContact;
    private String shopOwnerEmail;
    private String shopOwnerNIC;
    private Date shopOwnerDOB;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "shop")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "shop")
    private List<Item> items = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "shop")
    private List<Item> carts = new ArrayList<>();

    public Shop() {
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopContact() {
        return shopContact;
    }

    public void setShopContact(String shopContact) {
        this.shopContact = shopContact;
    }

    public String getShopEmail() {
        return shopEmail;
    }

    public void setShopEmail(String shopEmail) {
        this.shopEmail = shopEmail;
    }

    public String getShopOwner() {
        return shopOwner;
    }

    public void setShopOwner(String shopOwner) {
        this.shopOwner = shopOwner;
    }

    public String getShopOwnerContact() {
        return shopOwnerContact;
    }

    public void setShopOwnerContact(String shopOwnerContact) {
        this.shopOwnerContact = shopOwnerContact;
    }

    public String getShopOwnerEmail() {
        return shopOwnerEmail;
    }

    public void setShopOwnerEmail(String shopOwnerEmail) {
        this.shopOwnerEmail = shopOwnerEmail;
    }

    public String getShopOwnerNIC() {
        return shopOwnerNIC;
    }

    public void setShopOwnerNIC(String shopOwnerNIC) {
        this.shopOwnerNIC = shopOwnerNIC;
    }

    public Date getShopOwnerDOB() {
        return shopOwnerDOB;
    }

    public void setShopOwnerDOB(Date shopOwnerDOB) {
        this.shopOwnerDOB = shopOwnerDOB;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getCarts() {
        return carts;
    }

    public void setCarts(List<Item> carts) {
        this.carts = carts;
    }
}
