package br.com.poc.micronaut.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Singleton;

import br.com.poc.micronaut.DescricaoEnum;
import br.com.poc.micronaut.model.TransacaoModel;

@Singleton
public class ValuesService {
	
	private Map<Long, List<TransacaoModel>> historicoTrans;
	private List<Integer> meses;
	private int quantMeses;

	public List<TransacaoModel> preencheTransacoes(int id, short ano, short mes) {
		
		long indiceConcat = Long.valueOf(id + "" + ano + "" + "" + mes);
		
		if(historicoTrans.containsKey(indiceConcat)) {
			return historicoTrans.get(indiceConcat);
		}
		
		int quantMensal = ThreadLocalRandom.current().nextInt(1, 120);
		
		int transRepetida = ThreadLocalRandom.current().nextInt(1, quantMensal);
		List<TransacaoModel> transacoes = new ArrayList<>();
		
		int cont = 0;
		do {
			TransacaoModel transacao = new TransacaoModel();
			int randomDes = ThreadLocalRandom.current().nextInt(0, 10);
			 
			transacao.setDescricao(DescricaoEnum.values()[randomDes].getValue());
			
			YearMonth yearMonthObject = YearMonth.of(ano, mes);
			int daysInMonth = yearMonthObject.lengthOfMonth();
			
			int randomDia = ThreadLocalRandom.current().nextInt(1, daysInMonth + 1);
			
			LocalDate data = LocalDate.of(ano, mes, randomDia);
			
			Timestamp timestamp = Timestamp.valueOf(data.atStartOfDay());
			transacao.setData(timestamp.getTime());
			
			int randomValor = ThreadLocalRandom.current().nextInt(-9999999, 10000000);
			
			transacao.setValor(randomValor);
			int tamanhoTrans = transacoes.size();
			do {
				transacoes.add(transacao);
				transacao = new TransacaoModel(transacao.getDescricao(), transacao.getData(), transacao.getValor());
				transacao.setDuplicated(true);
			} while(transRepetida == cont && meses.stream().anyMatch(item -> item == mes) && transacoes.size() == (tamanhoTrans + 1));
			cont++;
		} while(cont < quantMensal);
		
		
		historicoTrans.put(indiceConcat, transacoes);
		return transacoes;
	}
	
	public ValuesService() {
		historicoTrans = new HashMap<>();
		meses = new ArrayList<>();
		quantMeses = ThreadLocalRandom.current().nextInt(3, 13);
		meses = Arrays.asList(randomNumbersWithoutRepetition(1, 12, quantMeses));
	}
	
	public Integer[] randomNumbersWithoutRepetition(int start, int end, int count) {
	    Random rng = new Random();

	    Integer[] result = new Integer[count];
	    int cur = 0;
	    int remaining = end - start;
	    for (int i = start; i < end && count > 0; i++) {
	        double probability = rng.nextDouble();
	        if (probability < ((double) count) / (double) remaining) {
	            count--;
	            result[cur++] = i;
	        }
	        remaining--;
	    }
	    return result;
	}
	
}
