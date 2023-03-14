Автотесты для сервиса Yandex Polygon


## Документация

- [Jenkins]
- [Confluence]
- [Чек-лист приоритетов автотестов]
- [Тест-кейсы]



## Запуск тестов

Запуска регрессионного набора тестов
```bash
  maven test
```
Можно запускать тесты по тэгам
```bash
  maven test -Dgroups=smoke
```
Или исключать из запука тесты по тэгу
```bash
  maven test -DexcludedGroups=
```
## Tree
```
───src                                        #
   ├───main                                   #
   │   ├───java                               #
   │   |                                      #
   └───test                                   #
       ├───java                               #
       │   ├─── services                      #Утилитные классы
       │   └─── Тестовые классы               #Тестовые классы
       └───resources                          #Ресурсы test секции
```