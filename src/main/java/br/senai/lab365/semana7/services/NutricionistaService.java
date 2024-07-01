package br.senai.lab365.semana7.services;

import br.senai.lab365.semana7.controllers.dtos.NutricionistaRequestDTO;
import br.senai.lab365.semana7.controllers.dtos.NutricionistaResponseDTO;
import br.senai.lab365.semana7.entities.FuncionarioEntity;
import br.senai.lab365.semana7.entities.NutricionistaEntity;
import br.senai.lab365.semana7.repositories.NutricionistaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NutricionistaService extends FuncionarioService {

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    public NutricionistaResponseDTO criarNutricionista(NutricionistaRequestDTO requestDTO) {
        boolean nomeExiste = nutricionistaRepository.existsByNome(requestDTO.getNome());
        if (nomeExiste) {
            throw new RuntimeException("O nutricionista com esse nome já está registrado.");
        }

        NutricionistaEntity nutricionista = new NutricionistaEntity();
        BeanUtils.copyProperties(requestDTO, nutricionista);
        nutricionistaRepository.save(nutricionista);
        return converterNutricionistaParaResponseDTO(nutricionista); // Corrigido para chamar o método correto
    }

    public List<NutricionistaResponseDTO> listarTodosNutricionistas () {
        List<NutricionistaEntity> nutricionistas = nutricionistaRepository.findAll();
        return nutricionistas.stream()
                .map(this::converterNutricionistaParaResponseDTO)
                .collect(Collectors.toList());
    }

    public NutricionistaResponseDTO listarNutricionistaPorId (Long id) {
        NutricionistaEntity nutricionista = nutricionistaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O nutricionista com este id não foi encontrado."));
        return converterNutricionistaParaResponseDTO(nutricionista);
    }

    public NutricionistaResponseDTO atualizarNutricionista (Long id, NutricionistaRequestDTO requestDTO) {
        NutricionistaEntity nutricionista = nutricionistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("O nutricionista com este id não foi encontrado."));

        boolean nomeExistente = nutricionistaRepository.existsByNome(requestDTO.getNome());
        if (nomeExistente && !nutricionista.getNome().equals(requestDTO.getNome())) {
            throw new RuntimeException("Já existe um nutricionista com este nome.");
        }

        BeanUtils.copyProperties(requestDTO, nutricionista);
        nutricionistaRepository.save(nutricionista);
        return converterNutricionistaParaResponseDTO(nutricionista);
    }

    public void deletarNutricionista(Long id) {
        NutricionistaEntity nutricionista = nutricionistaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O nutricionista com este id não foi encontrado."));
        nutricionistaRepository.delete(nutricionista);
    }

    public void addAnoAoTempoExperiencia (Long id) {
        NutricionistaEntity nutricionista = nutricionistaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O nutricionista com este id não foi encontrado."));
        nutricionista.setTempoExperiencia(nutricionista.getTempoExperiencia() + 1);
        nutricionistaRepository.save(nutricionista);
    }

    public void adicionarCertificacao(Long id, String certificacao) {
        NutricionistaEntity nutricionista = nutricionistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("O nutricionista com este id não foi encontrado."));

        if (!nutricionista.getCertificacoes().isEmpty()) {
            nutricionista.setCertificacoes(nutricionista.getCertificacoes() + ", " + certificacao);
        } else {
            nutricionista.setCertificacoes(certificacao);
        }

        nutricionistaRepository.save(nutricionista);
    }

    @Override
    protected NutricionistaResponseDTO converterNutricionistaParaResponseDTO(FuncionarioEntity funcionario) {
        NutricionistaResponseDTO nutricionistaResponseDTO = new NutricionistaResponseDTO();
        BeanUtils.copyProperties(funcionario, nutricionistaResponseDTO);
        return nutricionistaResponseDTO;
    }
}
