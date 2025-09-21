package lk.ijse.backend.controller;


import lk.ijse.backend.DTO.ItemDTO;
import lk.ijse.backend.DTO.ResponseDTO;
import lk.ijse.backend.service.ItemService;
import lk.ijse.backend.util.FileUploadUtil;
import lk.ijse.backend.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveItem(@ModelAttribute ItemDTO itemDTO, @RequestParam("image") MultipartFile image , @RequestHeader ("Authorization") String authHeader) {
        try {
            String imagePath = FileUploadUtil.uploadFile("item-Images/", image.getOriginalFilename(), image);
            itemDTO.setItemImage("assets/images/item-Images/" + imagePath);
            int res = itemService.addItem(itemDTO);
            switch (res) {
                case VarList.Created -> {
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", null));
                }
                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Item Already Exists", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (IOException e) {
            return ResponseEntity.ok(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteItem(@PathVariable long id, @RequestHeader ("Authorization") String authHeader) {
        try {
            itemService.deleteItem(id);
            return ResponseEntity.ok(new ResponseDTO(VarList.OK, "Success", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateItem(@ModelAttribute ItemDTO itemDTO, @RequestParam(value = "image", required = false ) MultipartFile image, @RequestHeader ("Authorization") String authHeader) {
        try {
            ItemDTO findItemInDB = itemService.searchItem(itemDTO.getItemId());

            if (image != null && !image.isEmpty()) {
                String imagePath = FileUploadUtil.uploadFile("item-Images/", image.getOriginalFilename(), image);
                findItemInDB.setItemImage("assets/images/item-Images/" + imagePath);
            }

            findItemInDB.setItemId(itemDTO.getItemId());
            findItemInDB.setItemName(itemDTO.getItemName());
            findItemInDB.setItemDescription(itemDTO.getItemDescription());
            findItemInDB.setVehicleModel(itemDTO.getVehicleModel());
            findItemInDB.setFuelType(itemDTO.getFuelType());
            findItemInDB.setItemPrice(itemDTO.getItemPrice());
            findItemInDB.setItemQty(itemDTO.getItemQty());
            findItemInDB.setShopId(itemDTO.getShopId());

           int res = itemService.updateItem(findItemInDB);
            switch (res) {
                case VarList.OK -> {
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseDTO(VarList.OK, "Success", null));
                }
                case VarList.Not_Found -> {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ResponseDTO(VarList.Not_Found, "Item Not Found", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (IOException e) {
            return ResponseEntity.ok(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<ResponseDTO> searchItem(@PathVariable long id) {
        ItemDTO itemDTO = itemService.searchItem(id);
        if (itemDTO != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK, "Success", itemDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(VarList.Not_Found, "Item Not Found", null));
        }
    }
    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllItems() {
        List<ItemDTO> allItems = itemService.getAllItems();
        return ResponseEntity.ok(new ResponseDTO(VarList.OK, "Success", allItems));
    }

    @GetMapping("/single-shop-all-item")
    public ResponseEntity<ResponseDTO> getSingleShopAllItems(@RequestHeader ("Authorization") String authHeader,@RequestParam long shopId) {
        List<ItemDTO> allItems = itemService.searchItemByShop(shopId);
        return ResponseEntity.ok(new ResponseDTO(VarList.OK, "Success", allItems));
    }
}
