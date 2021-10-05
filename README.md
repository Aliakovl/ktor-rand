# ktor-rand

## Варианты запуска ktor-rand

1) Build: ``./gradlew build && ./gradlew installDist``

    Run: ``./build/install/ktor-rand/bin/ktor-rand --port 8080``

2) Build and run in docker container: ``./run.docker.sh``

### Параметры запуска сервера:

  ``--port`` ``0..65536``

### Примеры запросов:

``curl -i -X GET "localhost:8080/?begin=a&end=b"``,

``curl -i -X GET "localhost:8080/?length=l"``,

где _a_, _b_ и _l_ &mdash; числа от 0 до 2147483647.
