package br.com.poc.micronaut.controller;

import java.time.Year;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import br.com.poc.micronaut.service.ValuesService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/desafio/api")
public class TransacoesController {

	@Inject
	ValuesService values;
	
	private Map<String, Object> erro = new HashMap<>();
	
    @Get(uri = "/{id}/transacoes/{ano}/{mes}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Object> transacoes(int id, short ano, short mes) {
    	
    	if(id < 1000 || id > 100000) {
    		badRequest("O ID deve ser um número de 1.000 a 100.000");
            return HttpResponse.status(HttpStatus.BAD_REQUEST).body(erro);
    	} else if(ano > Year.now().getValue()) {
            badRequest("Ano deve ser menor igual ao atual");
            return HttpResponse.status(HttpStatus.BAD_REQUEST).body(erro);
    	} else if(ano >= Year.now().getValue() && mes > YearMonth.now().getMonthValue()) {
    		badRequest("Mês deve ser menor igual ao atual");
            return HttpResponse.status(HttpStatus.BAD_REQUEST).body(erro);
    	}
    	
        return HttpResponse.ok(values.preencheTransacoes(id, ano, mes));
    }
    
    private void badRequest(String mensagem) {
    	erro.put("status", 401);
    	erro.put("message", mensagem);
        erro.put("error", "Bad Request");
    }
	
	
}
