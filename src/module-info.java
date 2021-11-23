module Project {
	requires java.desktop;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
//
	exports Controller;
	exports Model;
	opens Controller to javafx.graphics,javafx.fxml;
	opens Model to javafx.base, javafx.fxml;
}