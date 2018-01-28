package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import Entidades.Apuracao;
import Entidades.Escola;
import Entidades.Jurado;
import Entidades.Totais;

public class ApuracaoDaoImplementation implements ApuracaoDao {
	JTDSUtil conexao = new JTDSUtil();
	Connection c = conexao.getConnection();

	public boolean insereAvaliacao(Apuracao apuracao) throws ApuracaoDaoException{
		boolean inserido = false;
		String sql = "{call insereAvaliacao(?,?,?,?,?)}";
		try {
			CallableStatement cs = c.prepareCall(sql);
			cs.setInt(1, apuracao.getIDEscola());
			cs.setInt(2, apuracao.getIDJurado());
			cs.setInt(3, apuracao.getPosicaoJurado());
			cs.setInt(4, apuracao.getIDQuesito());
			cs.setFloat(5, apuracao.getNota());
			cs.execute();
			cs.close();
			inserido = true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Atenção",JOptionPane.CANCEL_OPTION);
		}

		return inserido;
	}


	public List<Apuracao> consultaNotas(int id) throws ApuracaoDaoException{
		List<Apuracao> notas=new ArrayList<Apuracao>();
		String sql = "{call consultaNotas(?)}";
		try {
			CallableStatement cs = c.prepareCall(sql);
			cs.setInt(1, id);


			ResultSet rs = cs.executeQuery();

			while(rs.next()){
				Apuracao a=new Apuracao();
				a.setNota(rs.getFloat("nota1"));
				a.setNota2(rs.getFloat("nota2"));
				a.setNota3(rs.getFloat("nota3"));
				a.setNota4(rs.getFloat("nota4"));
				a.setNota5(rs.getFloat("nota5"));
				a.setMenorNota(rs.getFloat("nota5"));
				a.setMenorNota(rs.getFloat("menor_nota"));
				a.setMaiorNota(rs.getFloat("maior_nota"));
				a.setTotal(rs.getFloat("total"));

				notas.add(a);
			}
			cs.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Atenção",JOptionPane.CANCEL_OPTION);
		}

		return notas;
	}



	public List<Totais> buscarTotais() throws ApuracaoDaoException{
		List<Totais> notas=new ArrayList<Totais>();
		String sql = "Select escola, total_obtido from ValoresTotais order by total_obtido desc";
		try {

			PreparedStatement ps=c.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				Totais a=new Totais();
				a.setNome(rs.getString("escola"));
				a.setNota(rs.getFloat("total_obtido"));

				notas.add(a);
			}
			rs.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Atenção",JOptionPane.CANCEL_OPTION);
		}

		return notas;
	}


	public void reiniciarApuracao() throws SQLException{

		String sql = "{call reinicializaApuracao()}";
		try{

			PreparedStatement ps=c.prepareStatement(sql);
			ps.execute();	
			ps.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,e.getMessage(),"Atenção",JOptionPane.CANCEL_OPTION);
		}





	}





	public List<Escola> ListaDeEscolas()  throws ApuracaoDaoException{
		List<Escola> listaEscola=new ArrayList<Escola>();

		String sql="Select ID, nome from escola";

		try{
			PreparedStatement ps=c.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				Escola escola=new Escola();
				escola.setID(rs.getInt("ID"));
				escola.setNome(rs.getString("nome"));
				escola.setNome(rs.getString("nome"));

				listaEscola.add(escola);
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"Erro na geração da lista de Escolas", "Atenção", JOptionPane.INFORMATION_MESSAGE);
			throw new ApuracaoDaoException("Erro na geração da lista de escolas");

		}

		return listaEscola;
	}


	public List<Jurado> ListaDeJurados() throws ApuracaoDaoException {
		List<Jurado> listaJurado=new ArrayList<Jurado>();

		String sql="Select ID, nome, quesito from jurado";

		try{
			PreparedStatement ps=c.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				Jurado jurado=new Jurado();
				jurado.setID(rs.getInt("ID"));
				jurado.setNome(rs.getString("nome"));
				jurado.setQuesito(rs.getString("quesito"));
				listaJurado.add(jurado);
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"Erro na geração da lista de Jurados", "Atenção", JOptionPane.INFORMATION_MESSAGE);
			throw new ApuracaoDaoException("Erro na geração da lista de jurados");
		}

		return listaJurado;
	}


	public List<Jurado> ListaDeJuradosPosicao() throws ApuracaoDaoException {
		List<Jurado> listaJurado=new ArrayList<Jurado>();

		String sql="Select posicao from jurado";

		try{
			PreparedStatement ps=c.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				Jurado jurado=new Jurado();
				jurado.setPosicao(rs.getInt("posicao"));
				listaJurado.add(jurado);
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"Erro na geração da lista de Jurados", "Atenção", JOptionPane.INFORMATION_MESSAGE);
			throw new ApuracaoDaoException("Erro na geração da lista de jurados");
		}

		return listaJurado;
	}
}



