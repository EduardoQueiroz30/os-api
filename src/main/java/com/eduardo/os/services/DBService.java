package com.eduardo.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardo.os.domain.Cliente;
import com.eduardo.os.domain.OS;
import com.eduardo.os.domain.Tecnico;
import com.eduardo.os.domain.enuns.Prioridade;
import com.eduardo.os.domain.enuns.Status;
import com.eduardo.os.repositories.ClienteRepository;
import com.eduardo.os.repositories.OSRepository;
import com.eduardo.os.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteReepository;
	@Autowired
	private OSRepository osRepository;

	public void instaciaDB() {

		Tecnico t1 = new Tecnico(null, "Eduardo Lima", "854.875.900-36", "(13) 98888-0909");
		Tecnico t2 = new Tecnico(null, "Linus Silva", "761.832.850-13", "(13) 98998-0908");
		Cliente c1 = new Cliente(null, "José Roberto", "193.693.850-29", "(13) 95588-0909");
		Cliente c2 = new Cliente(null, "Maria José", "919.284.090-67", "(13) 95500-0709");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteReepository.saveAll(Arrays.asList(c1, c2));
		osRepository.saveAll(Arrays.asList(os1));
	}

}
