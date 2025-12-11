package com.luca.engineer.controllers;

import com.luca.engineer.dto.RegistraVenditaRequest;
import com.luca.engineer.dto.RegistraVenditaResponse;
import com.luca.engineer.exceptions.TokenIsNotValid;
import com.luca.engineer.services.VenditaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/vendita")
public class VenditaController {

    private final VenditaService venditaService;

    @PostMapping("/registra")
    public ResponseEntity<RegistraVenditaResponse> registraVendita(
        @RequestBody RegistraVenditaRequest json,
        @RequestHeader(name = "Authorization") String token
    ) {
        try {
            return ResponseEntity.ok(venditaService.registraVendita(json, token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
