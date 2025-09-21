package lk.ijse.backend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*@AllArgsConstructor
@NoArgsConstructor
@Data*/
public class ItemDTO {
    private long itemId;
    @NotBlank
    @Size(min = 3, max = 50 , message = "Item name must contain 3-50 characters")
    private String itemName;
    @NotBlank(message = "Item description is required")
    private String itemDescription;
    @NotBlank(message = "Vehicle model is required")
    private String vehicleModel;
    @NotBlank(message = "Fuel type is required")
    private String fuelType;
    @Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "Invalid price")
    private double itemPrice;
    @Pattern(regexp = "^[0-9]+$", message = "Invalid quantity")
    private int itemQty;
    @NotBlank(message = "Item image is required")
    private String itemImage;
    private long shopId;



    public ItemDTO() {
    }

    public ItemDTO(long itemId, String itemName, String itemDescription, String vehicleModel, String fuelType, double itemPrice, int itemQty, String itemImage, long shopId) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.vehicleModel = vehicleModel;
        this.fuelType = fuelType;
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
        this.itemImage = itemImage;
        this.shopId = shopId;
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

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemQty=" + itemQty +
                ", itemImage='" + itemImage + '\'' +
                ", shopId=" + shopId +
                '}';
    }
}
