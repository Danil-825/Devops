﻿# DevOps Project
﻿# Мониторинг приложения
Было разработано веб-приложение на Spring Boot
В проекте приложения были созданы докерфайл и docker-compose файл, также yml-файлы для Prometheus, Grafana, Loki
![image](https://github.com/user-attachments/assets/67b0c655-959a-4bf3-b2f5-f397284e8da6)
страница сервера localhost:8080
![image](https://github.com/user-attachments/assets/f95b5e65-602a-4705-b1f6-6e7cee274ad4)
Создание эндпойнтов на порту localhost:9090/targets (Prometheus)
![image](https://github.com/user-attachments/assets/612438fc-5a94-45c1-acf3-a7435e12c3ee)
Привязка Prometheus и Loki к Grafana 
![image](https://github.com/user-attachments/assets/8f566bad-d3ec-4e72-8f1c-8673665f610a)
Создание панелей для вывода информации на дашборде
Для создания панелей необходимо привязать к Prometheus или Loki (Data Source), затем выбрать метрики, которые будут показывать характеристики сервера в различной форме.
![image](https://github.com/user-attachments/assets/ed7899b6-e11a-44a8-9d4d-9eb4247efa56)
Создание панелей для вывода информации на дашборде
![image](https://github.com/user-attachments/assets/7645cf92-3d5e-454d-a871-78abce2233a5)
Созданный дашборд
Для того, чтобы увеличить нагрузку на сервер можно увеличить коэффициент в метриках, чтобы значение возросло в разы. Когда нагрузка будет увеличена более чем на 80%, тогда на адрес электронной почты придет оповестительное письмо
![image](https://github.com/user-attachments/assets/21222ba4-39d7-46b2-aa9d-49225765e148)
Увеличение нагрузки на сервер
![image](https://github.com/user-attachments/assets/c2d4baed-6065-432e-87aa-99a050b89f9a)
Оповещение о большой нагрузке на электронную почту

﻿# Nginx
 Были созданы и запущены три контейнера докера с проектами на разных портах: Java Spring, Python, PHP Laravel. Java Spring запускался на порту 8080, Python на порту 9000, а PHP Laravel – 8000.
 ![image](https://github.com/user-attachments/assets/ce24e99b-b59a-45c7-8d66-30f0aa0acd20)
Запуск контейнеров
![image](https://github.com/user-attachments/assets/22ba9cf3-48f9-4f29-bee4-e50c7612d56d)
Проверка запуска приложений
Далее был установлен Nginx со всеми обновлениями. В папке конфигурационных файлов был создан файл, в который вписан код для управления серверами. Сначала введем для всех портов одинаковый вес (weight = 1)
![image](https://github.com/user-attachments/assets/06f1ad2c-bbbf-4129-bebe-d459f16ea15d)
Код конфигурационного файла
При запуске серверов соотношение запусков приложений оказалось 3:3:4
![image](https://github.com/user-attachments/assets/5c12f9dd-9a61-4b6e-b99b-45d0158f41e5)
Теперь изменим вес каждого порта и посмотрим, как поменяется соотношение запусков приложений. Для этого поменяем вес у портов в соотношение 1:2:4 и поставим в цикле 70 запусков, чтобы было наглядно видно что получится:
upstream backend {
    least_conn;  
    server localhost:8000 weight=1;  #PHP
    server localhost:9000 weight=2;  #Python
    server localhost:8080 weight=4;  #Java
}
В итоге количество запусков примерно совпало с соотношением 1:2:4
![image](https://github.com/user-attachments/assets/aa3887c9-1983-4e86-8ff6-90d9d2c66220)








