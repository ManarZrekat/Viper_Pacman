module Project {
	requires java.desktop;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
//
	exports Controller;
	opens Controller to javafx.graphics,javafx.fxml;
//	//opens Controller to javafx.fxml;
}