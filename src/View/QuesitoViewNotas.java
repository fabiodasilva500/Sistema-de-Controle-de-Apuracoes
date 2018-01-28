package View;


import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import Controller.TableQuesitoController;
import Dao.ApuracaoDaoImplementation;
import Dao.ApuracaoDaoException;
import Entidades.Apuracao;
import Entidades.Escola;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class QuesitoViewNotas extends JFrame{


	private JPanel contentPane;
	private JTable table;
	private JTextField tfQuesito;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuesitoViewNotas frame = new QuesitoViewNotas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public QuesitoViewNotas() {
		super("Quesito atual");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);



		table = new JTable();


		contentPane.add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(28, 94, 730, 385);
		contentPane.add(scrollPane);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 64, 788, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 509, 788, 2);
		contentPane.add(separator_1);

		JLabel lblQuesitoAtual = new JLabel("Quesito:");
		lblQuesitoAtual.setBounds(36, 31, 97, 14);
		contentPane.add(lblQuesitoAtual);

		tfQuesito = new JTextField();
		tfQuesito.setBounds(130, 28, 628, 20);
		contentPane.add(tfQuesito);
		tfQuesito.setColumns(10);
		tfQuesito.setEnabled(false);
		setVisible(true);

	}


	public void pesquisar(int quesito){
		ApuracaoDaoImplementation aDAO=new ApuracaoDaoImplementation();


		List<Escola> escola=new ArrayList<Escola>();
		try {


			escola=aDAO.ListaDeEscolas();
		} catch (ApuracaoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Apuracao> a=new ArrayList<Apuracao>();
		try {
			a=aDAO.consultaNotas(quesito);

		} catch (ApuracaoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		updateTabelaEscolaQuesito(escola, a);
		apresentaQuesito(quesito);
	}





	public void updateTabelaEscolaQuesito(List<Escola> e, List<Apuracao> f){
		TableQuesitoController escolaTabela=new TableQuesitoController(e,f);
		table.setModel(escolaTabela);

		table.setAutoResizeMode(table.AUTO_RESIZE_OFF);  
		table.getColumnModel().getColumn(0).setPreferredWidth(140);  

		table.repaint();
	}



	public void apresentaQuesito(int quesito) {
		if(quesito==1){
			tfQuesito.setText("Comissão de Frente");
		}
		else
			if(quesito==2){
				tfQuesito.setText("Evolução");
			}
			else
				if(quesito==3){
					tfQuesito.setText("Fantásia");
				}
				else
					if(quesito==4){
						tfQuesito.setText("Bateria");
					}
					else
						if(quesito==5){
							tfQuesito.setText("Alegoria");
						}
						else
							if(quesito==6){
								tfQuesito.setText("Harmonia");
							}
							else
								if(quesito==7){
									tfQuesito.setText("Samba Enredo");
								}
								else
									if(quesito==8){
										tfQuesito.setText("Mestre Sala e Porta Bandeira");
									}
									else
										if(quesito==9){
											tfQuesito.setText("Enredo");
										}

	}
}








