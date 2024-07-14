package org.csystem.app.flight.service.city.controller;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.flight.data.service.FlightSystemDataService;
import org.csystem.app.flight.data.service.dto.CitySaveDTO;
import org.csystem.app.flight.data.service.dto.CityUpdateDTO;
import org.csystem.app.flight.service.city.error.CityError;
import org.csystem.data.exception.service.DataServiceException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/csd/system/flight/city")
@Slf4j
@ComponentScan(basePackages = "org.csystem")
public class CityController {
    private final FlightSystemDataService m_flightSystemDataService;

    public CityController(FlightSystemDataService flightSystemDataService)
    {
        m_flightSystemDataService = flightSystemDataService;
    }

    @PostMapping("add")
    public ResponseEntity<Object> addCity(@RequestBody CitySaveDTO citySaveDTO)
    {
        try {
            log.info("CityController.addCity -> City:{}", citySaveDTO.toString());
            return ResponseEntity.ok(m_flightSystemDataService.saveCity(citySaveDTO));
        }
        catch (DataServiceException ex) {
            var cityError = new CityError(citySaveDTO.getName(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());

            log.error("CityController.addCity -> City: {}, Exception: {}, Response {}", citySaveDTO.toString(),
                    ex.getMessage(), cityError.toString());

            return ResponseEntity.internalServerError().body(cityError);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") long id)
    {
        log.info("CityController.deleteById -> Id:{}", id);

        throw new UnsupportedOperationException("TODO:Bekir Kocadağ");
    }

    @GetMapping("find")
    public ResponseEntity<Object> findByName(@RequestParam String name)
    {
        log.info("CityController.findByName -> Name:{}", name);

        throw new UnsupportedOperationException("TODO:Bekir Kocadağ");
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") long id)
    {
        log.info("CityController.findById -> Id:{}", id);

        throw new UnsupportedOperationException("TODO:Bekir Kocadağ");
    }

    @GetMapping("find/all")
    public ResponseEntity<Object> findAll()
    {
        log.info("CityController.findAll");
        throw new UnsupportedOperationException("TODO:Bekir Kocadağ");
    }

    @PutMapping("update")
    public ResponseEntity<Object> update(@RequestBody CityUpdateDTO cityUpdateDTO)
    {
        log.info("CityController.update -> City:{}", cityUpdateDTO.toString());

        throw new UnsupportedOperationException("TODO:Bekir Kocadağ, Fatih Karabulut");
    }
}
