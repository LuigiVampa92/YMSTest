# YMSTest
Тестовое задание для школы мобильной разработки Яндекс в 2018 г. 

# Сборка
Собирается просто:
```
git clone https://github.com/LuigiVampa92/YMSTest.git
cd YMSTest
./gradlew assembleDebug
```

# Технологии

Приложение написано полностью на kotlin. Я постарался применить и продемонстрировать использование различных технологий, в том числе тех, которые для такого маленького проекта вообще-то избыточны, но тестовое задание на то и тестовое задание.
Код для OAuth авторизации в сервисах яндекса взял здесь: https://github.com/yandexmobile/yandex-login-sdk-android
Почему то не нашёл эту библиотеку в публичных репозиториях, поэтому добавил модулем в код (именно она отображается в статистике как Java код)
