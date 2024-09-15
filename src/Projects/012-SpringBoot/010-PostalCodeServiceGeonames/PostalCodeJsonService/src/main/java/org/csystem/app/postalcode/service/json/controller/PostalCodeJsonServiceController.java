package org.csystem.app.postalcode.service.json.controller;

import org.csystem.app.postalcode.data.service.PostalCodeDataService;
import org.csystem.app.postalcode.data.service.dto.PostalCodesDTO;
import org.csystem.data.exception.service.DataServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/postalCode/json")
public class PostalCodeJsonServiceController {
    private final PostalCodeDataService m_postalCodeDataService;

    public PostalCodeJsonServiceController(PostalCodeDataService postalCodeDataService)
    {
        m_postalCodeDataService = postalCodeDataService;
    }

    @GetMapping("code")
    public ResponseEntity<PostalCodesDTO> findByPostalCode(@RequestParam(name = "c") String postalCode)
    {
        ResponseEntity<PostalCodesDTO> result;

        try {
            result =  ResponseEntity.ok(m_postalCodeDataService.findPostalCodes(postalCode));
        }
        catch (DataServiceException ex) {
            result = ResponseEntity.internalServerError().build();
        }

        return result;
    }

    @GetMapping("city")
    public ResponseEntity<PostalCodesDTO> findByCityName(@RequestParam(name = "name") String cityName)
    {
        ResponseEntity<PostalCodesDTO> result;

        try {
            result =  ResponseEntity.ok(m_postalCodeDataService.findPostalCodesByCityName(cityName));
        }
        catch (DataServiceException ex) {
            result = ResponseEntity.internalServerError().build();
        }

        return result;
    }

    //...
}
