package org.csystem.app.postalcode.service.json.controller;


import org.csystem.app.postalcode.data.service.PostalCodeDataService;
import org.csystem.app.postalcode.data.service.dto.PostalCode;
import org.csystem.app.postalcode.data.service.dto.PostalCodes;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/postalcode/json")
public class PostalCodeJsonServiceController {
    private final PostalCodeDataService m_postalCodeDataService;

    public PostalCodeJsonServiceController(PostalCodeDataService postalCodeDataService)
    {
        m_postalCodeDataService = postalCodeDataService;
    }

    @GetMapping("code")
    public ResponseEntity<PostalCodes> findByPostalCode(@RequestParam(name = "c") String postalCode)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @GetMapping("city")
    public ResponseEntity<PostalCodes> findByCityName(@RequestParam(name = "name") String cityName)
    {
        throw new UnsupportedOperationException("TODO");
    }

    //...
}
