package br.senai.lab365.semana7.services;

import br.senai.lab365.semana7.controllers.dtos.PacienteRequestDTO;
import br.senai.lab365.semana7.controllers.dtos.PacienteResponseDTO;
import br.senai.lab365.semana7.entities.EnderecoEntity;
import br.senai.lab365.semana7.entities.PacienteEntity;
import br.senai.lab365.semana7.repositories.EnderecoRepository;
import br.senai.lab365.semana7.repositories.PacienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public PacienteResponseDTO criarPaciente (PacienteRequestDTO requestDTO) {
        PacienteEntity paciente = new PacienteEntity();
        BeanUtils.copyProperties(requestDTO, paciente);

        EnderecoEntity endereco = enderecoRepository.findById(requestDTO.getEnderecoId())
                .orElseThrow(()->new RuntimeException("O endereço com este id não foi encontrado."));
        paciente = pacienteRepository.save(paciente);
        return converterEntidadeParaResponseDTO(paciente);
    }

    public List<PacienteResponseDTO> listarTodosPacientes () {
        List<PacienteEntity> pacientes = pacienteRepository.findAll();
        return pacientes.stream().map(this::converterEntidadeParaResponseDTO)
                .collect(Collectors.toList());
    }

    public PacienteResponseDTO listarPacientePorId (Long id) {
        PacienteEntity paciente = pacienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O paciente com este id não foi encontrado"));
        return converterEntidadeParaResponseDTO(paciente);
    }

    public PacienteResponseDTO atualizarPaciente (Long id, PacienteRequestDTO requestDTO) {
        PacienteEntity paciente = pacienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O paciente com este id não foi encontrado"));
        BeanUtils.copyProperties(requestDTO, paciente);
        pacienteRepository.save(paciente);
        return converterEntidadeParaResponseDTO(paciente);
    }

    public void deletarPaciente (Long id) {
        PacienteEntity paciente = pacienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O paciente com este id não foi encontrado"));
        pacienteRepository.delete(paciente);
    }

    private PacienteResponseDTO converterEntidadeParaResponseDTO (PacienteEntity paciente) {
        PacienteResponseDTO pacienteResponseDTO = new PacienteResponseDTO();
        BeanUtils.copyProperties(paciente, pacienteResponseDTO);
        return pacienteResponseDTO;
    }
}
