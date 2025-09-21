package lk.ijse.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/*@AllArgsConstructor
@NoArgsConstructor
@Data*/

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;
    private String itemName;
    private String itemDescription;
    private String vehicleModel;
    private String fuelType;
    private double itemPrice;
    private int itemQty;
    private String itemImage;


    @OneToMany(cascade = CascadeType.ALL,
    fetch = FetchType.LAZY,
    mappedBy = "item")
    private List<OrderedItemDetail> itemDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "shopId", referencedColumnName = "shopId")
    private Shop shop;

    public Item() {
    }

    public Item(long itemId, String itemName, String itemDescription, String vehicleModel, String fuelType, double itemPrice, int itemQty, String itemImage, List<OrderedItemDetail> itemDetails, Shop shop) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.vehicleModel = vehicleModel;
        this.fuelType = fuelType;
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
        this.itemImage = itemImage;
        this.itemDetails = itemDetails;
        this.shop = shop;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public List<OrderedItemDetail> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(List<OrderedItemDetail> itemDetails) {
        this.itemDetails = itemDetails;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
