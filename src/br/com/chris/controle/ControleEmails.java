package br.com.chris.controle;

import java.util.List;

import br.com.chris.DAO.EmailDAO;
import br.com.chris.entidade.Configuracao;
import br.com.chris.entidade.Usuario;

public class ControleEmails {

	public List buscaEmails () throws IllegalAccessException{
		/**
		 * Busca e retorna todos os usuarios do Database
		 */
		return (new EmailDAO().PegaEmails());
	}
	
	public void atualizaDAO (Usuario user) throws IllegalAccessException{
		/**
		 * Atualiza o Campo enviado para true
		 */
		new EmailDAO().atualizar(user);
	}
	
	public Configuracao configurar () throws IllegalAccessException{
		/**
		 * Configura os parametros de envio de email
		 */
		return (new EmailDAO().config());
	}
}
