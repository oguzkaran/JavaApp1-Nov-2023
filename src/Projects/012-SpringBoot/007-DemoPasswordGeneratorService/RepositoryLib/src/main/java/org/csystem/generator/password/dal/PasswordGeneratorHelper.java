package org.csystem.generator.password.dal;

import lombok.extern.slf4j.Slf4j;
import org.csystem.generator.password.entity.UserInfo;
import org.csystem.generator.password.repository.IUserInfoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Component
@Slf4j
public class PasswordGeneratorHelper {
    private final IUserInfoRepository m_userInfoRepository;
    //Other repositories

    public PasswordGeneratorHelper(IUserInfoRepository userInfoRepository)
    {
        m_userInfoRepository = userInfoRepository;
    }

    public List<String> findAllUserNames()
    {
        log.info("PasswordGeneratorHelper.findAllUserNames:");

        return StreamSupport.stream(m_userInfoRepository.findAll().spliterator(), false)
                .map(UserInfo::getUsername).toList();
    }

    public Iterable<UserInfo> findAllUsers()
    {
        log.info("PasswordGeneratorHelper.findAllUsers:");

        return m_userInfoRepository.findAll();
    }

    public Optional<UserInfo> saveUserIfNotExists(UserInfo userInfo)
    {
        //...

        log.info("PasswordGeneratorHelper.saveUserInfoIfNotExists:{}", userInfo.toString());

        return m_userInfoRepository.saveIfNotExists(userInfo);
    }

    //... (facade)
}
