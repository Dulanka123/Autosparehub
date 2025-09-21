package lk.ijse.backend.service.impl;

import lk.ijse.backend.DTO.UpdateUserDTO;
import lk.ijse.backend.DTO.UserDTO;
import lk.ijse.backend.entity.User;
import lk.ijse.backend.repo.UserRepository;
import lk.ijse.backend.service.UserService;
import lk.ijse.backend.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    public UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return modelMapper.map(user,UserDTO.class);
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public UserDTO searchUser(String username) {
        if (userRepository.existsByEmail(username)) {
            User user=userRepository.findByEmail(username);
            return modelMapper.map(user,UserDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public int deleteUser(String email) {
        if (userRepository.existsByEmail(email)) {
            userRepository.deleteByEmail(email);
            return VarList.Created;
        } else {
            return VarList.Not_Found;
        }
    }

    public int updateUser(UpdateUserDTO dto) {
        if (!userRepository.existsByEmail(dto.getEmail())) {
            return VarList.Not_Found;
        }

        User user = userRepository.findByEmail(dto.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Check if old password matches
        if (!encoder.matches(dto.getOldPassword(), user.getPassword())) {
            return VarList.Unauthorized; // or any constant representing wrong password
        }

        // Update fields
        user.setName(dto.getName());
        user.setMobile(dto.getMobile());
        user.setAddress(dto.getAddress());
        user.setNic(dto.getNic());
        user.setDob(dto.getDob());

        // If new password is not empty, update it
        if (dto.getNewPassword() != null && !dto.getNewPassword().isEmpty()) {
            user.setPassword(encoder.encode(dto.getNewPassword()));
        }

        userRepository.save(user);
        return VarList.OK;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        userRepository.findAll();
        return modelMapper.map(userRepository.findAll(),new TypeToken<List<UserDTO>>(){}.getType());
    }


    @Override
    public int saveUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.Created;
        }
    }}
