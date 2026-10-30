package sp.senac.br.pizzaria.model;

public enum FormaPagamento {
    CARTAO_CREDITO("Crédito"),
    CARTAO_DEBITO("Débito"),
    DINHEIRO("Dinheiro"),
    PIX("Pix"),
    VOUCHER("Voucher");

    private String descricao;

    FormaPagamento(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return this.descricao;
    }
}
