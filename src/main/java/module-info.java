module sp.senac.br.pizzaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    requires jdk.jfr;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens sp.senac.br.pizzaria.controller to javafx.fxml;
    opens sp.senac.br.pizzaria.model to javafx.base, org.hibernate.orm.core;
    exports sp.senac.br.pizzaria;
}