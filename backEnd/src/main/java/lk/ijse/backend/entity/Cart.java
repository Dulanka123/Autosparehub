package lk.ijse.backend.entity;

import jakarta.persistence.*;
import lk.ijse.backend.DTO.UserDTO;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;
    private long itemId;
    private String name;
    private String image;
    private int qty;
    private double price;


    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "shopId", referencedColumnName = "shopId")
    private Shop shop;

    public Cart() {
    }

    public Cart(long cartId, long itemId, String name, String image, int qty, double price, User user, Shop shop) {
        this.cartId = cartId;
        this.itemId = itemId;
        this.name = name;
        this.image = image;
        this.qty = qty;
        this.price = price;
        this.user = user;
        this.shop = shop;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
}
