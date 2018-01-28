package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;


import Controller.ControllerApuracao;
import Dao.ApuracaoDaoImplementation;
import Dao.ApuracaoDaoException;
import Entidades.Apuracao;
import Entidades.Escola;
import Entidades.Jurado;
import java.awt.Font;



public class ApuracaoView extends JFrame  implements WindowListener, ControllerApuracao{

	private JPanel contentPane;
	private final JComboBox cbEscola = new JComboBox();
	private final JComboBox cbQuesito = new JComboBox();
	private final JComboBox cbJurado = new JComboBox();
	private final JComboBox cbPosicao = new JComboBox();

	private JTextField tfNota;
	private int quesito=0;
	private int escola=0;
	private int jurado=0;
	private int posicao = 0;
	private ApuracaoDaoImplementation dao;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApuracaoView frame = new ApuracaoView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void verificaInputNota(KeyEvent key){
		char sub = key.getKeyChar();
		int recebe= sub;

		if(recebe!=46){
			if(Character.isDigit(sub)==false){
				key.consume();
			}
			if(tfNota.getText().length() >= 3){
				key.consume();			
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public ApuracaoView() {
		
		
		
		
		super("Apuração");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEscola = new JLabel("Escola*");
		lblEscola.setBounds(10, 42, 46, 14);
		contentPane.add(lblEscola);


		cbEscola.setBounds(85, 39, 559, 20);
		contentPane.add(cbEscola);

		JLabel lblQuesito = new JLabel("Quesito*");
		lblQuesito.setBounds(10, 143, 65, 14);
		contentPane.add(lblQuesito);

		cbQuesito.setBounds(85, 140, 559, 20);
		contentPane.add(cbQuesito);

		final JLabel lblJurado = new JLabel("Jurado*");
		lblJurado.setBounds(10, 90, 46, 14);
		contentPane.add(lblJurado);

		cbJurado.setBounds(85, 87, 559, 20);
		contentPane.add(cbJurado);

		final JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(287, 261, 120, 23);
		contentPane.add(btnAdicionar);

		final JButton btnVerQuesito = new JButton("Ver Quesito Atual");
		btnVerQuesito.setBounds(85, 406, 139, 23);
		contentPane.add(btnVerQuesito);

		final JButton btnVerTotal = new JButton("Ver Total");
		btnVerTotal.setBounds(330, 406, 120, 23);
		contentPane.add(btnVerTotal);


		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(-4, 344, 788, 2);
		contentPane.add(separator_1);

		JLabel lblVisualizacao = new JLabel("Visualiza\u00E7\u00E3o da apura\u00E7\u00E3o");
		lblVisualizacao.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblVisualizacao.setBounds(10, 344, 239, 14);
		contentPane.add(lblVisualizacao);

		JLabel lblMensagem = new JLabel("Campos com * s\u00E3o obrigat\u00F3rios para inser\u00E7\u00E3o");
		lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblMensagem.setBounds(275, 189, 298, 14);
		contentPane.add(lblMensagem);

		tfNota = new JTextField();
		tfNota.setBounds(77, 262, 86, 20);
		contentPane.add(tfNota);
		tfNota.setColumns(10);


		tfNota.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputNota(tecla);
			}
		});


		JLabel lblNota = new JLabel("Nota*");
		lblNota.setBounds(10, 265, 46, 14);
		contentPane.add(lblNota);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(-4, 184, 788, 2);
		contentPane.add(separator_2);

		JLabel lblAtencao = new JLabel("Aten\u00E7\u00E3o para o cadastro da nota");
		lblAtencao.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblAtencao.setBounds(10, 189, 226, 14);
		contentPane.add(lblAtencao);

		btnAdicionar.setVisible(true);
		btnVerQuesito.setVisible(true);
		btnVerTotal.setVisible(true);

		cbEscola.setEnabled(false);
		cbJurado.setEnabled(false);
		cbQuesito.setEnabled(false);
		cbPosicao.setEnabled(false);


		cbPosicao.setBounds(673, 87, 101, 20);
		contentPane.add(cbPosicao);

		final JButton btnReiniciar = new JButton("Reiniciar Contagem");
		btnReiniciar.setBounds(547, 406, 151, 23);
		contentPane.add(btnReiniciar);
		btnReiniciar.setVisible(false);

		JButton btnPrepara = new JButton("Preparar recontagem");
		btnPrepara.setBounds(536, 261, 162, 23);
		contentPane.add(btnPrepara);
		carregarCombox();


		final ActionListener listenerCadastro = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				inserirAvaliacao();
				btnReiniciar.setVisible(false);

				if(escola ==14 && jurado==45 && posicao==5){
					btnAdicionar.setEnabled(false);
				}
				
				
			}
		};

		btnAdicionar.addActionListener(listenerCadastro);


		setVisible(true);
		addWindowListener(this);



		final ActionListener listenerPesquisa= new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Apuracao apuracao=new Apuracao();

				apuracao.setIDQuesito(cbQuesito.getSelectedIndex()); 

				apuracao.setIDEscola(cbEscola.getSelectedIndex()+1);
				apuracao.setIDQuesito(cbQuesito.getSelectedIndex()+1); 
				apuracao.setIDJurado(cbJurado.getSelectedIndex()+1); 
				apuracao.setPosicaoJurado(cbPosicao.getSelectedIndex()+1);


				int id=1;

				id = apuracao.getIDQuesito();

				if(apuracao.getIDEscola()==1){

					if(apuracao.getIDJurado()>=6 && apuracao.getIDJurado()<=10){
						id = 2;
					}

					if(apuracao.getIDJurado()>=11 && apuracao.getIDJurado()<=15){
						id = 3;
					}

					if(apuracao.getIDJurado()>=16 && apuracao.getIDJurado()<=20){
						id = 4;
					}


					if(apuracao.getIDJurado()>=21 && apuracao.getIDJurado()<=25){
						id = 5;
					}


					if(apuracao.getIDJurado()>=26 && apuracao.getIDJurado()<=30){
						id = 6;
					}

					if(apuracao.getIDJurado()>=31 && apuracao.getIDJurado()<=35){
						id = 7;
					}

					if(apuracao.getIDJurado()>=36 && apuracao.getIDJurado()<=40){
						id = 8;
					}

					if(apuracao.getIDJurado()>=41 && apuracao.getIDJurado()<=45){
						id = 9;
					}

					if(apuracao.getIDEscola()==1){


						if(apuracao.getIDJurado()==6
								|| apuracao.getIDJurado()==11
								|| apuracao.getIDJurado()==16
								|| apuracao.getIDJurado()==21
								|| apuracao.getIDJurado()==26
								|| apuracao.getIDJurado()==31
								|| apuracao.getIDJurado()==36
								|| apuracao.getIDJurado()==41){

							id = apuracao.getIDQuesito()-1;
						}

					}
				}

				QuesitoViewNotas quesito=new QuesitoViewNotas();
				quesito.pesquisar(id);
				btnReiniciar.setVisible(false);


			}


		};
		btnVerQuesito.addActionListener(listenerPesquisa);


		final ActionListener listenerTotais = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TotalViewNotas notas=new TotalViewNotas();
				notas.pesquisar();
				btnReiniciar.setVisible(false);

			}
		};

		btnVerTotal.addActionListener(listenerTotais);


		final ActionListener listenerPreparaReinicializacao = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnReiniciar.setVisible(true);
				JOptionPane.showMessageDialog(null,"Caso deseje realmente reiniciar a apuração clique no botão\n'Reiniciar Contagem'"+
						"ressaltando que esta operação será\nirreversível.", "Atenção",JOptionPane.CANCEL_OPTION);

				
			}
		};

		btnPrepara.addActionListener(listenerPreparaReinicializacao);

		
		final ActionListener listenerReiniciar = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				ApuracaoDaoImplementation dao=new ApuracaoDaoImplementation();
				try {
					dao.reiniciarApuracao();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
				JOptionPane.showMessageDialog(null,"Apuração reiniciada com sucesso.\nVocê será redirecionado para tela de acesso.","Êxito",JOptionPane.INFORMATION_MESSAGE);
				new AcessoLoginView().setVisible(true);
	               ApuracaoView.this.dispose();
              }
		};

		btnReiniciar.addActionListener(listenerReiniciar);
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {

		int finalizar = JOptionPane.showConfirmDialog(this,"Deseja finalizar o sistema?","Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if(finalizar==JOptionPane.YES_NO_OPTION){
			System.exit(0);
		}

	}



	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}


	public void carregarCombox(){
		List<Escola> listaEscola=new ArrayList<Escola>();
		dao=new ApuracaoDaoImplementation();
		try {
			listaEscola = dao.ListaDeEscolas();
		} catch (ApuracaoDaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(Escola escola : listaEscola){
			cbEscola.addItem("ID: "+escola.getID()+ " - "+escola.getNome());
		}

		List<Jurado> listaJurado=new ArrayList<Jurado>();

		try {
			listaJurado = dao.ListaDeJurados();
		} catch (ApuracaoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Jurado jurado : listaJurado){
			cbJurado.addItem("ID: "+jurado.getID()+" - "+jurado.getNome());
		}


		List<Jurado> listaJuradoPosicao=new ArrayList<Jurado>();

		try {
			listaJuradoPosicao = dao.ListaDeJuradosPosicao();
		} catch (ApuracaoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Jurado jurado : listaJuradoPosicao){
			cbPosicao.addItem("Posição: "+jurado.getPosicao());
		}

		cbQuesito.addItem("ID: "+1+" - "+"Comissão de Frente");
		cbQuesito.addItem("ID: "+2+" - "+"Evolução");
		cbQuesito.addItem("ID: "+3+" - "+"Fantásia");
		cbQuesito.addItem("ID: "+4+" - "+"Bateria");
		cbQuesito.addItem("ID: "+5+" - "+"Alegoria");
		cbQuesito.addItem("ID: "+6+" - "+"Harmonia");
		cbQuesito.addItem("ID: "+7+" - "+"Samba Enredo");
		cbQuesito.addItem("ID: "+8+" - "+"Mestre Sala e Porta Bandeira");
		cbQuesito.addItem("ID: "+9+" - "+"Enredo");		
	}


	public  void inserirAvaliacao(){  
		Apuracao apuracao = new Apuracao();

		if(cbEscola.getSelectedIndex()==5)
		{
			apuracao.setIDEscola(cbEscola.getSelectedIndex()+1); 

		}

		apuracao.setIDEscola(cbEscola.getSelectedIndex()+1); 
		apuracao.setIDQuesito(cbQuesito.getSelectedIndex()+1); 
		apuracao.setIDJurado(cbJurado.getSelectedIndex()+1); 
		apuracao.setPosicaoJurado(cbPosicao.getSelectedIndex()+1);

		if(!tfNota.getText().isEmpty())
		{
			apuracao.setNota(Float.parseFloat(tfNota.getText()));

			if(apuracao.getNota()>=6  && apuracao.getNota()<=10){


				if(!tfNota.getText().isEmpty()){
					boolean inserido = false;
					try {
						inserido = dao.insereAvaliacao(apuracao);
					} catch (ApuracaoDaoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if(inserido){
						
						if(escola ==13 && jurado==44 && posicao==4){
							JOptionPane.showMessageDialog(null, "Caro usuário, todos os dados já foram inseridos.","Atenção",JOptionPane.CANCEL_OPTION);
						}
						else{
						JOptionPane.showMessageDialog(null, "Avaliação inserida com sucesso.","Êxito",JOptionPane.INFORMATION_MESSAGE);
						if (quesito>=0 && quesito<9){
							if(jurado>=0 && jurado<45){
								if(escola>=-1 && escola<14){
									escola++;

									cbEscola.setSelectedIndex(escola);
									cbQuesito.setSelectedIndex(quesito);
									cbJurado.setSelectedIndex(jurado);
									cbPosicao.setSelectedIndex(posicao);

									if(escola==13 && jurado!=4  &&
											jurado!=9  && jurado!=14 && jurado!=19
											&& jurado!=24 && jurado!=29  && jurado!=34
											&& jurado!=39 && jurado!=44 && jurado<=44){
										escola=-1;
										jurado++;
										posicao++;
									}


									if(escola==13 && jurado==4){
										escola=-1;
										posicao=0;
										jurado++;
										if(escola==-1 && jurado==5){
											quesito++;								
										}
									}

									if(escola==13 && jurado==9){
										escola=-1;
										posicao=0;
										jurado++;
										if(escola==-1 && jurado==10){
											quesito++;
										}
									}


									if(escola==13 && jurado==14){
										escola=-1;
										posicao=0;
										jurado++;
										if(escola==-1 && jurado==15){
											quesito++;
										}
									}

									if(escola==13 && jurado==19){
										escola=-1;
										posicao=0;
										jurado++;
										if(escola==-1 && jurado==20){
											quesito++;
										}
									}


									if(escola==13 && jurado==24){
										escola=-1;
										posicao=0;
										jurado++;
										if(escola==-1 && jurado==25){
											quesito++;
										}
									}


									if(escola==13 && jurado==29){
										escola=-1;
										posicao=0;
										jurado++;
										if(escola==-1 && jurado==30){
											quesito++;
										}
									}

									if(escola==13 && jurado==34){
										escola=-1;
										posicao=0;
										jurado++;
										if(escola==-1 && jurado==35){
											quesito++;
										}
									}


									if(escola==13 && jurado==39){
										escola=-1;
										posicao=0;
										jurado++;
										if(escola==-1 && jurado==40){
											quesito++;
										}
									}

								}
							}					  
						}
					}
				}

				else{
					JOptionPane.showMessageDialog(null,"Não são aceitos caracteres no campo nota,\npor favor verifique o conteúdo digitado.", "Atenção",JOptionPane.CANCEL_OPTION);
				}
			}

			else{
				JOptionPane.showMessageDialog(null,"Caro usuário o carnaval de São Paulo só utiliza notas entre 6 e 10,\nverifique o conteúdo informado.", "Atenção",JOptionPane.CANCEL_OPTION);
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"Nota não informada.", "Atenção",JOptionPane.CANCEL_OPTION);
		}
		
		
	   
	}
}
}



