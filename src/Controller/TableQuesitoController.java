package Controller;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Entidades.Apuracao;
import Entidades.Escola;

public class TableQuesitoController extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Apuracao>apuracao;
	private List<Escola>escola;


	private String []nomes={"Escola de Samba", "Nota 1", "Nota 2","Nota 3", "Nota 4", "Nota 5", "Menor Nota", "Maior Nota", "Total"};

	public TableQuesitoController(List<Escola> escola, List<Apuracao>apuracao){
		this.apuracao=apuracao;
		this.escola=escola;
	}

	@Override
	public int getColumnCount() {
		return nomes.length;
	}

	public String getColumnName(int i){
		return nomes[i];
	}

	@Override
	public int getRowCount() {
		return apuracao.size();
	}


	//Não serve pra nada
	@Override
	public Object getValueAt(int linha, int coluna) {
		Object o=" ";


		if((coluna<=getColumnCount())){
			Apuracao a=apuracao.get(linha);
			Escola e=escola.get(linha);


			if(coluna==0){
				o= e.getNome();
			}
			else
				if(coluna==1){
					o=a.getNota();
				}
				else
					if(coluna==2){
						o=a.getNota2();
					}
					else
						if(coluna==3){
							o=a.getNota3();
						}
						else
							if(coluna==4){
								o=a.getNota4();
							}
							else
								if(coluna==5){
									o=a.getNota5();
								}
								else
									if(coluna==6){
										o=a.getMenorNota();
									}
									else
										if(coluna==7){
											o=a.getMaiorNota();
										}
										else
											o=a.getTotal();
		}

		return o;

	}
	

	      
	
	    
}
