package org.csystem.generator.password.repository;

import org.csystem.generator.password.entity.UserInfo;
import org.csystem.data.exception.repository.ICrudRepository;

import java.util.Optional;

public interface IUserInfoRepository extends ICrudRepository<UserInfo, String> {
    Optional<UserInfo> saveIfNotExists(UserInfo userInfo);
}
