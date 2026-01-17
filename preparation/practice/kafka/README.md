# Практика: Kafka

## Цели
- Понять работу producer/consumer и offsets
- Отработать сценарии at-least-once и обработку повторов
- Научиться валидировать сообщения в очереди

## Упражнения
1. Создать topic и отправить 10 сообщений.
2. Прочитать сообщения consumer-ом и зафиксировать offsets.
3. Эмулировать повторную доставку (сброс offset).
4. Проверить порядок сообщений внутри одной партиции.
5. Реализовать проверку идемпотентности обработчика.

## Тестовый стенд Kafka

### Вариант 1: Docker Compose (самый простой для старта)
- Поднять Kafka + Zookeeper.
- Пробросить порты и использовать стандартные настройки.
- Завести отдельный topic для тестов.

### Вариант 2: Testcontainers (для автотестов)
- Использовать `KafkaContainer` в JUnit тестах.
- Поднимать брокер на время прогона.
- Изолировать данные для каждого теста.

### Вариант 3: Локальная установка
- Подходит, если нужно отладить ручные сценарии.
- Удобно для понимания CLI инструментов Kafka.

## Черновики упражнений для стенда

### Docker Compose: практические таски
1. Подготовить `docker-compose.yml` с Kafka + Zookeeper.
2. Поднять стенд и проверить доступность брокера.
3. Создать topic `qa.practice.events` с 3 партициями.
4. Отправить набор сообщений с ключом и без ключа.
5. Прочитать сообщения и проверить распределение по партициям.

Черновик `docker-compose.yml` (как минимум):
```yaml
version: "3.8"
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:7.6.0
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
  kafka:
    image: confluentinc/cp-kafka:7.6.0
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
```

### Testcontainers: практические таски
1. Поднять `KafkaContainer` в JUnit тесте.
2. Создать topic программно в тесте.
3. Отправить сообщения producer-ом и считать их consumer-ом.
4. Зафиксировать offsets и убедиться в повторном чтении при сбросе.
5. Проверить обработку дубликатов в at-least-once.

Черновик JUnit (скелет):
```java
KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.6.0"));
kafka.start();

String bootstrapServers = kafka.getBootstrapServers();
// TODO: создать topic, настроить producer/consumer, отправить сообщения
```

## Что проверить дополнительно
- Таймауты consumer-а и повторная обработка
- Поведение при недоступности брокера
- Обработку dead-letter сценариев (если используются)
