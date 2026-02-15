package com.luca.engineer.services;

import com.luca.engineer.client.AutocarMicroserviceAuthClient;
import com.luca.engineer.dto.RegistraVenditaRequest;
import com.luca.engineer.dto.RegistraVenditaResponse;
import com.luca.engineer.dto.TokenCheckResponse;
import com.luca.engineer.exceptions.AutoNotFound;
import com.luca.engineer.exceptions.TokenIsNotValid;
import com.luca.engineer.models.Auto;
import com.luca.engineer.models.Vendita;
import com.luca.engineer.repositories.AutoRepository;
import com.luca.engineer.repositories.VenditaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class VenditaService {

	private final AutocarMicroserviceAuthClient authClient;
    private final VenditaRepository venditaRepository;
    private final AutoRepository autoRepository;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;

    public Boolean checkToken(String token) {
        ResponseEntity<TokenCheckResponse> response = authClient.tokenCheck(token);
        return response.getBody().isValido();
    }

    public RegistraVenditaResponse registraVendita(RegistraVenditaRequest json, String token) throws TokenIsNotValid, AutoNotFound {
        if (checkToken(token)) {
            Auto auto = autoRepository.findById(json.getIdAuto()).get();

            if (auto == null)
                throw new AutoNotFound();

            Vendita vendita = new Vendita();
            vendita.setPrezzo(json.getPrezzo());
            vendita.setDataAcquisto(json.getDataAcquisto());
            vendita.setNominativoCliente(json.getNominativoCliente());
            vendita.setAuto(auto);

            auto.setVenduta(true);

            vendita = venditaRepository.save(vendita);
            autoRepository.save(auto);

            RegistraVenditaResponse res = modelMapper.map(vendita, RegistraVenditaResponse.class);
            return res;
        }
        throw new TokenIsNotValid();
    }

}
