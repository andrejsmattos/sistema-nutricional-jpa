package br.senai.lab365.semana7.controllers;

import br.senai.lab365.semana7.controllers.dtos.EnderecoRequestDTO;
import br.senai.lab365.semana7.controllers.dtos.EnderecoResponseDTO;
import br.senai.lab365.semana7.controllers.dtos.NutricionistaRequestDTO;
import br.senai.lab365.semana7.controllers.dtos.NutricionistaResponseDTO;
import br.senai.lab365.semana7.services.NutricionistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nutricionistas")
public class NutricionistaController {

    @Autowired
    private NutricionistaService nutricionistaService;

    @PostMapping
    public NutricionistaResponseDTO criarNutricionista (@RequestBody NutricionistaRequestDTO requestDTO){
        return nutricionistaService.criarNutricionista(requestDTO);
    }
}
