package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Info {
	
	// private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public static String[] mes = {"", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho",
            "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
	
	private static Connection conn = DB.getConnection();
	private static Statement st = null;
	private static ResultSet rs = null;
	
	public static String totalMovimentacao(int m) {
		Double total = 0.0;
		try {
			st = conn.createStatement();
			if (m == 0) {
				rs = st.executeQuery("SELECT SUM(valor) FROM transacao");				
			} else {
				rs = st.executeQuery(String.format("SELECT SUM(valor) FROM transacao WHERE mes='%s'",mes[m]));				
			}
			
			rs.next();
			total = rs.getDouble(1);
		}
		catch(SQLException e) {
			System.err.println("Error: "+ e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}	
		return String.format("%.2f", total);

	}

	public static Double totalSigilosas(int m) {
		Double total = 0.0;
		try {
			st = conn.createStatement();
			if (m==0) {
				rs = st.executeQuery(
						"SELECT SUM(valor) "
						+ "FROM transacao "
						+ "WHERE codfavorecido = -11");
			} else {
				rs = st.executeQuery(String.format(
						"SELECT SUM(valor) "
						+ "FROM transacao "
						+ "WHERE codfavorecido = -11 AND mes='%s'",mes[m]));
			}
			
			rs.next();
			total = rs.getDouble(1);
		}
		catch(SQLException e) {
			System.err.println("Error: "+ e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}	
		return total;

	}

	public static String orgaoComMaisSigilosas(int m) {
		Double total = 0.0;
		Integer cod = 0;
		String nome = "";
		try {
			st = conn.createStatement();
			if (m==0) {
				rs = st.executeQuery(
						"select transacao.codorgao as cod, o.nome as nome, sum(transacao.valor) as soma "
								+ "from transacao "
								+ "inner join orgao o on transacao.codorgao = o.cod "
								+ "where transacao.codfavorecido = -11 "
								+ "group by o.nome, transacao.codorgao "
								+ "order by soma desc");				
			} else {
				rs = st.executeQuery(String.format(
						"select transacao.codorgao as cod, o.nome as nome, sum(transacao.valor) as soma "
								+ "from transacao "
								+ "inner join orgao o on transacao.codorgao = o.cod "
								+ "where transacao.codfavorecido = -11 and transacao.mes='%s'"
								+ "group by o.nome, transacao.codorgao "
								+ "order by soma desc",mes[m]));	
			}
			rs.next();
			total = rs.getDouble("soma");
			cod = rs.getInt("cod");
			nome = rs.getString("nome");
		}
		catch(SQLException e) {
			System.err.println("Error: "+ e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}	
		return "Total: "+total+" - "+cod+" "+nome;

	}

	public static String portadorComMaisSaques(int m) {
		Double total = 0.0;
		String portador = "";
		String nomeOrgao = "";
		try {
			st = conn.createStatement();
			if (m==0) {
				rs = st.executeQuery(
						"select p.nome as portador, o.nome as nome, sum(transacao.valor) as soma "
								+ "from transacao "
								+ "inner join orgao o on transacao.codorgao = o.cod "
								+ "inner join portador p on transacao.codportador = p.cpf "
								+ "where transacao.codfavorecido = -2 "
								+ "group by p.nome, o.nome "
								+ "order by soma desc");
				
			} else {
				rs = st.executeQuery(String.format(
						"select p.nome as portador, o.nome as nome, sum(transacao.valor) as soma "
								+ "from transacao "
								+ "inner join orgao o on transacao.codorgao = o.cod "
								+ "inner join portador p on transacao.codportador = p.cpf "
								+ "where transacao.codfavorecido = -2 and transacao.mes='%s'"
								+ "group by p.nome, o.nome "
								+ "order by soma desc", mes[m]));

			}
			
			rs.next();
			total = rs.getDouble("soma");
			portador = rs.getString("portador");
			nomeOrgao = rs.getString("nome");
		}
		catch(SQLException e) {
			System.err.println("Error: "+ e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}	
		return String.format("Total: %.2f", total)+" - "+portador+" Orgão:"+nomeOrgao;

	}
	

	public static String favorecidoComMaisCompras(int m) {
		String nome = "";
		try {
			st = conn.createStatement();
			if (m==0) {
				rs = st.executeQuery(
						"select f.nome as nome, sum(t.valor) "
								+ "from transacao t "
								+ "inner join favorecido f on t.codfavorecido = f.cod "
								+ "where t.codfavorecido > 0 "
								+ "group by f.nome "
								+ "order by sum desc");
			} else {
				rs = st.executeQuery(String.format(
						"select f.nome as nome, sum(t.valor) "
								+ "from transacao t "
								+ "inner join favorecido f on t.codfavorecido = f.cod "
								+ "where t.codfavorecido > 0 and t.mes='%s'"
								+ "group by f.nome "
								+ "order by sum desc", mes[m]));
			}
			rs.next();
			nome = rs.getString("nome");
		}
		catch(SQLException e) {
			System.err.println("Error: "+ e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}	
		return nome;

	}
	
}
