package br.senai.lab365.semana7.services;

import br.senai.lab365.semana7.controllers.dtos.FuncionarioRequestDTO;
import br.senai.lab365.semana7.controllers.dtos.FuncionarioResponseDTO;
import br.senai.lab365.semana7.entities.EnderecoEntity;
import br.senai.lab365.semana7.entities.FuncionarioEntity;
import br.senai.lab365.semana7.repositories.EnderecoRepository;
import br.senai.lab365.semana7.repositories.FuncionarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public FuncionarioResponseDTO criarFuncionario (FuncionarioRequestDTO requestDTO) {
        FuncionarioEntity funcionario = new FuncionarioEntity();
        BeanUtils.copyProperties(requestDTO, funcionario);

        EnderecoEntity endereco = enderecoRepository.findById(requestDTO.getEnderecoId())
                .orElseThrow(()->new RuntimeException("O endereço com este id não foi encontrado."));
        funcionario = funcionarioRepository.save(funcionario);
        return converterEntidadeParaResponseDTO(funcionario);
    }

    public List<FuncionarioResponseDTO> listarTodosFuncionarios () {
        List<FuncionarioEntity> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream().map(this::converterEntidadeParaResponseDTO)
                .collect(Collectors.toList());
    }

    public FuncionarioResponseDTO listarFuncionarioPorId (Long id) {
        FuncionarioEntity funcionario = funcionarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O funcionário com este id não foi encontrado."));
        return converterEntidadeParaResponseDTO(funcionario);
    }

    public FuncionarioResponseDTO atualizarFuncionario (Long id, FuncionarioRequestDTO requestDTO) {
        FuncionarioEntity funcionario = funcionarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O funcionário com este id não foi encontrado."));
        BeanUtils.copyProperties(requestDTO, funcionario);
        funcionarioRepository.save(funcionario);
        return converterEntidadeParaResponseDTO(funcionario);
    }

    public void deletarFuncionario (Long id) {
        FuncionarioEntity funcionario = funcionarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O funcionário com este id não foi encontrado."));
        funcionarioRepository.delete(funcionario);
    }

    private FuncionarioResponseDTO converterEntidadeParaResponseDTO(FuncionarioEntity funcionario){
        FuncionarioResponseDTO funcionarioResponseDto= new FuncionarioResponseDTO();
        BeanUtils.copyProperties(funcionario, funcionarioResponseDto);
        return funcionarioResponseDto;
    }
}
