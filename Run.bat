@echo off
java --module-path %PATH_TO_FX% --add-modules=javafx.controls,javafx.graphics,javafx.fxml -jar app/CustomerManagementGUI.jar
pause