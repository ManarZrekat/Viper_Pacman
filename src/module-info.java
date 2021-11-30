module Project {
	requires java.desktop;
	requires javafx.base;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.controls;
	requires org.junit.jupiter.api;
	requires junit;
//
	exports Controller;
	exports Model;
	exports View;
	opens Controller to javafx.graphics,javafx.fxml;
	opens Model to javafx.base, javafx.fxml;
	opens View to javafx.graphics, java.desktop;

}