package Controller;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Dao.ApuracaoDaoImplementation;
import Entidades.Totais;

public class TableTotalController extends AbstractTableModel{

	private List<Totais> apuracao;

	private String []nomes={"Posição", "Escola de Samba", "Total"};

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


	//Não serve pra nada
	@Override
	public Object getValueAt(int linha, int coluna) {
		Object o=" ";


		if((coluna<=getColumnCount())){
			Totais a=apuracao.get(linha);

			if(coluna==0){

				if(linha==0){
					o="1ª";
				}

				if(linha==1){
					o="2ª";
				}

				if(linha==2){
					o="3ª";
				}

				if(linha==3){
					o="4ª";
				}

				if(linha==4){
					o="5ª";
				}

				if(linha==5){
					o="6ª";
				}


				if(linha==6){
					o="7ª";
				}

				if(linha==7){
					o="8ª";
				}


				if(linha==8){
					o="9ª";
				}

				if(linha==9){
					o="10ª";
				}

				if(linha==10){
					o="11ª";
				}

				if(linha==11){
					o="12ª";
				}


				if(linha==12){
					o="13ª";
				}


				if(linha==13){
					o="14ª";
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
