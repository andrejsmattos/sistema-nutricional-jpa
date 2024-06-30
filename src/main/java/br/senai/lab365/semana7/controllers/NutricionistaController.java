package br.senai.lab365.semana7.controllers;

import br.senai.lab365.semana7.controllers.dtos.EnderecoRequestDTO;
import br.senai.lab365.semana7.controllers.dtos.EnderecoResponseDTO;
import br.senai.lab365.semana7.controllers.dtos.NutricionistaRequestDTO;
import br.senai.lab365.semana7.controllers.dtos.NutricionistaResponseDTO;
import br.senai.lab365.semana7.services.NutricionistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nutricionistas")
public class NutricionistaController {

    @Autowired
    private NutricionistaService nutricionistaService;

    @PostMapping
    public NutricionistaResponseDTO criarNutricionista (@RequestBody NutricionistaRequestDTO requestDTO){
        return nutricionistaService.criarNutricionista(requestDTO);
    }

    @GetMapping
    public List<NutricionistaResponseDTO> listarTodosNutricionistas () {
        return nutricionistaService.listarTodosNutricionistas();
    }

    @GetMapping("/{id}")
    public NutricionistaResponseDTO listarNutricionistaPorId(@PathVariable Long id){
        return nutricionistaService.listarNutricionistaPorId(id);
    }

    @PutMapping("/{id}")
    public NutricionistaResponseDTO atualizarNutricionista (@PathVariable Long id, @RequestBody NutricionistaRequestDTO requestDTO) {
        return nutricionistaService.atualizarNutricionista(id, requestDTO);
    }


    @DeleteMapping("/{id}")
    public void deletarNutricionista (@PathVariable Long id) {
        nutricionistaService.deletarNutricionista(id);
    }

}
