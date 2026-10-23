package com.pinapp.notifications_api.notification.infrastructure.database;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserEntityMapper {

//    UserEntity mapToUserEntity(User user);
//
//    User mapToUser(UserEntity userEntity);

}
