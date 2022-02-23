package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import model.entities.Favorecido;
import model.entities.Orgao;
import model.entities.OrgaoSuperior;
import model.entities.Portador;
import model.entities.Transacao;
import model.entities.UnidadeGestora;

public class Insert {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private static Connection conn = DB.getConnection();
	private static PreparedStatement st = null;
	
	public static void orgaoSuperior(OrgaoSuperior orgaoSuperior) {
			try {
				st = conn.prepareStatement(
						"INSERT INTO orgaosup VALUES(?, ?)");
				st.setInt(1, orgaoSuperior.getCod());
				st.setString(2, orgaoSuperior.getNome());
				
				int rowsAffect = st.executeUpdate();
				System.out.println("Rows affect: "+rowsAffect);
			}
			catch(SQLException e) {
				System.err.println("Error: "+ e.getMessage());
			}
			finally {
				DB.closeStatement(st);
		}
	}

	public static void orgao(Orgao orgao) {
			try {
				st = conn.prepareStatement(
						"INSERT INTO orgao VALUES(?, ?, ?)");
				st.setInt(1, orgao.getCod());
				st.setString(2, orgao.getNome());
				st.setInt(3, orgao.getCodOrgaoSup());
				
				int rowsAffect = st.executeUpdate();
				System.out.println("Rows affect: "+rowsAffect);
			}
			catch(SQLException e) {
				System.err.println("Error: "+ e.getMessage());
			}
			finally {
				DB.closeStatement(st);
			}			
	}

	public static void unidadeGestora(UnidadeGestora unidadeGestora) {
			try {
				st = conn.prepareStatement(
						"INSERT INTO unidadegestora VALUES(?, ?, ?)");
				st.setInt(1, unidadeGestora.getCod());
				st.setString(2, unidadeGestora.getNome());
				st.setInt(3, unidadeGestora.getCodOrgao());
				
				int rowsAffect = st.executeUpdate();
				System.out.println("Rows affect: "+rowsAffect);
			}
			catch(SQLException e) {
				System.err.println("Error: "+ e.getMessage());
			}
			finally {
				DB.closeStatement(st);
			}			
	}
	

	public static void portador(Portador portador) {
			try {
				st = conn.prepareStatement(
						"INSERT INTO portador VALUES(?, ?, ?)");
				st.setInt(1, portador.getCpf());
				st.setString(2, portador.getNome());
				st.setInt(3, portador.getCodUnidGestora());
				
				int rowsAffect = st.executeUpdate();
				System.out.println("Rows affect: "+rowsAffect);
			}
			catch(SQLException e) {
				System.err.println("Error: "+ e.getMessage());
			}
			finally {
				DB.closeStatement(st);
			}			
	}
	
	
	public static void favorecido(Favorecido favorecido) {
		try {
			st = conn.prepareStatement(
					"INSERT INTO favorecido VALUES(?, ?)");
			st.setLong(1, favorecido.getCod());
			st.setString(2, favorecido.getNome());
			
			int rowsAffect = st.executeUpdate();
			System.out.println("Rows affect: "+rowsAffect);
		}
		catch(SQLException e) {
			System.err.println("Error: "+ e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
	
	public static void transacao(Transacao transacao, int count) {
		try {
			st = conn.prepareStatement(
					"INSERT INTO transacao(data, ano, valor, descricao, codportador, codfavorecido, codorgao) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)");
			st.setDate(1, new Date(sdf.parse(transacao.getData()).getTime()));
			st.setInt(2, transacao.getAno());
			st.setDouble(3, transacao.getValor());
			st.setString(4, transacao.getDescricao());
			st.setInt(5, transacao.getCodPortador());
			st.setLong(6, transacao.getCodFavorecido());
			st.setLong(7, transacao.getCodOrgao());
			
			int rowsAffect = st.executeUpdate();
			
			if (rowsAffect > 0) {
				System.out.println("Rows affect: "+count);
			}
			else {
				System.err.println("Error: not salved line: "+ count);
			}
			
		}
		catch(SQLException e) {
			System.err.println("Error: "+ e.getMessage());
		}
		catch(ParseException e) {
			System.err.println(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
}
