@echo off
javac --module-path %PATH_TO_FX% --add-modules=javafx.base,javafx.controls,javafx.fxml src\smtdatabaseapp\*.java -d classes
copy src\smtdatabaseapp\*.fxml classes\smtdatabaseapp\*.fxml
pause