package lk.ijse.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*@AllArgsConstructor
@NoArgsConstructor
@Data*/
@Entity
public class OrderedItemDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderedItemDetailId;
    private String description;
    private double price;
    private double itemPrice;
    private int qty;
    private long shopId;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    private Item item;

    public OrderedItemDetail() {
    }

    public OrderedItemDetail(long orderedItemDetailId, String description, double price, double itemPrice, int qty, long shopId, Order order, Item item) {
        this.orderedItemDetailId = orderedItemDetailId;
        this.description = description;
        this.price = price;
        this.itemPrice = itemPrice;
        this.qty = qty;
        this.shopId = shopId;
        this.order = order;
        this.item = item;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public long getOrderedItemDetailId() {
        return orderedItemDetailId;
    }

    public void setOrderedItemDetailId(long orderedItemDetailId) {
        this.orderedItemDetailId = orderedItemDetailId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "OrderedItemDetail{" +
                "orderedItemDetailId=" + orderedItemDetailId +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", itemPrice=" + itemPrice +
                ", qty=" + qty +
                ", order=" + order +
                ", item=" + item +
                '}';
    }
}
