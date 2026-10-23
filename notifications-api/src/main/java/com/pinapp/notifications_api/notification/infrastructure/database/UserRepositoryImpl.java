package com.pinapp.notifications_api.notification.infrastructure.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl /*implements UserRepository*/ {

    private final List<UserEntity> userEntityList = new ArrayList<>();

    private final UserEntityMapper userEntityMapper;

//    @Override
//    public User save(User user) {
//        UserEntity userEntity = userEntityMapper.mapToUserEntity(user);
//        userEntityList.add(userEntity);
//        return user;
//    }
//
//    @Override
//    public Optional<User> findById(Long id) {
//        return userEntityList.stream()
//                .filter(userEntity -> userEntity.getId().equals(id))
//                .findFirst()
//                .map(userEntityMapper::mapToUser);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        userEntityList.removeIf(userEntity -> userEntity.getId().equals(id));
//    }
//
//    @Override
//    public boolean existsByEmail(String email) {
//        return userEntityList.stream()
//                .anyMatch(userEntity -> userEntity.getEmail().equals(email));
//    }
}
