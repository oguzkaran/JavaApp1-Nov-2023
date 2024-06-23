package org.csystem.generator.password.data.service.mapper;

import org.csystem.generator.password.data.service.dto.UserInfoSaveDTO;
import org.csystem.generator.password.entity.UserInfo;

public interface IUserInfoMapper {
    UserInfo toUserInfo(UserInfoSaveDTO userInfoSaveDTO);
}
