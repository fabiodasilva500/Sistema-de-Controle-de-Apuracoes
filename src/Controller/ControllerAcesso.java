package Controller;

import Entidades.Acesso;
public interface ControllerAcesso {
	public boolean inserirLogin(Acesso acesso);
	public boolean excluirLogin(Acesso acesso);
	public Acesso consultarLogin(Acesso acesso);
}
