
Было выполнено:
1)	Плагин-мониторинг состояния web-сайта
2)	Плагин-мониторинг состояния файла
3)	Плагин-помошник, запускает остальные плагины и позволяет их выводить на экран. (Взаимодействует с другими плагинами, то есть, если Вы напишите свой, то тоже сможете его подключить) 
Как выполнен мониторинг сайта:
Сайт мониторится по параметрам, заданным в link.txt
Нужно указать: url(адрес сайта), name(имя заголовка, которое будет в таблицы), class(по которому будем мониторить), также можно указать period, через который сайт будет проверяться.(стандартно сутки)
Пример:
url:mail.ru
name:mail   
class:span[class=quotations__item__rate] Рис. 1 поиск класса для примера
вывод производится в ../public_html/out/SitesMonitoring.html

•	Не обязательно должен быть именно класс. Можно ввести любой тэг[class/id=name]
Пример: a[id=idOfA]
Если что-то изменилось, содержимое файла сразу меняется, показывается всегда только актуальная информация

Как выполнен мониторинг файла:
Файл мониторится весь, необходимо задать параметры в test.properties:
1)	fname – имя файла
2)	name – имя заголовка для файла(как будет отображаться)
3)	period – период проверки файла
пример: 
fname: test.txt
name: test
period: 5000
При каких-либо изменениях в файле, выводится для каждого изменения:
1)	Строка до изменения.
2)	Строка после изменения.
Файл не меняется
Чтобы мониторинг файла работал, его следует поместить в директорию с .jar файлом, задать в test.properties необходимые параметры и запустить.
Вывод изменений производится в :
../public_html/out/fileListener.html

Как объединить Ваши плагины в обработчик плагинов.
1)	Ваш плагин должен выводить свой ответ в формате html в ../public_html/out/имяВашегоJarФайла.html

Пример: ваш jar файл называется Test.jar, находится в директории C:/Users/Test/Test.jar

Ответ должен быть в 
C:/Users/Test/public_html/out/Test.html

2)	Прописать в start.txt имя вашего jar файла (start.txt находится в папке тестовое/jar ответ/обработчик плагинов

Чтобы посмотреть работу плагина, необходимо:
1.Запустить из командной строки плагин:
 рис 2. Запуск плагина из командной строки
2. зайти на http://localhost:8080/, нажать 
active monitors
рис 3. Кнопка «active monitors»
Выбрать плагины, которые должны работать, нажать submit, или просмотреть работу плагина, нажав на его имя:
 
Рис 4. Просмотр плагинов

 Рис 5. Просмотр работы плагина

Недочеты:
При запуске главного плагина, он открывает остальные, но при закрытии не закрывает. Закрывает только если  



Спасибо за внимание!

