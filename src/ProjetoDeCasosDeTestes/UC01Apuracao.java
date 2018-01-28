package ProjetoDeCasosDeTestes;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import Dao.ApuracaoDaoImplementation;
import Dao.ApuracaoDaoException;
import Entidades.Apuracao;

public class UC01Apuracao {
	private ApuracaoDaoImplementation apuracao;
	private Apuracao a;


	@Before
	public void setUp() throws Exception {
		apuracao=new ApuracaoDaoImplementation();
		a=new Apuracao();
	}


	@Test
	public void CT01_valida_avaliacao_com_sucesso(){
		a.setIDEscola(1);
		a.setIDJurado(1);
		a.setPosicaoJurado(1);
		a.setIDQuesito(1);
		a.setNota(7);
		try {
			assertTrue(apuracao.insereAvaliacao(a));
		} catch (ApuracaoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test 
	public void CT01_valida_avaliacao_sem_sucesso_nota_com_caracteres(){
		a.setIDEscola(1);
		a.setIDJurado(1);
		a.setPosicaoJurado(1);
		a.setIDQuesito(2);
		a.setNota(Float.parseFloat("a"));              //notas abaixo de 6 não são aceitas
		try {
			assertTrue(apuracao.insereAvaliacao(a));
		} catch (ApuracaoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test 
	public void CT01_valida_avaliacao_sem_sucesso_dados_ja_cadastrado(){
		a.setIDEscola(1);
		a.setIDJurado(1);
		a.setPosicaoJurado(1);
		a.setIDQuesito(1);
		a.setNota(2);              //notas abaixo de 6 não são aceitas
		try {
			assertTrue(apuracao.insereAvaliacao(a));
		} catch (ApuracaoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test 
	public void CT01_valida_avaliacao_sem_sucesso_dados_inexistentes(){
		a.setIDEscola(50);
		a.setIDJurado(50);
		a.setPosicaoJurado(50);
		a.setIDQuesito(100);
		a.setNota(Float.parseFloat("a"));              //notas abaixo de 6 não são aceitas
		try {
			assertTrue(apuracao.insereAvaliacao(a));
		} catch (ApuracaoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@After
	public void tearDown() throws Exception {

	}



}
