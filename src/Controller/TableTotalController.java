package Controller;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Dao.ApuracaoDaoImplementation;
import Entidades.Totais;

public class TableTotalController extends AbstractTableModel{

	private List<Totais> apuracao;

	private String []nomes={"Posi��o", "Escola de Samba", "Total"};

	public TableTotalController(List<Totais>apuracao){
		this.apuracao=apuracao;
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


	//N�o serve pra nada
	@Override
	public Object getValueAt(int linha, int coluna) {
		Object o=" ";


		if((coluna<=getColumnCount())){
			Totais a=apuracao.get(linha);

			if(coluna==0){

				if(linha==0){
					o="1�";
				}

				if(linha==1){
					o="2�";
				}

				if(linha==2){
					o="3�";
				}

				if(linha==3){
					o="4�";
				}

				if(linha==4){
					o="5�";
				}

				if(linha==5){
					o="6�";
				}


				if(linha==6){
					o="7�";
				}

				if(linha==7){
					o="8�";
				}


				if(linha==8){
					o="9�";
				}

				if(linha==9){
					o="10�";
				}

				if(linha==10){
					o="11�";
				}

				if(linha==11){
					o="12�";
				}


				if(linha==12){
					o="13�";
				}


				if(linha==13){
					o="14�";
				}
			}

			else


				if(coluna==1){
					o=a.getNome();
				}
				else
					o=a.getNota();
		}

		return o;

	}
}
