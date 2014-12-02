package com.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.jdbc.model.Contato;

public class ContatoDAO {
	public void salvar(Contato contato) {
		Conexao conexao = new Conexao();
		PreparedStatement salvarSt = null;
		String sql = "insert into contato(nome,telefone,email,dt_cad,obs)values(?,?,?,?,?)";

		try {
			salvarSt = conexao.getConexao().prepareStatement(sql);
			salvarSt.setString(1, contato.getNome());
			salvarSt.setString(2, contato.getTelefone());
			salvarSt.setString(3, contato.getEmail());
			salvarSt.setDate(4, contato.getDataCadastro());
			salvarSt.setString(5, contato.getObservacao());
			salvarSt.executeUpdate();
		} catch (SQLException e) {
			String message = "Erro: Não foi possivel inserir o contato";
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
		} finally {
			try {
				salvarSt.close();
			} catch (SQLException e) {
				String message = "Erro ao fechar a operação de inserção do contato";
				JOptionPane.showMessageDialog(null, message);
				e.printStackTrace();
			}
			conexao.desconectaBD();
		}
		{
		}

	}

	public void identificarCamposASeremAlterados(Contato contato) {
		String guardaADataDoObjetoEmTexto;
		DateFormat dateformat;
		contato.setNome(JOptionPane.showInputDialog(null, "Alterar Nome",
				contato.getNome()));
		contato.setTelefone(JOptionPane.showInputDialog(null,
				"Altera Telefone", contato.getTelefone()));
		contato.setEmail(JOptionPane.showInputDialog(null, "Altera Email",
				contato.getEmail()));

		dateformat = new SimpleDateFormat("yyyy-MM-dd");
		guardaADataDoObjetoEmTexto = JOptionPane.showInputDialog(null,
				"Altera Data", contato.getDataCadastro());
		try {
			Date guardaADataDoObjetoEmDate = new Date(dateformat.parse(
					guardaADataDoObjetoEmTexto).getTime());
			contato.setDataCadastro(guardaADataDoObjetoEmDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		contato.setObservacao(JOptionPane.showInputDialog(null,
				"Altera Observação", contato.getObservacao()));
		
	}

	public void atualizar(Contato contato) {
		Conexao conexao = new Conexao();
		PreparedStatement updateStm = null;
		identificarCamposASeremAlterados(contato);
		String sql = "update contato set nome='"+contato.getNome()+"', telefone='"+contato.getTelefone()+"',"
				+ "email='"+contato.getEmail()+"', dt_cad="+contato.getDataCadastro()+", obs='"+contato.getObservacao()+"'" +
						"where codigo="+contato.getCodigo()+";";
		try {
			updateStm = conexao.getConexao().prepareStatement(sql);
		/*	updateStm.setString(1, contato.getNome());
			updateStm.setString(2, contato.getTelefone());
			updateStm.setString(3, contato.getEmail());
			updateStm.setDate(4, contato.getDataCadastro());
			updateStm.setString(5, contato.getObservacao());
			updateStm.setInt(6, contato.getCodigo());
*/
			updateStm.executeUpdate();
		} catch (SQLException e) {
			JOptionPane
					.showMessageDialog(null,
							"Erro: Não foi possivel atualizar o contato, erro de Statement!");
			e.printStackTrace();
		} finally {
			try {
				updateStm.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Erro: Erro ao fechar Statement!");
				e.printStackTrace();
			}
			conexao.desconectaBD();
		}
	}

	public void excluir(Contato contato) {
		Conexao conexao = new Conexao();
		PreparedStatement excluirStm = null;
		String sql = "delete from contato where codigo=?";

		try {
			excluirStm = conexao.getConexao().prepareStatement(sql);
			excluirStm.setInt(1, contato.getCodigo());
			excluirStm.executeUpdate();

		} catch (SQLException e) {
			String message = "Erro: Não foi possivel excluir o contato"
					+ contato.getNome();
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();

		} finally {
			try {
				excluirStm.close();
			} catch (SQLException e) {
				String message = "Erro ao fechar a operação de exclusão do contato";
				JOptionPane.showMessageDialog(null, message);
				e.printStackTrace();
			}
			conexao.desconectaBD();
		}
	}

	public List<Contato> listar() {
		List<Contato> contatos = new ArrayList<Contato>();
		Conexao conexao = new Conexao();
		Statement consulta = null;
		ResultSet resultado = null;
		Contato contato = null;

		String sql = "select * from contato";
		try {
			consulta = conexao.getConexao().createStatement();
			resultado = consulta.executeQuery(sql);

			while (resultado.next()) {
				contato = new Contato(resultado.getString("nome"),
						resultado.getString("telefone"),
						resultado.getString("email"),
						resultado.getDate("dt_cad"), resultado.getString("obs"));
				contato.setCodigo(resultado.getInt("codigo"));
				contatos.add(contato);
			}
		} catch (SQLException e) {
			String message = "Erro: Não foi possivel listar os contatos";
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexao.desconectaBD();
			} catch (SQLException e) {
				String message = "Erro: Houve falhas o fechar a consulta de contatos";
				JOptionPane.showMessageDialog(null, message);
				e.printStackTrace();
			}

		}

		return contatos;
	}

	public Contato buscarContato(int codigoUsuario) {
		Conexao conexao = new Conexao();
		Statement consulta = null;
		ResultSet resultado = null;
		Contato contato = null;
		String sql = "select * from contato where codigo=" + codigoUsuario
				+ ";";

		try {
			consulta = conexao.getConexao().createStatement();
			resultado = consulta.executeQuery(sql);
			contato = new Contato(resultado.getString("nome"),
					resultado.getString("telefone"),
					resultado.getString("email"), resultado.getDate("dt_cad"),
					resultado.getString("obs"));
			contato.setCodigo(resultado.getInt("codigo"));

		} catch (SQLException e) {
			String message = "Erro: Não foi possivel encontrar o contato de codigo "
					+ codigoUsuario;
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
			return contato;
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.desconectaBD();
			} catch (SQLException e) {
				String message = "Erro: ao fechar opreação de busca";
				JOptionPane.showMessageDialog(null, message);
				e.printStackTrace();
			}
		}

		return contato;
	}

	public Contato buscarContato(String nome) {
		Conexao conexao = new Conexao();
		Statement consulta = null;
		ResultSet resultado = null;
		Contato contato = null;
		String sql = "select * from contato where nome='" + nome + "';";

		try {
			consulta = conexao.getConexao().createStatement();
			resultado = consulta.executeQuery(sql);

			while (resultado.next()) {
				contato = new Contato(resultado.getString("nome"),
						resultado.getString("telefone"),
						resultado.getString("email"),
						resultado.getDate("dt_cad"), resultado.getString("obs"));
				contato.setCodigo(resultado.getInt("codigo"));
			}

		} catch (SQLException e) {
			String message = "Erro: Não foi possivel encontrar o contato "
					+ nome;
			JOptionPane.showMessageDialog(null, message);
			e.printStackTrace();
			return contato;
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.desconectaBD();
			} catch (SQLException e) {
				String message = "Erro: ao fechar opreação de busca";
				JOptionPane.showMessageDialog(null, message);
				e.printStackTrace();
			}

		}

		return contato;
	}

	public int contarNumerosDeTuplas() {
		return listar().size();
	}

}
