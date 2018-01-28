package Dao;

import Entidades.Acesso;

public interface AcessoLoginDao {
	public boolean InsereLogin(Acesso login) throws AcessoLoginDaoException;
	public boolean AtualizarLogin(Acesso login) throws AcessoLoginDaoException;
	public boolean ExcluirLogin(Acesso login) throws AcessoLoginDaoException;
	public Acesso consultaLogin(Acesso login) throws AcessoLoginDaoException;
	public Acesso consultaDados(Acesso login) throws AcessoLoginDaoException;


}
