package br.senai.lab365.semana7.controllers;

import br.senai.lab365.semana7.controllers.dtos.ConsultaRequestDTO;
import br.senai.lab365.semana7.controllers.dtos.ConsultaResponseDTO;
import br.senai.lab365.semana7.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/nutricionista/{nomeNutricionista}")
    public ResponseEntity<List<ConsultaResponseDTO>> listarConsultasPorNomeNutricionista(@PathVariable String nomeNutricionista) {
        List<ConsultaResponseDTO> consultas = consultaService.listarConsultasPorNomeNutricionista(nomeNutricionista);
        return ResponseEntity.ok(consultas);
    }

    // Endpoint para listar consultas por nome do paciente
    @GetMapping("/paciente/{nomePaciente}")
    public ResponseEntity<List<ConsultaResponseDTO>> listarConsultasPorNomePaciente(@PathVariable String nomePaciente) {
        List<ConsultaResponseDTO> consultas = consultaService.listarConsultasPorNomePaciente(nomePaciente);
        return ResponseEntity.ok(consultas);
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
