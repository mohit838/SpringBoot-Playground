package com.mohitul.blog_apps_demo.services.userServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mohitul.blog_apps_demo.entity.UserEntity;
import com.mohitul.blog_apps_demo.exceptions.ResourceNotFoundException;
import com.mohitul.blog_apps_demo.mapper.UserMapper;
import com.mohitul.blog_apps_demo.payloads.UserDto;
import com.mohitul.blog_apps_demo.repository.UserRepository;
import com.mohitul.blog_apps_demo.services.UserServices;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserServices {

    private UserRepository userRepository;

    @Override
    public UserDto createNewUser(UserDto userDto) {
        UserEntity userEntity = UserMapper.mapToUser(userDto);
        UserEntity savedUser = userRepository.save(userEntity);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User", " id ", userId));

        userEntity.setUserName(userDto.getUserName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setAbout(userDto.getAbout());

        UserEntity updateUser = userRepository.save(userEntity);

        return UserMapper.mapToUserDto(updateUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", " id:", userId));

        return UserMapper.mapToUserDto(userEntity);
    }

    @Override
    public List<UserDto> listOfUsers() {
        List<UserEntity> entities = userRepository.findAll(
                Sort.by(Sort.Direction.DESC, "id"));

        return entities.stream()
                .map((entity) -> UserMapper.mapToUserDto(entity)).collect(Collectors.toList());

    }

    @Override
    public void deleteUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User ", " id:", userId));
        userRepository.delete(userEntity);

    }
}
