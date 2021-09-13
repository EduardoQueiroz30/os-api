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
		Tecnico t3 = new Tecnico(null, "Otavio Mesquita", "209.123.760-40", "(13) 98888-0003");
		Tecnico t4 = new Tecnico(null, "Celso Lima", "567.481.850-92", "(13) 98998-0004");
		Tecnico t5 = new Tecnico(null, "Mario de Andrade", "857.579.800-60", "(13) 98888-0005");
		Tecnico t6 = new Tecnico(null, "Maria Rita", "249.628.690-27", "(13) 98998-0006");
		Cliente c1 = new Cliente(null, "José Roberto", "193.693.850-29", "(13) 95588-0007");
		Cliente c2 = new Cliente(null, "Maria José", "919.284.090-67", "(13) 95500-0008");
		Cliente c3 = new Cliente(null, "Roberto Carlos", "243.148.370-39", "(13) 95588-0009");
		Cliente c4 = new Cliente(null, "Silvio Santos", "970.797.080-44", "(13) 95500-0010");
		Cliente c5 = new Cliente(null, "Caio Siqueira", "775.701.780-76", "(13) 95588-0011");
		Cliente c6 = new Cliente(null, "Mario Roberto", "042.349.680-80", "(13) 95500-0012");
		
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS 1 ", Status.ANDAMENTO, t1, c1);
		OS os2 = new OS(null, Prioridade.BAIXA, "Teste create OS 2 ", Status.ABERTO, t2, c2);
		OS os3 = new OS(null, Prioridade.MEDIA, "Teste create OS 3 ", Status.ENCERRADO, t3, c3);
		OS os4 = new OS(null, Prioridade.ALTA, "Teste create OS 4 ", Status.ANDAMENTO, t4, c4);
		OS os5 = new OS(null, Prioridade.BAIXA, "Teste create OS 5 ", Status.ABERTO, t5, c5);
		OS os6 = new OS(null, Prioridade.MEDIA, "Teste create OS6 ", Status.ENCERRADO, t6, c6);

		t1.getList().add(os1);
		c1.getList().add(os1);
		t2.getList().add(os2);
		c2.getList().add(os2);
		t3.getList().add(os3);
		c3.getList().add(os3);
		t4.getList().add(os4);
		c4.getList().add(os4);
		t5.getList().add(os5);
		c5.getList().add(os5);
		t6.getList().add(os6);
		c6.getList().add(os6);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5, t6));
		clienteReepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
		osRepository.saveAll(Arrays.asList(os1, os2, os3, os4, os5, os6));
	}

}
