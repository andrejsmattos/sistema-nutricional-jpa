package br.senai.lab365.semana7.services;

import br.senai.lab365.semana7.controllers.dtos.EnderecoRequestDTO;
import br.senai.lab365.semana7.controllers.dtos.EnderecoResponseDTO;
import br.senai.lab365.semana7.entities.EnderecoEntity;
import br.senai.lab365.semana7.repositories.EnderecoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoResponseDTO criarEndereco (EnderecoRequestDTO requestDTO){
        EnderecoEntity endereco = new EnderecoEntity();
        endereco.setLogradouro(requestDTO.getLogradouro());
        endereco.setEstado(requestDTO.getEstado());
        endereco.setCidade(requestDTO.getCidade());
        endereco.setNumero(requestDTO.getNumero());
        endereco.setCep(requestDTO.getCep());
        enderecoRepository.save(endereco);
        return converterEntidadeparaResponseDTO(endereco);
    }

    public List<EnderecoResponseDTO> listarTodosEnderecos () {
        List<EnderecoEntity> enderecos = enderecoRepository.findAll();
            return enderecos.stream()
                    .map(this::converterEntidadeparaResponseDTO)
                    .collect(Collectors.toList());
    }

    public EnderecoResponseDTO listarEnderecoPorId (Long id) {
        EnderecoEntity endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("O endereço com este id não foi encontrado."));
        return converterEntidadeparaResponseDTO(endereco);
    }

    public EnderecoResponseDTO atualizarEndereco (Long id, EnderecoRequestDTO requestDTO){
        EnderecoEntity endereco = enderecoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O endereço com este id não foi encontrado."));
        BeanUtils.copyProperties(requestDTO, endereco);
        enderecoRepository.save(endereco);
        return converterEntidadeparaResponseDTO(endereco);
    }

    public void deletarEndereco (Long id) {
        EnderecoEntity endereco = enderecoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O endereço com este id não foi encontrado."));
        enderecoRepository.delete(endereco);
    }

    private EnderecoResponseDTO converterEntidadeparaResponseDTO(EnderecoEntity endereco){
        EnderecoResponseDTO enderecoResponseDto= new EnderecoResponseDTO();
        BeanUtils.copyProperties(endereco, enderecoResponseDto);
        return enderecoResponseDto;
    }

}
