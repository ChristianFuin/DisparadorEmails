package br.com.chris.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.chris.entidade.Configuracao;
import br.com.chris.entidade.Usuario;

public class EmailDAO extends ConexaoBanco {

	// busca todos os usuarios no banco de Dados

	public List PegaEmails() throws IllegalAccessException {

		String sql = "select * from MSG_E.dbf ";
		List emails = new ArrayList();
		
		Connection con = null;

		try {
			con = conectaDbf();
			if (con != null) {
				sql += "where EMAIL <> null ";
			}

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			// resultado da busca no databse e add o usuario na lista
			while (rs.next()) {

				Usuario user = new Usuario();
				user.setEmail(rs.getString("EMAIL"));
				user.setAssunto(rs.getString("REFERENTE"));
				user.setTexto(rs.getString("MSG1"));
				user.setTexto1(rs.getString("MSG2"));
				user.setTexto2(rs.getString("MSG3"));
				user.setTexto3(rs.getString("MSG4"));
				user.setTexto4(rs.getString("MSG5"));
				user.setTexto5(rs.getString("MSG6"));
				user.setTexto6(rs.getString("MSG7"));
				user.setTexto7(rs.getString("MSG8"));
				user.setTexto8(rs.getString("MSG9"));
				user.setTexto9(rs.getString("MSG10"));
				user.setResposta(rs.getString("RESP1"));
				user.setResposta1(rs.getString("RESP2"));
				user.setResposta2(rs.getString("RESP3"));

				emails.add(user);
			}
			rs.close();
			desconectar(con);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emails;
	}

	public void atualizar(Usuario user) throws IllegalAccessException {
		Connection con = null;
		String sql = "DELETE FROM MSG_E WHERE EMAIL = ?";
		try {
			con = conectaDbf();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.execute();
		} catch (SQLException e) {

		}

	}

	public Configuracao config() throws IllegalAccessException {

		String sql = "select * from E_Config.dbf";
		Connection con = null;
		Configuracao conf = new Configuracao();

		try {
			con = conectar();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				conf.setEmail(rs.getString("E_ENV"));
				conf.setPassword(rs.getString("E_PASS"));
				conf.setPorta(rs.getString("E_PORTA"));
				conf.setSmtp(rs.getString("E_SMTP"));
				conf.setCaminho(rs.getString("E_PATH_MSG"));
			}

			rs.close();

		} catch (SQLException e) {
			System.out.println("erro de sql");
			e.printStackTrace();
		}
		
		return conf;
	}

}
