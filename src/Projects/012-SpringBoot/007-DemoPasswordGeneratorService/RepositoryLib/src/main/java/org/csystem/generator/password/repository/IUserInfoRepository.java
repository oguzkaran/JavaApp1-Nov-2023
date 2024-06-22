package org.csystem.generator.password.repository;

import org.csystem.generator.password.entity.UserInfo;
import org.csystem.data.exception.repository.ICrudRepository;

public interface IUserInfoRepository extends ICrudRepository<UserInfo, String> {
    //...
}
