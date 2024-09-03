package com.mohitul.blog_apps_demo.services.userServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mohitul.blog_apps_demo.entity.UserEntity;
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
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        UserEntity savedUser = userRepository.save(userEntity);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User", " id ", userId));

        modelMapper.map(userDto, userEntity);
        UserEntity updateUser = userRepository.save(userEntity);
        return modelMapper.map(updateUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", " id:", userId));

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
                .orElseThrow(() -> new ResourceNotFoundException("User ", " id:", userId));
        userRepository.delete(userEntity);

    }
}
