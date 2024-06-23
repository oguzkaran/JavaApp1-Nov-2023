package org.csystem.generator.password.dal;

import lombok.extern.slf4j.Slf4j;
import org.csystem.generator.password.entity.UserInfo;
import org.csystem.generator.password.repository.IUserInfoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class PasswordGeneratorHelper {
    private final IUserInfoRepository m_userInfoRepository;
    //Other repositories

    public PasswordGeneratorHelper(IUserInfoRepository userInfoRepository)
    {
        m_userInfoRepository = userInfoRepository;
    }

    public Optional<UserInfo> saveUserIfNotExists(UserInfo userInfo)
    {
        //...

        log.info("PasswordGeneratorHelper.saveUserInfoIfNotExists:{}", userInfo.toString());

        return m_userInfoRepository.saveIfNotExists(userInfo);
    }

    //... (facade)
}
