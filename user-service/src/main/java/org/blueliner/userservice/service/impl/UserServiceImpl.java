package org.blueliner.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blueliner.userservice.dto.UserCredentialDto;
import org.blueliner.userservice.model.User;
import org.blueliner.userservice.repository.UserRepository;
import org.blueliner.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    @Override
    public void saveUserCredentials(UserCredentialDto userCredentialDto) {
        User foodOrder = modelMapper.map(userCredentialDto, User.class);
        User persistedFoodOrder = userRepository.save(foodOrder);
        log.info("food order persisted {}", persistedFoodOrder);
    }

}
