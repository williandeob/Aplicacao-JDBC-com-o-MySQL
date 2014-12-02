package com.jdbc.teste;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.jdbc.dao.ContatoDAO;
import com.jdbc.model.Contato;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteContatoDAO {
	Contato contato;
	ContatoDAO contatoDAO;
	@Before
	public void setUp() throws Exception {
		Date dataCadastro = new Date(System.currentTimeMillis());
		contato = new Contato("nome", "telefone", "email", dataCadastro,
				"observação");
		contatoDAO = new ContatoDAO();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void test1Salvar() {
		contatoDAO.salvar(contato);
		List<Contato> listaDeContatos = contatoDAO.listar();
		for (int i = 0; i < listaDeContatos.size(); i++) {
			if ((listaDeContatos.get(i).getNome().equals(contato.getNome())) && 
					(listaDeContatos.get(i).getTelefone().equals(contato.getTelefone())) && 
					(listaDeContatos.get(i).getEmail().equals(contato.getEmail()))) {
				
				assertEquals(contato.getNome(), listaDeContatos.get(i)
						.getNome());
				assertEquals(contato.getTelefone(), listaDeContatos.get(i)
						.getTelefone());
				assertEquals(contato.getEmail(), listaDeContatos.get(i)
						.getEmail());
			//	assertEquals(contato.getDataCadastro(),
				//		listaDeContatos.get(i).getDataCadastro());
				assertEquals(contato.getObservacao(), listaDeContatos.get(i)
						.getObservacao());
				break;
			}
		}
	}

	@Test
	public void test2Listar() {
		assertEquals(contatoDAO.contarNumerosDeTuplas(), contatoDAO.listar()
				.size());
	}

	@Test
	public void test3BuscarContatoPeloNome() {
		assertEquals(contato.getNome(), contatoDAO.buscarContato(contato.getNome()).getNome());
		assertEquals(contato.getTelefone(), contatoDAO.buscarContato(contato.getNome()).getTelefone());
		assertEquals(contato.getEmail(), contatoDAO.buscarContato(contato.getNome()).getEmail());
		assertEquals(contato.getObservacao(),contatoDAO.buscarContato(contato.getNome()).getObservacao());
		}
	
	@Test
	public void test4DeletarContato(){
		Contato contato=new Contato(contatoDAO.listar().get(0).getNome(), contatoDAO.listar().get(0).getTelefone(),
				contatoDAO.listar().get(0).getEmail(), contatoDAO.listar().get(0).getDataCadastro(),
				contatoDAO.listar().get(0).getObservacao());
		contato.setCodigo(contatoDAO.listar().get(0).getCodigo());
		contatoDAO.excluir(contatoDAO.listar().get(0));
		assertNull(contatoDAO.buscarContato(contato.getNome()));

	}

}
