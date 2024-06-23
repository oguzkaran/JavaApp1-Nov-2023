package org.csystem.generator.password;


import org.csystem.generator.password.entity.UserInfo;
import org.csystem.generator.password.repository.IUserInfoRepository;
import org.csystem.generator.password.repository.UserInfoRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Random;

@Disabled
public class UserInfoRepositorySaveTest {
    private IUserInfoRepository m_userInfoRepository;
    private File m_file;

    @BeforeEach
    public void setUp() throws IOException
    {
        var path = Files.createDirectories(Path.of("passwords"));
        m_file = new File(path.toFile(), "oguz");
        m_file.delete();
        m_userInfoRepository = new UserInfoRepository(new File("passwords"), new Random());
    }

    @Test
    public void test()
    {
        var userInfo = new UserInfo("oguz", "1234", 10, 4, LocalDateTime.now());
        m_userInfoRepository.save(userInfo);
        assertTrue(m_file.exists());
        assertTrue(m_file.length() > 0);
    }
}
