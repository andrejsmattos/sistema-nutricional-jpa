package br.senai.lab365.semana7.services;

import br.senai.lab365.semana7.controllers.dtos.NutricionistaRequestDTO;
import br.senai.lab365.semana7.controllers.dtos.NutricionistaResponseDTO;
import br.senai.lab365.semana7.entities.NutricionistaEntity;
import br.senai.lab365.semana7.repositories.NutricionistaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NutricionistaService {

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    private NutricionistaResponseDTO criarNutricionista (NutricionistaRequestDTO requestDTO) {
        NutricionistaEntity nutricionista = new NutricionistaEntity();
        nutricionista.setCRN(requestDTO.getCRN());
        nutricionista.setEspecialidade(requestDTO.getEspecialidade());
        nutricionistaRepository.save(nutricionista);
        return converterEntidadeParaResponseDTO(nutricionista);
    }

    private List<NutricionistaResponseDTO> listarTodosNutricionistas () {
        List<NutricionistaEntity> nutricionistas = nutricionistaRepository.findAll();
        return nutricionistas.stream()
                .map(this::converterEntidadeParaResponseDTO)
                .collect(Collectors.toList());
    }

    private NutricionistaResponseDTO listarNutricionistaPorId (Long id) {
        NutricionistaEntity nutricionista = nutricionistaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O nutricionista com este id não foi encontrado."));
        return converterEntidadeParaResponseDTO(nutricionista);
    }

    public NutricionistaResponseDTO atualizarNutricionista (Long id, NutricionistaRequestDTO requestDTO) {
        NutricionistaEntity nutricionista = nutricionistaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O nutricionista com este id não foi encontrado."));
        BeanUtils.copyProperties(requestDTO, nutricionista);
        nutricionistaRepository.save(nutricionista);
        return converterEntidadeParaResponseDTO(nutricionista);
    }

    public void deletarNutricionista(Long id) {
        NutricionistaEntity nutricionista = nutricionistaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O nutricionista com este id não foi encontrado."));
        nutricionistaRepository.delete(nutricionista);
    }

    public NutricionistaResponseDTO converterEntidadeParaResponseDTO (NutricionistaEntity nutricionista) {
        NutricionistaResponseDTO nutricionistaResponseDTO = new NutricionistaResponseDTO();
        BeanUtils.copyProperties(nutricionista, nutricionistaResponseDTO);
        return nutricionistaResponseDTO;
    }
}
