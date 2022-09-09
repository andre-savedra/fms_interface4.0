start "win_title" java -jar C:\FMS\MES\fms_interface4.0\jar-local\FmsInterfaceWeb-0.0.2-SNAPSHOT.jar

msg * "FMS EM MODO LOCAL!!! MANTENHA A JANELA DE PROMPT DE COMANDO ABERTA PARA MANTER O SERVIDOR LIGADO  ;)"

timeout 4

start "" http://localhost:8080