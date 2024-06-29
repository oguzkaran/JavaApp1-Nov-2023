package org.csystem.generator.password.repository;


import lombok.extern.slf4j.Slf4j;
import org.csystem.data.exception.repository.RepositoryException;
import org.csystem.generator.password.entity.UserInfo;
import org.csystem.util.string.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.random.RandomGenerator;
import java.util.stream.Stream;

/* Ignore asynchronous access for demo. That repository will use DBMS, DBMS synchronize the access */

@Component
@Scope("prototype")
@Slf4j
public class UserInfoRepository implements IUserInfoRepository {
    private final File m_directory;
    private final RandomGenerator m_randomGenerator;

    static {
        try {
            Files.createDirectories(Path.of("passwords"));
        }
        catch (IOException ex) {
            log.error("Directory creation:{}", ex.getMessage());
        }
    }

    private void savePasswordCallback(BufferedWriter bw, String password)
    {
        try {
            bw.write(String.format("%s\r\n", password));
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void savePasswords(Path path, UserInfo userInfo) throws IOException
    {
        try (var bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
            userInfo.setRegisterTime(LocalDateTime.now());
            bw.write(DateTimeFormatter.ISO_DATE_TIME.format(userInfo.getRegisterTime()) + "\r\n");
            Stream.generate(() -> StringUtil.generateRandomTextEN(m_randomGenerator, userInfo.getLen()))
                    .limit(userInfo.getCount())
                    .forEach(p -> savePasswordCallback(bw, p));
        }
    }

    public UserInfoRepository(File directory, RandomGenerator randomGenerator)
    {
        m_directory = directory;
        m_randomGenerator = randomGenerator;
    }

    @Override
    public boolean existsById(String username)
    {
        return m_directory.toPath().resolve(username).toFile().exists();
    }

    @Override
    public <S extends UserInfo> S save(S userInfo)
    {
        try {
            var path = m_directory.toPath().resolve(userInfo.getUsername());

            savePasswords(path, userInfo);

            return userInfo;
        }
        catch (IOException ex) {
            throw new RepositoryException("UserInfoRepository.save: IO problem", ex);
        }
        catch (Throwable ex) {
            throw new RepositoryException("UserInfoRepository.save: problem", ex);
        }
    }

    @Override
    public Optional<UserInfo> saveIfNotExists(UserInfo userInfo)
    {
        return existsById(userInfo.getUsername()) ? Optional.empty() : Optional.of(save(userInfo));
    }

    //////////////////////////////////////////////////////////////////////
    @Override
    public long count()
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public void delete(UserInfo entity)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public void deleteAll()
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public void deleteAll(Iterable<? extends UserInfo> entities)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public void deleteById(String s)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }


    @Override
    public Iterable<UserInfo> findAll()
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public Iterable<UserInfo> findAllById(Iterable<String> strings)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public Optional<UserInfo> findById(String s)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }



    @Override
    public <S extends UserInfo> Iterable<S> saveAll(Iterable<S> entities)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }
}
