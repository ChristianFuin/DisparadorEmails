package br.com.chris.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.ldap.StartTlsRequest;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import br.com.chris.controle.ControleEmails;
import br.com.chris.entidade.Configuracao;
import br.com.chris.entidade.Usuario;

public class EnviaEmail {

	public static void main(String[] args) throws IllegalAccessException, IOException {

		(new EnviaEmail()).malaDireta();

		System.exit(0);

	}

	public void malaDireta() throws IllegalAccessException, IOException {

		List mala = (new ControleEmails().buscaEmails());// tras a lista de usuarios do bd
		if (mala.size() > 0) {
			for (Object item : mala) {
				Usuario user = (Usuario) item;
				enviar(user);
				new ControleEmails().atualizaDAO(user);// limpa o BD
			}
		} else {
			System.exit(0);
		}

	}

	public void enviar(Usuario user) throws IOException, IllegalAccessException {

		Configuracao confEmail = configuracao();

		SimpleEmail email = new SimpleEmail();
		email.setHostName(confEmail.getSmtp());
		email.setSslSmtpPort(confEmail.getPorta());
		email.setStartTLSRequired(true);
		email.setSSLOnConnect(true);
		email.setAuthentication(confEmail.getEmail(), confEmail.getPassword());

		StringBuffer mensagem = new StringBuffer();
		mensagem.append(verificarVazio(user.getTexto())).append(verificarVazio(user.getTexto1()))
				.append(verificarVazio(user.getTexto2())).append(verificarVazio(user.getTexto3()))
				.append(verificarVazio(user.getTexto4())).append(verificarVazio(user.getTexto5()))
				.append(verificarVazio(user.getTexto6())).append(verificarVazio(user.getTexto7()))
				.append(verificarVazio(user.getTexto8())).append(verificarVazio(user.getTexto9())).append("\n\n")
				.append(verificarVazio(user.getResposta())).append(verificarVazio(user.getResposta1()))
				.append(verificarVazio(user.getResposta2()))
				.append(("\n ***** Enviado por GSI - Meridian Software *****"));

		try {

			email.setFrom(confEmail.getEmail());
			email.setDebug(true);
			email.setSubject(user.getAssunto());
			email.setMsg(mensagem.toString());
			email.addTo(user.getEmail());
			email.send();

		} catch (Exception e) {
			System.out.println("ERRO NO ENVIO");
			e.printStackTrace();
		}

	}

	public Configuracao configuracao() throws IllegalAccessException {
		Configuracao confi = (new ControleEmails().configurar());
		return confi;
	}

	public String verificarVazio(String texto) {

		if (texto != null) {
			texto += ("\n");
		} else {
			texto = "";
		}

		return texto;
	}

}
