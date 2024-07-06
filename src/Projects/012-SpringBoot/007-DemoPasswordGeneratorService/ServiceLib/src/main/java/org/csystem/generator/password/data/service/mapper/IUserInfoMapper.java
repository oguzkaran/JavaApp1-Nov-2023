package org.csystem.generator.password.data.service.mapper;

import org.csystem.generator.password.data.service.dto.UserInfoSaveDTO;
import org.csystem.generator.password.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(implementationName = "UserInfoMapperImpl", componentModel = "spring")
public interface IUserInfoMapper {
    @Mapping(source = "textCount", target = "count")
    @Mapping(source = "textLength", target = "len")
    UserInfo toUserInfo(UserInfoSaveDTO userInfoSaveDTO);
}
