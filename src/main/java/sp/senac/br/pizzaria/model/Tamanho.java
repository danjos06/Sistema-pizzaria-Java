package sp.senac.br.pizzaria.model;

public enum Tamanho {
    BROTO("Broto", 4),
    MEDIA("Media", 6),
    GRANDE("Grande", 8);

    private String descricao;
    private int pedacos;

    Tamanho(String descricao, int pedacos){
        this.descricao = descricao;
        this.pedacos = pedacos;
    }

    @Override
    public String toString() {
        return this.descricao + ", " + pedacos + " pedaços";
    }
}