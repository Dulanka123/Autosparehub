package lk.ijse.backend.DTO;

import java.util.UUID;

public class CartDTO {
    private long cartId;
    private UUID userId;
    private long itemId;
    private String name;
    private String image;
    private int qty;
    private double price;
    private long shopId;

    public CartDTO() {
    }

    public CartDTO(long cartId, UUID userId, long itemId, String name, String image, int qty, double price, long shopId) {
        this.cartId = cartId;
        this.userId = userId;
        this.itemId = itemId;
        this.name = name;
        this.image = image;
        this.qty = qty;
        this.price = price;
        this.shopId = shopId;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "cartId=" + cartId +
                ", userId=" + userId +
                ", itemId=" + itemId +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", shopId=" + shopId +
                '}';
    }
}
