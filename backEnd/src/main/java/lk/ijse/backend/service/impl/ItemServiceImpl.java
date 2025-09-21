package lk.ijse.backend.service.impl;

import lk.ijse.backend.DTO.ItemDTO;
import lk.ijse.backend.entity.Item;
import lk.ijse.backend.repo.ItemRepository;
import lk.ijse.backend.service.ItemService;
import lk.ijse.backend.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int addItem(ItemDTO itemDTO) {
        if (itemRepository.existsItemByItemName(itemDTO.getItemName()) && itemRepository.existsByShop_ShopId(itemDTO.getShopId())) {
            return VarList.Not_Acceptable;
        }else {
            itemRepository.save(modelMapper.map(itemDTO, Item.class));
            return VarList.Created;
        }
    }

    @Override
    public int deleteItem(long itemId) {
        if (itemRepository.existsById(itemId)) {
            itemRepository.deleteById(itemId);
            return VarList.OK;
        } else {
            return VarList.Not_Found;
        }
    }

    @Override
    public int updateItem(ItemDTO itemDTO) {
        if (itemRepository.existsById(itemDTO.getItemId())) {
            itemRepository.save(modelMapper.map(itemDTO, Item.class));
            return VarList.OK;
        } else {
            return VarList.Not_Found;
        }
    }

    @Override
    public ItemDTO searchItem(long itemId) {
        if (itemRepository.existsById(itemId)) {
            return modelMapper.map(itemRepository.findById(itemId).get(), ItemDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> itemList = itemRepository.findAll();
        return modelMapper.map(itemList,new TypeToken<List<ItemDTO>>(){}.getType());
    }

    @Override
    public ItemDTO searchItemByName(String itemName) {
        if (itemRepository.existsItemByItemName(itemName)) {
            return modelMapper.map(itemRepository.findItemByItemName(itemName), ItemDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public List<ItemDTO> searchItemByShop(long shopId) {
        List<Item> itemList = itemRepository.findItemByShop_ShopId(shopId);
        return modelMapper.map(itemList,new TypeToken<List<ItemDTO>>(){}.getType());
    }
}
