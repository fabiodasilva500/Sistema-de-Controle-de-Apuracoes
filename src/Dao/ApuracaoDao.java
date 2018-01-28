package Dao;

import java.sql.SQLException;
import java.util.List;

import Entidades.Apuracao;
import Entidades.Escola;
import Entidades.Jurado;
import Entidades.Totais;

public interface ApuracaoDao {
	public boolean insereAvaliacao(Apuracao apuracao) throws ApuracaoDaoException;
	public List<Apuracao> consultaNotas(int id) throws ApuracaoDaoException;
	public List<Totais> buscarTotais() throws ApuracaoDaoException;
	public void reiniciarApuracao() throws SQLException;
	public List<Escola> ListaDeEscolas()  throws ApuracaoDaoException;
	public List<Jurado> ListaDeJurados() throws ApuracaoDaoException;
	public List<Jurado> ListaDeJuradosPosicao() throws ApuracaoDaoException;


}
