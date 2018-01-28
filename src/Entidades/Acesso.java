package Entidades;

public class Acesso {
	private String login_usuario;
	private String senha;
	private String cargo;
	private int  IDAcesso;
	private String cpf;
	private String nome;



	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getCargo(){
		return cargo;
	}

	public void setCargo(String cargo){
		this.cargo=cargo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}




	public int getIDAcesso() {
		return IDAcesso;
	}

	public void setIDAcesso(int iDAcesso) {
		IDAcesso = iDAcesso;
	}


	public String getLogin() {
		return login_usuario;
	}

	public void setLogin(String login_usuario) {
		this.login_usuario = login_usuario;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}


}
