package com.jdbc.model;

import java.sql.Date;

public class Contato {
private int codigo;
private String nome, telefone, email, observacao;
private Date dataCadastro;
public Contato(String nome, String telefone, String email, Date dataCadastro, String observacao) {
	super();
	this.nome = nome;
	this.telefone = telefone;
	this.email = email;
	this.dataCadastro = dataCadastro;
	this.observacao=observacao;
	
}
public int getCodigo() {
	return codigo;
}
public void setCodigo(int codigo) {
	this.codigo = codigo;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getTelefone() {
	return telefone;
}
public void setTelefone(String telefone) {
	this.telefone = telefone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Date getDataCadastro() {
	return dataCadastro;
}
public void setDataCadastro(Date dataCadastro) {
	this.dataCadastro = dataCadastro;
}
public String getObservacao() {
	return observacao;
}
public void setObservacao(String observacao) {
	this.observacao = observacao;
}

}
