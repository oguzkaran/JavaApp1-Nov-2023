package org.csystem.generator.password.data.service;

import lombok.extern.slf4j.Slf4j;
import org.csystem.generator.password.dal.PasswordGeneratorHelper;
import org.csystem.generator.password.data.service.dto.UserInfoSaveDTO;
import org.csystem.generator.password.data.service.mapper.IUserInfoMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PasswordGeneratorDataService {
    private final PasswordGeneratorHelper m_passwordGeneratorHelper;
    private final IUserInfoMapper m_userInfoMapper;

    public PasswordGeneratorDataService(PasswordGeneratorHelper passwordGeneratorHelper, IUserInfoMapper userInfoMapper)
    {
        m_passwordGeneratorHelper = passwordGeneratorHelper;
        m_userInfoMapper = userInfoMapper;
    }

    public boolean saveUserInfo(UserInfoSaveDTO userInfoSaveDTO)
    {
        log.info("PasswordGeneratorService.saveUserInfo:{}", userInfoSaveDTO.toString());

        return m_passwordGeneratorHelper.saveUserIfNotExists(m_userInfoMapper.toUserInfo(userInfoSaveDTO)).isPresent();
    }

    //...
}
