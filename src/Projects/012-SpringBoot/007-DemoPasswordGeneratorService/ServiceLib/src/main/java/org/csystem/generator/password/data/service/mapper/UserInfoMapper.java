package org.csystem.generator.password.data.service.mapper;

import org.csystem.generator.password.data.service.dto.UserInfoSaveDTO;
import org.csystem.generator.password.entity.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserInfoMapper implements IUserInfoMapper {
    @Override
    public UserInfo toUserInfo(UserInfoSaveDTO userInfoSaveDTO)
    {
        var userInfo = new UserInfo();

        userInfo.setUsername(userInfoSaveDTO.getUsername());
        userInfo.setPassword(userInfoSaveDTO.getPassword());
        userInfo.setCount(userInfoSaveDTO.getTextCount());
        userInfo.setLen(userInfoSaveDTO.getTextLength());

        return userInfo;
    }
}
