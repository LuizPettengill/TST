/*
AUTOR: LUIZ GUILHERME PALHARES PETTENGILL
EMAIL: LUIZPETTENGILL@HOTMAIL.COM

FUN��O DA APLICA��O: CALCULO DE PRAZO RECURSAL, SEGUINDO AS LEIS ATUAIS.
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

		System.out.println("Esse programa ir� calcular em dias �teis o ultimo dia para "
				+ "interposi��o de recursos a partir do prazo recursal informado pelo usu�rio.");
		
		//PEDINDO PARA O USU�RIO ESCOLHER O PRAZO RECURSAL
		System.out.println("Escolha o prazo recursal(em dias):");
		System.out.printf("%n		[5]    [8]    [15]: ");
		int processo = sc.nextInt();		
		
		//CHECANDO SE O PRAZO � EM DOBRO
		System.out.printf("%n              O prazo � em dobro(sim/nao)? ");
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
		
		//PEDINDO PARA O USU�RIO DIGITAR A DATA DO REGISTRO E ATRIBUINDO A DATA A dataRegistro
		System.out.printf("%n	      Qual a data do registro (DD/MM/YYYY)? ");
		Date dataRegistro = sdf.parse(sc.next());
		
		//CRIANDO INSTANCIA DA CLASSE Calendar, INSTANCIA = c
		Calendar c = Calendar.getInstance();
		c.setTime(dataRegistro);
		
		//FOR LOOP PARA CHECAR SE O DIA DA SEMANA � SABADO OU DOMINGO, E EFETUANDO CALCULOS PARA IGNORAR AMBOS PARA A CONTAGEM DE DIAS
		for(int i=1; i<=tamanho; i++) {
			if ((c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)  || (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
				c.add(Calendar.DATE, 1);
				i--;
			}
			else {
				c.add(Calendar.DATE, 1);
			}
		}
		
		//IF ADICIONADO PARA PREVEN��O DE ERRO CASO O DIA CAISSE EM SABADO, POIS O ULTIMO FOR LOOP IGNORAVA UMA INTERA��O
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			c.add(Calendar.DATE, 2);
		}
		
		//PRINTANDO DATA DO PRAZO NA TELA, SEM FERIADOS.
		System.out.printf("%n-----------------------------------------------------------------------------%n"
				+ "	             Resultado: " + sdf.format(c.getTime()) +
				"%n-----------------------------------------------------------------------------");
		
		//PERGUNTANDO PARA O USU�RIO SE EXISTEM FERIADOS NO PERIODO DO PRAZO PARA ADICIONAR � CONTA
		System.out.printf("%n%n	       Existem feriados de hoje ao dia " + sdf.format(c.getTime()) + " que caiam em dias �teis(sim/nao)? ");
		String feriado = sc.next();
		
		//PERGUNTANDO A DURA��O EM DIAS DO(S) FERIADO(S)
		if(feriado.equals("sim")) {
			System.out.printf("%n               Qual a dura��o do(s) feriado(s) em dias? ");
			int duracao = sc.nextInt();
			//CHECANDO SE COM A ATRIBUI��O DOS FERIADOS, O PRAZO PASSAR� POR MAIS ALGUM FINAL DE SEMANA, CASO SIM EFETUAR O MESMO CALCULO PARA FINAL DE SEMANA ANTERIOR
			for(int i=0; i<=duracao; i++) {
				if ((c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)  || (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
					c.add(Calendar.DATE, 1);
					i--;
				}
				else {
					c.add(Calendar.DATE, 1);
				}
			}
			//SUBTRA��O DE 1 DIA PARA CORRE��O DE BUG NO PROGRAMA QUE CAUSAVA O RESULTADO FINAL A ESTAR 1 DIA ADIANTADO
			c.add(Calendar.DATE, -1);
			
			//PRINTANDO DATA DO PRAZO COM FERIADO(S) NA TELA, E FINALIZANDO APLICA��O
			System.out.printf("%n-----------------------------------------------------------------------------%n"
					+ "	             Resultado com feriados inclusos: " + sdf.format(c.getTime()) +
					"%n-----------------------------------------------------------------------------");
		}
		
		System.out.printf("%n%n                   Fim da aplica��o.%n");
		
		sc.close();
	}
	
}