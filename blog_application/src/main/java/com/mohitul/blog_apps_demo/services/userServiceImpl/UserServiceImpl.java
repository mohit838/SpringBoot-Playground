package com.mohitul.blog_apps_demo.services.userServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mohitul.blog_apps_demo.entity.UserEntity;
import com.mohitul.blog_apps_demo.exceptions.ResourceAlreadyExistsException;
import com.mohitul.blog_apps_demo.exceptions.ResourceNotFoundException;
import com.mohitul.blog_apps_demo.payloads.UserDto;
import com.mohitul.blog_apps_demo.repository.UserRepository;
import com.mohitul.blog_apps_demo.services.UserServices;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserServices {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto createNewUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ResourceAlreadyExistsException(
                    "User", "email", userDto.getEmail());
        }

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        UserEntity savedUser = userRepository.save(userEntity);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        UserEntity existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        if (userDto.getUserName() != null && !userDto.getUserName().trim().isEmpty()) {
            existingUser.setUserName(userDto.getUserName());
        }
        if (userDto.getEmail() != null && !userDto.getEmail().trim().isEmpty()) {
            existingUser.setEmail(userDto.getEmail());
        }
        if (userDto.getPassword() != null && !userDto.getPassword().trim().isEmpty()) {
            existingUser.setPassword(userDto.getPassword());
        }
        if (userDto.getAbout() != null && !userDto.getAbout().trim().isEmpty()) {
            existingUser.setAbout(userDto.getAbout());
        }

        UserEntity updatedUser = userRepository.save(existingUser);

        return modelMapper.map(updatedUser, UserDto.class);
    }


    @Override
    public UserDto getUserById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "User", "id", userId));

        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public List<UserDto> listOfUsers() {
        List<UserEntity> entities = userRepository.findAll(
                Sort.by(Sort.Direction.DESC, "id"));

        return entities.stream()
                .map(userEntity -> modelMapper.map(userEntity, UserDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User", "id", userId));
        userRepository.delete(userEntity);

    }
}
