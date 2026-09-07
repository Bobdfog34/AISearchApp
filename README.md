# AI Search App 🤖

Это полноценное Android приложение с искусственным интеллектом, которое ищет информацию в интернете и предоставляет ответы на вопросы пользователя.

## Особенности

✅ **Чат с AI** - естественный диалог с ботом
✅ **Поиск в интернете** - использует Google Custom Search API
✅ **Работает без подключки** - кэширует результаты
✅ **Простой интерфейс** - удобный чат-интерфейс
✅ **Быстрый поиск** - асинхронный поиск в фоне

## Требования для запуска

1. **Android Studio** (последняя версия)
2. **Android SDK 21+**
3. **Gradle 8.0+**

## Настройка API

Приложение использует **Google Custom Search API**. Для работы:

### Шаг 1: Получи API ключ
1. Перейди на https://programmablesearchengine.google.com/
2. Создай новый поисковик
3. Получи `API KEY` и `SEARCH ENGINE ID`

### Шаг 2: Добавь ключи в приложение
Открой файл `app/src/main/kotlin/com/example/aisearchapp/data/repository/SearchRepository.kt`

Замени эти строки:
```kotlin
private val API_KEY = "AIzaSyDemoKeyChangeThis"
private val SEARCH_ENGINE_ID = "demoSearchEngineId"
```

На свои реальные ключи:
```kotlin
private val API_KEY = "твой_реальный_api_key"
private val SEARCH_ENGINE_ID = "твой_реальный_search_engine_id"
```

## Как запустить

### На эмуляторе:
```bash
./gradlew runDebug
```

### На физическом устройстве:
```bash
./gradlew installDebug
```

### Собрать APK для релиза:
```bash
./gradlew assembleRelease
```

APK файл будет в папке `app/build/outputs/apk/release/`

## Структура проекта

```
app/src/main/
├── kotlin/com/example/aisearchapp/
│   ├── MainActivity.kt                    # Главная активность
│   ├── data/
│   │   ├── api/SearchApiService.kt       # API сервис
│   │   ├── models/                        # Модели данных
│   │   └── repository/SearchRepository.kt # Репозиторий для работы с API
│   └── ui/
│       ├── adapters/ChatAdapter.kt       # Адаптер для чата
│       └── viewmodel/ChatViewModel.kt    # ViewModel
└── res/
    ├── layout/                            # XML разметки
    ├── drawable/                          # Иконки и фоны
    └── values/                            # Ресурсы (цвета, строки)
```

## Как это работает?

1. **Пользователь вводит вопрос** в поле ввода
2. **Приложение отправляет запрос** к Google Custom Search API
3. **API возвращает результаты поиска** (ссылки, заголовки, описания)
4. **Приложение обрабатывает результаты** и показывает их пользователю
5. **Результаты отображаются** в виде чата

## Возможные улучшения

- [ ] Интеграция с ChatGPT API для более умных ответов
- [ ] Сохранение истории чатов
- [ ] Поддержка голосовых команд
- [ ] Темная тема
- [ ] Настройки приложения

## Лицензия

MIT

## Поддержка

Если у тебя есть проблемы или вопросы, открой Issue на GitHub!

---

**Автор:** Bobdfog34
**Язык:** Kotlin + Android
**Статус:** В разработке 🚀