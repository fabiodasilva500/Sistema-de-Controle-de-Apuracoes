package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;

import Controller.ControllerAcesso;
import Dao.AcessoLoginDaoImplementation;
import Dao.AcessoLoginDaoException;
import Entidades.Acesso;

public class LoginExclusaoView extends JFrame implements ControllerAcesso {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfCPF;
	private JTextField tfLogin;
	private JTextField tfSenha;
	private JTextField tfAcesso;

	/**
	 * Launch the application.
	 */



	private void verificaInputCPF(KeyEvent key){
		char sub = key.getKeyChar();	
		if(Character.isDigit(sub) == false){
			key.consume();			
		}

		if(tfCPF.getText().length() >= 11){
			key.consume();			
		}
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginExclusaoView frame = new LoginExclusaoView();
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
	public LoginExclusaoView() {
		super("Exclusão de Cadastro de Usuário");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 784, 562);
		contentPane.add(panel);

		JLabel lblNomeCompleto = new JLabel("Nome completo");
		lblNomeCompleto.setBounds(33, 146, 219, 14);
		panel.add(lblNomeCompleto);

		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setBounds(35, 100, 254, 14);
		panel.add(lblCPF);

		tfNome = new JTextField();
		tfNome.setEnabled(false);
		tfNome.setColumns(10);
		tfNome.setBounds(315, 143, 408, 20);
		panel.add(tfNome);

		tfCPF = new JTextField();
		tfCPF.setColumns(10);
		tfCPF.setBounds(315, 97, 245, 20);

		tfCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				verificaInputCPF(tecla);
			}
		});


		panel.add(tfCPF);




		JLabel lblCargo = new JLabel("Cargo ocupado na Emissora");
		lblCargo.setBounds(33, 201, 219, 14);
		panel.add(lblCargo);

		final JComboBox cbCargo = new JComboBox();
		cbCargo.setModel(new DefaultComboBoxModel(new String[] {"Jornalista", "Repórter", "Convidado especial"}));
		cbCargo.setEnabled(false);
		cbCargo.setBounds(315, 198, 294, 20);
		panel.add(cbCargo);

		JLabel lblLogin = new JLabel("Login utilizado");
		lblLogin.setBounds(33, 261, 219, 14);
		panel.add(lblLogin);

		tfLogin = new JTextField();
		tfLogin.setEnabled(false);
		tfLogin.setColumns(10);
		tfLogin.setBounds(315, 258, 223, 20);
		panel.add(tfLogin);

		JLabel lblSenha = new JLabel("Senha atual do funcion\u00E1rio");
		lblSenha.setBounds(33, 322, 219, 14);
		panel.add(lblSenha);

		tfSenha = new JTextField();
		tfSenha.setEnabled(false);
		tfSenha.setColumns(10);
		tfSenha.setBounds(315, 319, 163, 20);
		panel.add(tfSenha);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(163, 496, 89, 23);
		panel.add(btnConsultar);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 462, 794, 5);
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 84, 794, 5);
		panel.add(separator_1);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(542, 496, 89, 23);
		panel.add(btnExcluir);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(372, 496, 89, 23);
		panel.add(btnVoltar);

		tfAcesso = new JTextField();
		tfAcesso.setBounds(315, 35, 245, 20);
		panel.add(tfAcesso);
		tfAcesso.setColumns(10);

		JLabel lblAcesso = new JLabel("Insira a senha especial de acesso");
		lblAcesso.setBounds(33, 38, 219, 14);
		panel.add(lblAcesso);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 367, 794, 5);
		panel.add(separator_2);




		ActionListener buscaListener=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(tfAcesso.getText().equals("Apuração Carnaval 2013")){


					Acesso acesso=new Acesso();
					AcessoLoginDaoImplementation dao=new AcessoLoginDaoImplementation();

					Acesso acessoConsultado=new Acesso();
					acesso.setCpf(tfCPF.getText());

					try {
						acessoConsultado=dao.consultaDados(acesso);
					} catch (AcessoLoginDaoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					tfCPF.setText(acessoConsultado.getCpf());
					tfNome.setText(acessoConsultado.getNome());
					cbCargo.setSelectedItem(acessoConsultado.getCargo());
					tfLogin.setText(acessoConsultado.getLogin());
					tfSenha.setText(acessoConsultado.getSenha());


				}
				else{
					JOptionPane.showMessageDialog(null,"A senha de acesso informada está incorreta, por favor consulte\nconsulte o administrador do sistema e tente novamente.","Atenção",JOptionPane.CANCEL_OPTION);
				}
			}


		};
		btnConsultar.addActionListener(buscaListener);




		ActionListener voltar =new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AcessoLoginView().setVisible(true);
				LoginExclusaoView.this.dispose();

			}

		};

		btnVoltar.addActionListener(voltar);



		ActionListener excluir=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Acesso acesso=new Acesso();
				acesso.setCpf(tfCPF.getText());

				if(!tfNome.getText().isEmpty()){

					boolean excluido = false;
					excluido=excluirLogin(acesso);

					if(excluido){
						JOptionPane.showMessageDialog(null,"Cadastro do usuário excluído com sucesso","Êxito",JOptionPane.INFORMATION_MESSAGE);
						tfCPF.setText("");
						tfNome.setText("");
						tfLogin.setText("");
						tfSenha.setText("");
						tfAcesso.setText("");

					}
					else{
						JOptionPane.showMessageDialog(null,"Cadastro do usuário não excluído","Atenção",JOptionPane.CANCEL_OPTION);
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"A busca não foi realizada","Atenção",JOptionPane.CANCEL_OPTION);
				}
			}

		};
		btnExcluir.addActionListener(excluir);
	}


	@Override
	public boolean inserirLogin(Acesso acesso) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean excluirLogin(Acesso acesso) {
		boolean excluido = false;
		AcessoLoginDaoImplementation dao=new AcessoLoginDaoImplementation();				  
		try {
			excluido = dao.ExcluirLogin(acesso);
		} catch (AcessoLoginDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return excluido;
	}


	@Override
	public Acesso consultarLogin(Acesso acesso) {
		// TODO Auto-generated method stub
		return null;
	}
}