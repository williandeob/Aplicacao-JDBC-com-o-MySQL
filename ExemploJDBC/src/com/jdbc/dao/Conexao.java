package com.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {
public Connection conexao;

public Conexao() {
	super();
    conectarBD();
}
private Connection conectarBD(){
	String url="jdbc:mysql://localhost/agenda";
	String user="root";
	String password="olivetti";
	try {
		conexao=DriverManager.getConnection(url, user, password);
	} catch (SQLException e) {
		String message="Erro: Não foi possivel conectar ao banco";
		JOptionPane.showMessageDialog(null, message);
		e.printStackTrace();	
	}
	return conexao;
}
public void desconectaBD(){
	try {
		conexao.close();
	} catch (SQLException e) {
		String message="Erro: Não foi possivel se desconectar do banco";
		JOptionPane.showMessageDialog(null, message);
		e.printStackTrace();
	}
}
public Connection getConexao() {
	return conexao;
}

}
