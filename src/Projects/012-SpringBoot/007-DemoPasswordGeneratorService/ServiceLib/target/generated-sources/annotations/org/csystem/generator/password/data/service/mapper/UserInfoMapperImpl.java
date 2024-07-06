package org.csystem.generator.password.data.service.mapper;

import javax.annotation.processing.Generated;
import org.csystem.generator.password.data.service.dto.UserInfoSaveDTO;
import org.csystem.generator.password.entity.UserInfo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-06T15:29:55+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class UserInfoMapperImpl implements IUserInfoMapper {

    @Override
    public UserInfo toUserInfo(UserInfoSaveDTO userInfoSaveDTO) {
        if ( userInfoSaveDTO == null ) {
            return null;
        }

        UserInfo userInfo = new UserInfo();

        userInfo.setCount( userInfoSaveDTO.getTextCount() );
        userInfo.setLen( userInfoSaveDTO.getTextLength() );
        userInfo.setUsername( userInfoSaveDTO.getUsername() );
        userInfo.setPassword( userInfoSaveDTO.getPassword() );

        return userInfo;
    }
}
