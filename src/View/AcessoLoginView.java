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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import Controller.ControllerAcesso;
import Dao.AcessoLoginDaoImplementation;
import Dao.AcessoLoginDaoException;
import Entidades.Acesso;

import javax.swing.JSeparator;
import javax.swing.JPasswordField;

public class AcessoLoginView extends JFrame  implements ControllerAcesso{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfLogin;
	private JButton btnNovoLogin = new JButton("N\u00E3o \u00E9 cadastrado? Clique aqui");
	private JSeparator separator;
	private JSeparator separator_1;
	private JPasswordField tfSenha;
	/**
	 * Launch the application.
	 */


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
					AcessoLoginView frame = new AcessoLoginView();
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
	public AcessoLoginView() {
		super("Bem vindo ao Sistema de Controle de Apurações do Carnaval de São Paulo");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JButton btnAcessar = new JButton("Acessar");
		btnAcessar.setBounds(21, 437, 94, 23);
		contentPane.add(btnAcessar);

		tfLogin = new JTextField();
		tfLogin.setBounds(297, 167, 309, 20);
		contentPane.add(tfLogin);
		tfLogin.setColumns(10);

		tfLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputLogin(tecla);
			}
		});


		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(164, 173, 46, 14);
		contentPane.add(lblLogin);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(164, 241, 46, 14);
		contentPane.add(lblSenha);


		btnNovoLogin.setBounds(354, 437, 211, 23);
		contentPane.add(btnNovoLogin);

		separator = new JSeparator();
		separator.setBounds(0, 77, 794, 5);
		contentPane.add(separator);

		separator_1 = new JSeparator();
		separator_1.setBounds(0, 329, 794, 5);
		contentPane.add(separator_1);

		JButton btnEsqueciMinhaSenha = new JButton("Esqueci Minha Senha");
		btnEsqueciMinhaSenha.setBounds(147, 437, 171, 23);
		contentPane.add(btnEsqueciMinhaSenha);

		JButton btnExcluirCadastro = new JButton("Exclus\u00E3o de Cadastro");
		btnExcluirCadastro.setBounds(599, 437, 159, 23);
		contentPane.add(btnExcluirCadastro);

		tfSenha = new JPasswordField();
		tfSenha.setBounds(328, 238, 237, 20);
		contentPane.add(tfSenha);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 488, 794, 5);
		contentPane.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 396, 794, 5);
		contentPane.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(0, 299, 794, 5);
		contentPane.add(separator_4);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(0, 108, 794, 5);
		contentPane.add(separator_5);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(0, 45, 794, 5);
		contentPane.add(separator_6);

		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(0, 360, 794, 5);
		contentPane.add(separator_7);




		ActionListener login =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new LoginCadastroView().setVisible(true);
				AcessoLoginView.this.dispose();
			}

		};

		btnNovoLogin.addActionListener(login);

		ActionListener buscaListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Acesso acesso=new Acesso();

				Acesso acessoConsultado=new Acesso();
				acesso.setLogin(tfLogin.getText());
				acesso.setSenha(tfSenha.getText());
				acessoConsultado=consultarLogin(acesso);


				tfLogin.setText(acessoConsultado.getLogin());
				tfSenha.setText(acessoConsultado.getSenha());


				if(tfSenha.getText().equals(acessoConsultado.getSenha())){

					new ApuracaoView().setVisible(true);
					AcessoLoginView.this.dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,"Caro usuário seu login e senha estão incorretos, por favor verifique-os","Atenção",JOptionPane.CANCEL_OPTION);
				}
			}

		};

		btnAcessar.addActionListener(buscaListener);


		ActionListener RecuperaSenha =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new RecuperaSenhaView().setVisible(true);
				AcessoLoginView.this.dispose();

			}

		};

		btnEsqueciMinhaSenha.addActionListener(RecuperaSenha);





		ActionListener ExcluirCadastro =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new LoginExclusaoView().setVisible(true);
				AcessoLoginView.this.dispose();

			}

		};

		btnExcluirCadastro.addActionListener(ExcluirCadastro);

	}

	@Override
	public boolean inserirLogin(Acesso acesso) {
		return false;
	}

	@Override
	public boolean excluirLogin(Acesso acesso) {
		return false;
	}

	@Override
	public Acesso consultarLogin(Acesso acesso) {
		Acesso acessoConsultado=new Acesso();
		AcessoLoginDaoImplementation dao=new AcessoLoginDaoImplementation();
		try {
			acessoConsultado=dao.consultaLogin(acesso);
		} catch (AcessoLoginDaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return acessoConsultado;
	}
}


