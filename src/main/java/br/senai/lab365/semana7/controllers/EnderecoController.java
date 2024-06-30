package br.senai.lab365.semana7.controllers;

import br.senai.lab365.semana7.controllers.dtos.EnderecoRequestDTO;
import br.senai.lab365.semana7.controllers.dtos.EnderecoResponseDTO;
import br.senai.lab365.semana7.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public EnderecoResponseDTO criarEndereco (@RequestBody EnderecoRequestDTO requestDTO){
        return enderecoService.criarEndereco(requestDTO);
    }

    @GetMapping
    public List<EnderecoResponseDTO> listarTodosEnderecos () {
        return enderecoService.listarTodosEnderecos();
    }

    @GetMapping("/{id}")
    public EnderecoResponseDTO listarEnderecoPorId (@PathVariable Long id) {
        return enderecoService.listarEnderecoPorId(id);
    }

    @PutMapping("/{id}")
    public EnderecoResponseDTO atualizarEnderecoPorId (@PathVariable Long id, @RequestBody EnderecoRequestDTO requestDTO) {
        return enderecoService.atualizarEndereco(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarEnderecoPorId (@PathVariable Long id) {
        enderecoService.deletarEndereco(id);
    }

}
