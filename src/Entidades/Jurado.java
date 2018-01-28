package Entidades;

public class Jurado {
	private int ID;
	private String nome;
	private String quesito;
	private int posicao;




	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getQuesito() {
		return quesito;
	}
	public void setQuesito(String quesito) {
		this.quesito = quesito;
	}



	public int getPosicao() {
		return posicao;
	}
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

}
