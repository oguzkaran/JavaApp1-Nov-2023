package org.csystem.generator.password.configuratiıon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FileConfig {
    @Bean
    @Scope("prototype")
    public File files()
    {
        return new File("passwords");
    }
}
