/*
AUTOR: LUIZ GUILHERME PALHARES PETTENGILL
EMAIL: LUIZPETTENGILL@HOTMAIL.COM

FUNÇÃO DA APLICAÇÃO: CALCULO DE PRAZO RECURSAL, SEGUINDO AS LEIS ATUAIS.
*/

package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) throws ParseException {
	
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("Esse programa irá calcular em dias úteis o ultimo dia para "
				+ "interposição de recursos a partir do prazo recursal informado pelo usuário.");
		
		//PEDINDO PARA O USUÁRIO ESCOLHER O PRAZO RECURSAL
		System.out.println("Escolha o prazo recursal(em dias):");
		System.out.printf("%n		[5]    [8]    [15]: ");
		int processo = sc.nextInt();		
		
		//CHECANDO SE O PRAZO É EM DOBRO
		System.out.printf("%n              O prazo é em dobro(sim/nao)? ");
		String prazo = sc.next();
		
		int tamanho = 1;
		
		if(prazo.equals("sim")) {
			tamanho = 2;
		}
		//DETERMINANDO VARIAVEL TAMANHO PARA CALCULO DE DIAS NO FOR LOOP ABAIXO
		if( processo == 5 ) {
			tamanho *= 5;
		}
		else if( processo == 8 ) {
			tamanho *= 8;
		}
		else if( processo == 15 ) {
			tamanho *= 15;
		}
		
		//PEDINDO PARA O USUÁRIO DIGITAR A DATA DO REGISTRO E ATRIBUINDO A DATA A dataRegistro
		System.out.printf("%n	      Qual a data do registro (DD/MM/YYYY)? ");
		Date dataRegistro = sdf.parse(sc.next());
		
		//CRIANDO INSTANCIA DA CLASSE Calendar, INSTANCIA = c
		Calendar c = Calendar.getInstance();
		c.setTime(dataRegistro);
		
		//FOR LOOP PARA CHECAR SE O DIA DA SEMANA É SABADO OU DOMINGO, E EFETUANDO CALCULOS PARA IGNORAR AMBOS PARA A CONTAGEM DE DIAS
		for(int i=1; i<=tamanho; i++) {
			if ((c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)  || (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
				c.add(Calendar.DATE, 1);
				i--;
			}
			else {
				c.add(Calendar.DATE, 1);
			}
		}
		
		//IF ADICIONADO PARA PREVENÇÃO DE ERRO CASO O DIA CAISSE EM SABADO, POIS O ULTIMO FOR LOOP IGNORAVA UMA INTERAÇÃO
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			c.add(Calendar.DATE, 2);
		}
		
		//PRINTANDO DATA DO PRAZO NA TELA, SEM FERIADOS.
		System.out.printf("%n-----------------------------------------------------------------------------%n"
				+ "	             Resultado: " + sdf.format(c.getTime()) +
				"%n-----------------------------------------------------------------------------");
		
		//PERGUNTANDO PARA O USUÁRIO SE EXISTEM FERIADOS NO PERIODO DO PRAZO PARA ADICIONAR À CONTA
		System.out.printf("%n%n	       Existem feriados de hoje ao dia " + sdf.format(c.getTime()) + " que caiam em dias úteis(sim/nao)? ");
		String feriado = sc.next();
		
		//PERGUNTANDO A DURAÇÃO EM DIAS DO(S) FERIADO(S)
		if(feriado.equals("sim")) {
			System.out.printf("%n               Qual a duração do(s) feriado(s) em dias? ");
			int duracao = sc.nextInt();
			//CHECANDO SE COM A ATRIBUIÇÃO DOS FERIADOS, O PRAZO PASSARÁ POR MAIS ALGUM FINAL DE SEMANA, CASO SIM EFETUAR O MESMO CALCULO PARA FINAL DE SEMANA ANTERIOR
			for(int i=0; i<=duracao; i++) {
				if ((c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)  || (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
					c.add(Calendar.DATE, 1);
					i--;
				}
				else {
					c.add(Calendar.DATE, 1);
				}
			}
			//SUBTRAÇÃO DE 1 DIA PARA CORREÇÃO DE BUG NO PROGRAMA QUE CAUSAVA O RESULTADO FINAL A ESTAR 1 DIA ADIANTADO
			c.add(Calendar.DATE, -1);
			
			//PRINTANDO DATA DO PRAZO COM FERIADO(S) NA TELA, E FINALIZANDO APLICAÇÃO
			System.out.printf("%n-----------------------------------------------------------------------------%n"
					+ "	             Resultado com feriados inclusos: " + sdf.format(c.getTime()) +
					"%n-----------------------------------------------------------------------------");
		}
		
		System.out.printf("%n%n                   Fim da aplicação.%n");
		
		sc.close();
	}
	
}