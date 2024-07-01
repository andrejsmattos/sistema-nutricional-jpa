package br.senai.lab365.semana7.services;

import br.senai.lab365.semana7.controllers.dtos.ConsultaRequestDTO;
import br.senai.lab365.semana7.controllers.dtos.ConsultaResponseDTO;
import br.senai.lab365.semana7.entities.ConsultaEntity;
import br.senai.lab365.semana7.entities.NutricionistaEntity;
import br.senai.lab365.semana7.entities.PacienteEntity;
import br.senai.lab365.semana7.repositories.ConsultaRepository;
import br.senai.lab365.semana7.repositories.NutricionistaRepository;
import br.senai.lab365.semana7.repositories.PacienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    public ConsultaResponseDTO criarConsulta (ConsultaRequestDTO requestDTO) {
        ConsultaEntity consulta = new ConsultaEntity();
        BeanUtils.copyProperties(requestDTO, consulta);

        PacienteEntity paciente = pacienteRepository.findById(requestDTO.getPacienteId())
                .orElseThrow(()-> new RuntimeException("O paciente com este id não foi encontrado."));
        consulta.setPacienteEntity(paciente);

        NutricionistaEntity nutricionista = nutricionistaRepository.findById(requestDTO.getNutricionistaId())
                .orElseThrow(()-> new RuntimeException("O nutricionista com este id não foi encontrado."));
        consulta.setNutricionistaEntity(nutricionista);

        consulta = consultaRepository.save(consulta);
        return converterEntidadeParaResponseDTO(consulta);
    }

    public List<ConsultaResponseDTO> listarTodasConsultas () {
        List<ConsultaEntity> consultas = consultaRepository.findAll();
        return consultas.stream().map(this::converterEntidadeParaResponseDTO)
                .collect(Collectors.toList());
    }

    public ConsultaResponseDTO listarConsultaPorId (Long id) {
        ConsultaEntity consulta = consultaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("A consulta com este id não foi encontrada."));
        return converterEntidadeParaResponseDTO(consulta);
    }

    public ConsultaResponseDTO atualizarConsulta (Long id, ConsultaRequestDTO requestDTO) {
        ConsultaEntity consulta = consultaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("A consulta com este id não foi encontrada."));
        BeanUtils.copyProperties(requestDTO, consulta);

        PacienteEntity paciente = pacienteRepository.findById(requestDTO.getPacienteId())
                .orElseThrow(()-> new RuntimeException("O paciente com este id não foi encontrado."));
        consulta.setPacienteEntity(paciente);

        NutricionistaEntity nutricionista = nutricionistaRepository.findById(requestDTO.getNutricionistaId())
                .orElseThrow(()-> new RuntimeException("O nutricionista com este id não foi encontrado."));
        consulta.setNutricionistaEntity(nutricionista);

        consultaRepository.save(consulta);
        return converterEntidadeParaResponseDTO(consulta);
    }

    public void deletarConsulta (Long id) {
        ConsultaEntity consulta = consultaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("A consulta com este id não foi encontrada."));
        consultaRepository.delete(consulta);
    }

    private ConsultaResponseDTO converterEntidadeParaResponseDTO (ConsultaEntity consulta) {
        ConsultaResponseDTO consultaResponseDTO = new ConsultaResponseDTO();
        BeanUtils.copyProperties(consulta, consultaResponseDTO);
        return consultaResponseDTO;
    }
}
