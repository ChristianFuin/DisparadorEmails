package br.com.chris.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.chris.controle.ControleEmails;
import br.com.chris.entidade.Configuracao;

public class ConexaoBanco {

	private static final String Dbf = "com.hxtt.sql.dbf.DBFDriver"; // registro do drive jdbc

	protected Connection conectar() throws IllegalAccessException {
		
		Connection con = null;
		try {
			Class.forName(Dbf); // carrega Driver
			String caminhoArquivo = "jdbc:DBF:/"; // conexão com dbf
			con = DriverManager.getConnection(caminhoArquivo); // conexão com o bd
																
		} catch (ClassNotFoundException ex) {
			System.out.println(">>>" + ex.getMessage());
		} catch (SQLException ex) {
			System.out.println(">>>" + ex.getMessage());
		}

		return con;
	}
	
	protected Connection conectaDbf() throws IllegalAccessException {

		Connection con = null;
		Configuracao conf = (new ControleEmails().configurar());
		String caminho = conf.getCaminho();
		
		try {
			Class.forName(Dbf); // carrega Driver
			String caminhoArquivo = "jdbc:DBF:/" + caminho; // conexão com dbf
			con = DriverManager.getConnection(caminhoArquivo); // conexão com o bd
																
		} catch (ClassNotFoundException ex) {
			System.out.println(">>>" + ex.getMessage());
		} catch (SQLException ex) {
			System.out.println(">>>" + ex.getMessage());
		}

		return con;
	}
	
	protected void desconectar(Connection con) {
		if (con != null) {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
