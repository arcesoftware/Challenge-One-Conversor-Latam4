module com.example.aluracurrencyconverter {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.aluracurrencyconverter to javafx.fxml;
    exports com.example.aluracurrencyconverter;
}