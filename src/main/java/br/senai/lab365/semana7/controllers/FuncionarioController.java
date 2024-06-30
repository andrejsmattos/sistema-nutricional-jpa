package br.senai.lab365.semana7.controllers;

import br.senai.lab365.semana7.controllers.dtos.FuncionarioRequestDTO;
import br.senai.lab365.semana7.controllers.dtos.FuncionarioResponseDTO;
import br.senai.lab365.semana7.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public FuncionarioResponseDTO criarFuncionario (@RequestBody FuncionarioRequestDTO requestDTO) {
        return funcionarioService.criarFuncionario(requestDTO);
    }

    @GetMapping
    public List<FuncionarioResponseDTO> listarTodosFuncionarios () {
        return funcionarioService.listarTodosFuncionarios();
    }

    @GetMapping("/{id}")
    public FuncionarioResponseDTO listarFuncionarioPorId (@PathVariable Long id) {
        return funcionarioService.listarFuncionarioPorId(id);
    }

    @PutMapping("/{id}")
    public FuncionarioResponseDTO atualizarFuncionario (@PathVariable Long id, @RequestBody FuncionarioRequestDTO requestDTO) {
        return funcionarioService.atualizarFuncionario(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public void deletar (@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
    }
}
