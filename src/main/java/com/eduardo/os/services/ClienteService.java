package com.eduardo.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardo.os.domain.Cliente;
import com.eduardo.os.domain.Pessoa;
import com.eduardo.os.dtos.ClienteDTO;
import com.eduardo.os.repositories.ClienteRepository;
import com.eduardo.os.repositories.PessoaRepository;
import com.eduardo.os.services.exceptions.DataIntegratyViolationException;
import com.eduardo.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	/*
	 * Busca um cliente por ID
	 */
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id); // Usa findById de ClienteRepository p/ retornar um obj cliente

		// Retorna o cliente ou uma exceção se não for encontrado
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

	/*
	 * Retorna uma lista de clientes
	 */
	public List<Cliente> findAll() {
		return repository.findAll();
	}

	/*
	 * Cria um novo cliente
	 */
	public Cliente create(ClienteDTO objDTO) {

		// Se o CPF estiver cadastrado em cliente ou técnico retorna uma exceção
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		// Se o CPF não estiver cadastrado cria o novo cliente
		return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	/*
	 * Atualiza um cliente
	 */
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {

		// Verifica se o cliente existe, se não existir o próprio findById retorna uma
		// exceção
		Cliente oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());

		return repository.save(oldObj);
	}

	/*
	 * Deleta um cliente
	 */
	public void delete(Integer id) {
		Cliente obj = findById(id); // Verifica se o Id existe, se não existir gera uma exceção
		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Cliente possui Ordens de Serviço, não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	/*
	 * Busca Técnico pelo CPF
	 */
	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf()); // Busca o CPF em Técnico e cliente

		if (obj != null) {
			return obj;
		}
		return null;
	}
}
