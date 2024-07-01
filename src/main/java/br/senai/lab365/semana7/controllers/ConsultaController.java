package br.senai.lab365.semana7.controllers;

import br.senai.lab365.semana7.controllers.dtos.ConsultaRequestDTO;
import br.senai.lab365.semana7.controllers.dtos.ConsultaResponseDTO;
import br.senai.lab365.semana7.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ConsultaResponseDTO criarConsulta (@RequestBody ConsultaRequestDTO requestDTO) {
        return consultaService.criarConsulta(requestDTO);
    }

    @GetMapping
    public List<ConsultaResponseDTO> listarTodasConsultas () {
        return consultaService.listarTodasConsultas();
    }

    @GetMapping("/{id}")
    public ConsultaResponseDTO listarConsultaPorId (@PathVariable Long id) {
        return consultaService.listarConsultaPorId(id);
    }

    @PutMapping("/{id}")
    public ConsultaResponseDTO atualizarConsulta (@PathVariable Long id, @RequestBody ConsultaRequestDTO requestDTO) {
        return consultaService.atualizarConsulta(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarConsulta (@PathVariable Long id) {
        consultaService.deletarConsulta(id);
    }
}
