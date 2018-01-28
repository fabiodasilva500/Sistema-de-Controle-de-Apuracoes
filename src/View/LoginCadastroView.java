package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.ControllerAcesso;
import Dao.AcessoLoginDaoException;
import Dao.AcessoLoginDaoImplementation;
import Entidades.Acesso;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;

public class LoginCadastroView extends JFrame  implements ControllerAcesso{

	private JPanel contentPane;
	private JTextField tfAcesso;
	private JTextField tfLogin;
	private final JButton btnCadastrar = new JButton("Cadastrar");
	private JLabel lblNome;
	private JTextField tfNome;
	private JLabel lblCPF;
	private JLabel lblCargo;
	private JTextField tfCPF;
	private JPasswordField tfSenha;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private JSeparator separator_4;

	/**
	 * Launch the application.
	 */

	private void verificaInputNome(KeyEvent key){
		char sub = key.getKeyChar();		
		if(Character.isDigit(sub) == true){
			key.consume();			
		}

		if(tfNome.getText().length() >= 70){		
			key.consume();			
		}
	}


	private void verificaInputCPF(KeyEvent key){
		char sub = key.getKeyChar();	
		if(Character.isDigit(sub) == false){
			key.consume();			
		}

		if(tfCPF.getText().length() >= 11){
			key.consume();			
		}
	}



	private void verificaInputLogin(KeyEvent key){
		char sub = key.getKeyChar();		
		if(tfLogin.getText().length() >= 30){
			key.consume();			
		}
	}



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginCadastroView frame = new LoginCadastroView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginCadastroView() {
		super("Manutenção do Cadastro de Logins");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		btnCadastrar.setBounds(111, 505, 116, 23);
		contentPane.add(btnCadastrar);

		JLabel lblSenhaMaster = new JLabel("Insira a senha especial de acesso");
		lblSenhaMaster.setBounds(18, 60, 219, 14);
		contentPane.add(lblSenhaMaster);

		tfAcesso = new JTextField();
		tfAcesso.setBounds(301, 57, 343, 20);
		contentPane.add(tfAcesso);
		tfAcesso.setColumns(10);

		JLabel lblLogin = new JLabel("Login que o funcion\u00E1rio deseja utilizar");
		lblLogin.setBounds(18, 357, 219, 14);
		contentPane.add(lblLogin);

		tfLogin = new JTextField();
		tfLogin.setBounds(301, 354, 223, 20);
		contentPane.add(tfLogin);
		tfLogin.setColumns(10);


		tfLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputLogin(tecla);
			}
		});

		JLabel lblSenha = new JLabel("Senha (pode conter letras e/ou n\u00FAmeros)");
		lblSenha.setBounds(18, 413, 256, 14);
		contentPane.add(lblSenha);

		lblNome = new JLabel("Nome completo");
		lblNome.setBounds(18, 186, 219, 14);
		contentPane.add(lblNome);

		tfNome = new JTextField();
		tfNome.setBounds(301, 183, 408, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);


		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputNome(tecla);
			}
		});

		lblCPF = new JLabel("CPF");
		lblCPF.setBounds(18, 237, 152, 14);
		contentPane.add(lblCPF);



		lblCargo = new JLabel("Cargo ocupado na Emissora");
		lblCargo.setBounds(18, 284, 273, 14);
		contentPane.add(lblCargo);

		final JComboBox cbCargo = new JComboBox();
		cbCargo.setModel(new DefaultComboBoxModel(new String[] {"Jornalista", "Repórter", "Convidado especial"}));
		cbCargo.setBounds(301, 281, 294, 20);
		contentPane.add(cbCargo);

		tfCPF = new JTextField();
		tfCPF.setBounds(301, 234, 245, 20);
		contentPane.add(tfCPF);
		tfCPF.setColumns(10);
		tfCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputCPF(tecla);
			}
		});


		JSeparator separator = new JSeparator();
		separator.setBounds(-20, 136, 794, 5);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 489, 794, 5);
		contentPane.add(separator_1);

		JLabel lblDadosPessoais = new JLabel("Dados pessoais");
		lblDadosPessoais.setBounds(18, 143, 131, 14);
		contentPane.add(lblDadosPessoais);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(589, 505, 89, 23);
		contentPane.add(btnVoltar);

		tfSenha = new JPasswordField();
		tfSenha.setBounds(301, 410, 223, 20);
		contentPane.add(tfSenha);

		separator_2 = new JSeparator();
		separator_2.setBounds(0, 24, 794, 5);
		contentPane.add(separator_2);

		separator_3 = new JSeparator();
		separator_3.setBounds(0, 325, 794, 5);
		contentPane.add(separator_3);

		separator_4 = new JSeparator();
		separator_4.setBounds(0, 100, 794, 5);
		contentPane.add(separator_4);





		ActionListener cadastroListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Acesso login=new Acesso();


				if(tfAcesso.getText().equals("Apuração Carnaval 2013")){


					if(!tfLogin.getText().isEmpty()&&(!tfSenha.getText().isEmpty()&&(!tfCPF.getText().isEmpty())&&(!tfNome.getText().isEmpty() &&(tfCPF.getText().length()==11)))){
						login.setNome(tfNome.getText());
						login.setCpf(tfCPF.getText());
						login.setCargo(cbCargo.getSelectedItem().toString());
						login.setLogin(tfLogin.getText());
						login.setSenha(tfSenha.getText());


						boolean inserido = false;
						inserido=inserirLogin(login);

						if(inserido){
							JOptionPane.showMessageDialog(null,"Login inserido com sucesso",
									"Êxito",JOptionPane.INFORMATION_MESSAGE);
							new AcessoLoginView().setVisible(true);
							LoginCadastroView.this.dispose();
						}
					}

					else{
						JOptionPane.showMessageDialog(null,"Algum dos campos não foi inserido ou o CPF está inválido, por favor complete seu cadastro.",
								"Atenção", JOptionPane.CANCEL_OPTION);
					}

				}
				else{
					JOptionPane.showMessageDialog(null,"A senha de acesso informada está incorreta, por favor consulte\no administrador do sistema e tente novamente.","Atenção",JOptionPane.CANCEL_OPTION);

				}
			}


		};

		btnCadastrar.addActionListener(cadastroListener);

		ActionListener voltar =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AcessoLoginView().setVisible(true);
				LoginCadastroView.this.dispose();

			}

		};

		btnVoltar.addActionListener(voltar);

	}


	@Override
	public boolean inserirLogin(Acesso acesso) {
		boolean inserido=false;
		AcessoLoginDaoImplementation dao=new AcessoLoginDaoImplementation();
		try {
			inserido = dao.InsereLogin(acesso);
		} catch (AcessoLoginDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inserido;
	}


	@Override
	public boolean excluirLogin(Acesso acesso) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Acesso consultarLogin(Acesso acesso) {
		// TODO Auto-generated method stub
		return null;
	}

}

