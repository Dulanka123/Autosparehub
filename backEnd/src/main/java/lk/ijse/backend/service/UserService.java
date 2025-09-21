package lk.ijse.backend.service;


import lk.ijse.backend.DTO.UpdateUserDTO;
import lk.ijse.backend.DTO.UserDTO;

import java.util.List;
import java.util.UUID;


public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO searchUser(String username);
    int deleteUser(String email);
    int updateUser(UpdateUserDTO updateUserDTO);
    List<UserDTO> getAllUsers();
}