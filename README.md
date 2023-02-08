# Магазин цветов

Простое приложение с графическим интерфейсом и связью с БД

# Зависимости

* Java 19 SDK

# Сборка проекта


```bash
# mvnw script like mvn utility
$ bash mvnw install
$ bash mvnw dependency:copy-dependencies
```

# Запуск
После сборки
```
$ DB_HOST=localhost \
DB_PORT=3306 \
DB_NAME=demo_up04 \
DB_USER=root \
DB_PASS=root \
java --module-path=target/dependency/ --add-modules javafx.controls,javafx.fxml -cp target/flower_app-1.0-SNAPSHOT.jar com.example.flower_app.Main
```

# JDK 11
Для перехода на ветку с JDK 11

`git checkout jdk11`