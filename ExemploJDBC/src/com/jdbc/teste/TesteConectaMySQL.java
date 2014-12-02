package com.jdbc.teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConectaMySQL {
	public static void main(String arg[]) {

		Connection conexao = null;

		String url = "jdbc:mysql://localhost/agenda";
		String usuario = "root";
		String senha = "olivetti";

		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Conectou!");
		} catch (SQLException e) {
			System.out
					.println("Ocorreu um erro ao tentar conectar com o banco. Erro: "
							+ e.getMessage());
		} finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar Conexao. Erro: "
						+ e.getMessage());
			}
		}
	}
}
