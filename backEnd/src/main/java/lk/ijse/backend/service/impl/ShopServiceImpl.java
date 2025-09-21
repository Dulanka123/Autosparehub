package lk.ijse.backend.service.impl;

import lk.ijse.backend.DTO.ShopDTO;
import lk.ijse.backend.DTO.UserDTO;
import lk.ijse.backend.entity.Shop;
import lk.ijse.backend.entity.User;
import lk.ijse.backend.repo.ShopRepository;
import lk.ijse.backend.service.ShopService;
import lk.ijse.backend.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public int saveShop(ShopDTO shopDTO) {
        if (shopRepository.existsShopByShopEmail((shopDTO.getShopEmail()))) {
            return VarList.Not_Acceptable;
        } else {
            User user = modelMapper.map(shopDTO.getUserDTO(), User.class);
            Shop shop = modelMapper.map(shopDTO, Shop.class);
            shop.setUser(user);
            shopRepository.save(shop);
            return VarList.Created;
        }
    }

    @Override
    public ShopDTO searchShop(String shopEmail) {
        if (shopRepository.existsShopByShopEmail(shopEmail)) {
            Shop shop = shopRepository.findShopByShopEmail(shopEmail);
            UserDTO userDTO = modelMapper.map(shop.getUser(), UserDTO.class);
            ShopDTO shopDTO = modelMapper.map(shop, ShopDTO.class);
            shopDTO.setUserDTO(userDTO);
            return modelMapper.map(shopDTO, ShopDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public int deleteShop(long shopId) {
        if (shopRepository.existsById(shopId)) {
            shopRepository.deleteById(shopId);
            return VarList.OK;
        } else {
            return VarList.Not_Found;
        }
    }
    @Override
    public int updateShop(ShopDTO shopDTO) {
        if (shopRepository.existsShopByShopEmail(shopDTO.getShopEmail())) {
            User user = modelMapper.map(shopDTO.getUserDTO(), User.class);
            Shop shop = modelMapper.map(shopDTO, Shop.class);
            shop.setUser(user);
            shopRepository.save(shop);
            return VarList.OK;
        } else {
            return VarList.Not_Found;
        }
    }

    @Override
    public ShopDTO findShop(UserDTO userDTO) {
       Shop shop =  shopRepository.findShopByUser(modelMapper.map(userDTO , User.class));
         if (shop != null){
              return modelMapper.map(shop , ShopDTO.class);
    }
        return null;
    }

    @Override
    public List<ShopDTO> findAllShops() {
        return modelMapper.map(shopRepository.findAll(),new TypeToken<List<ShopDTO>>(){}.getType());
    }

    @Override
    public ShopDTO searchShopById(long shopId) {
        shopRepository.findById(shopId);
        return modelMapper.map(shopRepository.findById(shopId),ShopDTO.class);
    }
}
