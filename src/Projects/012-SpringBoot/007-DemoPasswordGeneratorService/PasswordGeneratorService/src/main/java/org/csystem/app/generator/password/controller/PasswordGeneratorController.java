package org.csystem.app.generator.password.controller;

import lombok.extern.slf4j.Slf4j;
import org.csystem.data.exception.service.DataServiceException;
import org.csystem.generator.password.data.service.PasswordGeneratorDataService;
import org.csystem.generator.password.data.service.dto.UserInfoSaveDTO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan(basePackages = "org.csystem")
@RequestMapping("/api/generator/password")
@Slf4j
public class PasswordGeneratorController {
    private final PasswordGeneratorDataService m_passwordGeneratorDataService;

    public PasswordGeneratorController(PasswordGeneratorDataService passwordGeneratorDataService)
    {
        m_passwordGeneratorDataService = passwordGeneratorDataService;
    }

    @PostMapping("/generate")
    public ResponseEntity<UserInfoSaveDTO> generatePassword(@RequestBody UserInfoSaveDTO userInfoSaveDTO)
    {
        ResponseEntity<UserInfoSaveDTO> responseEntity;

        try {
            var result = m_passwordGeneratorDataService.saveUserInfo(userInfoSaveDTO);

            responseEntity = result ? ResponseEntity.ok(userInfoSaveDTO) : ResponseEntity.badRequest().body(userInfoSaveDTO);
            log.info("PasswordGeneratorController.generatePassword -> status:{}", result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
        }
        catch (DataServiceException ex) {
            log.error("PasswordGeneratorController.generatePassword -> DataServiceException:Exception:{}, Message:{}",
                    ex.getCause().getClass().getSimpleName(), ex.getMessage());

            responseEntity = ResponseEntity.internalServerError().body(userInfoSaveDTO);
        }

        return responseEntity;
    }

    @GetMapping("/users/names/all")
    public List<String> findAllUsernames()
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    //...
}
