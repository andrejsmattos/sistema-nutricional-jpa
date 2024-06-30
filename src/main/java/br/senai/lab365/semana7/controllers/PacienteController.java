package br.senai.lab365.semana7.controllers;

import br.senai.lab365.semana7.controllers.dtos.PacienteRequestDTO;
import br.senai.lab365.semana7.controllers.dtos.PacienteResponseDTO;
import br.senai.lab365.semana7.repositories.PacienteRepository;
import br.senai.lab365.semana7.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public PacienteResponseDTO criarPaciente (@RequestBody PacienteRequestDTO requestDTO) {
        return pacienteService.criarPaciente(requestDTO);
    }

    @GetMapping
    public List<PacienteResponseDTO> listarTodosPacientes () {
        return pacienteService.listarTodosPacientes();
    }

    @GetMapping("/{id}")
    public PacienteResponseDTO listarPacientePorId (@PathVariable Long id) {
        return pacienteService.listarPacientePorId(id);
    }

    @PutMapping("/{id}")
    public PacienteResponseDTO atualizarPaciente (@PathVariable Long id, @RequestBody PacienteRequestDTO requestDTO) {
        return pacienteService.atualizarPaciente(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarPaciente (@PathVariable Long id) {
        pacienteService.deletarPaciente(id);
    }
}
