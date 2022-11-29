@echo off
md app
jar --create --file=app/CustomerManagementGUI.jar --main-class=smtdatabaseapp.CustomerManagementGUI -m Manifest.txt -C classes .
md app\lib
copy lib\mysql-connector-java.jar app\lib 
Pause