package ProjetoDeCasosDeTestes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Dao.AcessoLoginDaoException;
import Dao.AcessoLoginDaoImplementation;
import Entidades.Acesso;

public class UC02Acesso {
	private AcessoLoginDaoImplementation acesso;
	private Acesso a;


	@Before
	public void setUp() throws Exception {
		a=new Acesso();
		acesso=new AcessoLoginDaoImplementation();
	}


	@Test 
	public void CT02_insere_login_com_sucesso(){
		a.setCpf("40670491888");
		a.setNome("Fábio Pereira da Silva");
		a.setCargo("Repórter");
		a.setLogin("silva50");
		a.setSenha("123456");

		//notas abaixo de 6 não são aceitas
		try {
			assertTrue(acesso.InsereLogin(a));
		} catch (AcessoLoginDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Test 
	public void CT02_insere_login_sem_sucesso(){
		a.setCpf("40670491888");
		a.setNome("Fábio Pereira da Silva");
		a.setCargo("Repórter");
		a.setLogin("silva50");
		a.setSenha("123456");

		try {
			assertTrue(acesso.InsereLogin(a));
		} catch (AcessoLoginDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Test 
	public void CT02_insere_login_sem_sucesso_campos_vazios(){
		a.setCpf("40670491888");
		a.setNome("Fábio Pereira da Silva");
		a.setCargo("Repórter");
		a.setLogin(null);
		a.setSenha(null);

		try {
			assertTrue(acesso.InsereLogin(a));
		} catch (AcessoLoginDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	@Test 
	public void CT02_exclui_login_com_sucesso(){
		a.setCpf("40670491888");

		try {
			assertTrue(acesso.ExcluirLogin(a));
		} catch (AcessoLoginDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test 
	public void CT02_exclui_login_sem_sucesso(){
		a.setCpf("aaaaaa");

		try {
			assertTrue(acesso.ExcluirLogin(a));
		} catch (AcessoLoginDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Test 
	public void CT02_exclui_login_sem_sucesso_campos_vazios(){
		a.setCpf(null);

		try {
			assertTrue(acesso.ExcluirLogin(a));
		} catch (AcessoLoginDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Test 
	public void CT02_atualizar_login_com_sucesso(){
		a.setCpf("40670491888");

		try {
			assertTrue(acesso.AtualizarLogin(a));
		} catch (AcessoLoginDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test 
	public void CT02_atualizar_login_sem_sucesso(){
		a.setCpf("aaaaaa");

		try {
			assertTrue(acesso.AtualizarLogin(a));
		} catch (AcessoLoginDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Test 
	public void CT02_atualizar_login_sem_sucesso_campos_vazios(){
		a.setCpf(null);

		try {
			assertTrue(acesso.AtualizarLogin(a));
		} catch (AcessoLoginDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}







	}



	@After
	public void tearDown() throws Exception {
	}


}
