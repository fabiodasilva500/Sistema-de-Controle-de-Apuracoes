package View;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import Controller.TableTotalController;
import Dao.ApuracaoDaoImplementation;
import Dao.ApuracaoDaoException;
import Entidades.Apuracao;
import Entidades.Escola;
import Entidades.Totais;
import javax.swing.JSeparator;

import java.awt.EventQueue;
import javax.swing.JLabel;


public class TotalViewNotas extends JFrame{


	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			Apuracao apuracao=null;
			public void run() {
				try {
					TotalViewNotas frame = new TotalViewNotas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TotalViewNotas() {
		super("Acompanhamento parcial e final da apuração");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);



		table = new JTable();


		contentPane.add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(48, 94, 693, 385);
		contentPane.add(scrollPane);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 64, 788, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 509, 788, 2);
		contentPane.add(separator_1);
		
		JLabel lblDesfileDasCampees = new JLabel("Desfile das Campe\u00F5es:");
		lblDesfileDasCampees.setBounds(48, 23, 144, 14);
		contentPane.add(lblDesfileDasCampees);
		
		JLabel lblNewLabel = new JLabel("1\u00AA \u00E1 5\u00AA");
		lblNewLabel.setBounds(204, 23, 76, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblRebaixadas = new JLabel("Rebaixadas:");
		lblRebaixadas.setBounds(323, 23, 76, 14);
		contentPane.add(lblRebaixadas);
		
		JLabel lblNewLabel_1 = new JLabel("13\u00AA e 14\u00AA");
		lblNewLabel_1.setBounds(434, 23, 76, 14);
		contentPane.add(lblNewLabel_1);
		setVisible(true);		

	}



	public void pesquisar(){
		ApuracaoDaoImplementation aDAO=new ApuracaoDaoImplementation();


		List<Escola> escola=new ArrayList<Escola>();
		try {


			escola=aDAO.ListaDeEscolas();
		} catch (ApuracaoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Totais> totais=new ArrayList<Totais>();
		try {
			totais = aDAO.buscarTotais();			
		} catch (ApuracaoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		updateTabelaTotais(totais);
	}





	public void updateTabelaTotais(List<Totais> f){
		TableTotalController escolaTabela=new TableTotalController(f);
		table.setModel(escolaTabela);
		table.repaint();
	}
}









