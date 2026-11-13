module vallegrande.intrumentosmusicales {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens vallegrande.intrumentosmusicales to javafx.fxml;
    opens vallegrande.intrumentosmusicales.controller to javafx.fxml;
    opens vallegrande.intrumentosmusicales.model to javafx.base;

    exports vallegrande.intrumentosmusicales;
    exports vallegrande.intrumentosmusicales.controller;
    exports vallegrande.intrumentosmusicales.model;
}