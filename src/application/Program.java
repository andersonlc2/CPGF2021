package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import db.DB;
import model.entities.Favorecido;
import model.entities.Orgao;
import model.entities.OrgaoSuperior;
import model.entities.Portador;
import model.entities.Transacao;
import model.entities.UnidadeGestora;

public class Program {

	public static void main(String[] args) {
		
		//String [] files = {"202101_CPGF.csv", "202102_CPGF.csv", "202103_CPGF.csv","202104_CPGF.csv", "202105_CPGF.csv", "202106_CPGF.csv",
		//		"202107_CPGF.csv", "202108_CPGF.csv", "202109_CPGF.csv", "202110_CPGF.csv","202111_CPGF.csv", "202112_CPGF.csv"};
		//for (String f : files) {
		//	readFile("/home/anderson.correa/Props/"+f);
		//}
				
		int mesConsulta = 0;
		
		System.out.println();
		System.out.println("Qual a soma total das movimentações utilizando o CPGF?");
		System.out.println(dal.Info.totalMovimentacao(mesConsulta));
		
		System.out.println();
		System.out.println("Qual a soma das movimentações sigilosas ?");
		System.out.println(dal.Info.totalSigilosas(mesConsulta));

		System.out.println();
		System.out.println("Qual o Órgão que mais realizou movimentações sigilosas no período e qual o valor (somado)?");
		System.out.println(dal.Info.orgaoComMaisSigilosas(mesConsulta));
		
		System.out.println();
		System.out.println("Qual o nome do portador que mais realizou saques no período? Qual a soma dos saques realizada por ele? Qual o nome do Órgão desse portador?");
		System.out.println(dal.Info.portadorComMaisSaques(mesConsulta));

		System.out.println();
		System.out.println("Qual o nome do favorecido que mais recebeu compras realizadas utilizando o CPGF?");
		System.out.println(dal.Info.favorecidoComMaisCompras(mesConsulta));
		
		DB.closeConnection();
		
	}
	
	
	public static void readFile(String file) {
				
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			
			String line = br.readLine();
			line = br.readLine();
			int cont = 2;
			
			while(line != null) {
				String[] source = line.split(";");
				
				// aqui inicia as chamadas as classes dao
				OrgaoSuperior os = new OrgaoSuperior(Integer.parseInt(source[0].replace("\"", "")), source[1]);
				dal.Insert.orgaoSuperior(os);
				
				Orgao orgao = new Orgao(Integer.parseInt(source[2].replace("\"", "")), source[3], os.getCod());
				dal.Insert.orgao(orgao);
				
				UnidadeGestora ug = new UnidadeGestora(Integer.parseInt(source[4].replace("\"", "")), source[5], orgao.getCod());
				dal.Insert.unidadeGestora(ug);
							
				Portador portador = new Portador(
						source[8].replace("\"", "").equals("") ? 0 :Integer.parseInt(source[8].replace("\"", "")
								.replace("*", "").replace("-", "").replace(".", "")), source[9], ug.getCod()
				);
				dal.Insert.portador(portador);
				
				Favorecido favorecido = new Favorecido(Long.parseLong(source[10]
						.replace("\"", "")), source[11]);
				dal.Insert.favorecido(favorecido);

				Transacao transacao = new Transacao(
						0,
						source[13].replace("\"", "").equals("") ? "01/01/2001" : source[13].replace("\"", ""),
						Integer.parseInt(source[6].replace("\"", "")),
						Double.parseDouble(source[14].replace(",", ".").replace("\"", "")),
						source[12],
						portador.getCpf(),
						favorecido.getCod(),
						orgao.getCod(),
						dal.Info.mes[Integer.parseInt(source[7].replace("\"", ""))]
				);
				dal.Insert.transacao(transacao, cont);
				
				cont++;
				line = br.readLine();
			}
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		finally {
			DB.closeConnection();
		}
	}
}
