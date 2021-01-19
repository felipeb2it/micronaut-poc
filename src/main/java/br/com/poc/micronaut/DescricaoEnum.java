package br.com.poc.micronaut;

public enum DescricaoEnum {
	
    MERCADO(0, "Supermercado BH"), SACOLAO(1, "Sacolão Central"), 
    YBER(2, "Yber - Transporte"), YFOOD(3, "Yfood - Delivery"),
    AMAZUN(4, "Amazun - Store"), CVB(5, "CVB - Viagens"),
    PARMHA(6, "Droga Silva - Farmacia"), PHAT(7, "Pizza Hat - Restaurante"),
    CHINAREST(8, "Casa Chinesa - Restaurante"), CHURRA(9, "No espeto - Churrascaria");

    private final Integer key;
    private final String value;

    DescricaoEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
}
