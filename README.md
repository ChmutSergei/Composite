## Information handling

Создать приложение, разбирающее текст из файла и позволяющее выполнять с текстом три различные операции.

Требования

- Разобранный текст должен быть представлен в виде объекта, содержащего, например, абзацы, предложения, лексемы, слова,
 выражения, символы. Лексема - часть текста, ограниченная пробельными символами. 
 Для организации структуры данных использовать **Composite**.
- Классы с информацией являются классами сущностей и не должны быть перенагружены методами логики.
- Исходный текст всегда корректный. То есть, все предложения начинаются с заглавной буквы и завершаются 
символами «.», «?», «I» или Все абзацы начинаются с символа табуляции или заданного числа пробелов, например 4 пробела.
- Разобранный текст необходимо восстановить в первоначальном виде. Пробелы и знаки табуляции при разборе могут 
заменяться одним пробелом.
- Для деления текста на составляющие следует использовать регулярные выражения. 
Не забывать, что регулярные выражения для приложения являются литеральными константами.
- Код, выполняющий разбиение текста на составляющие части, следует оформить в виде классов-парсеров с использованием 
**Chain of Responsibility**.
- При разработке парсеров, разбирающих текст, необходимо следить количеством создаваемых объектов-парсеров. 
Их не должно быть слишком много.
- Битовые выражения, встречающиеся в тексте, должны быть вычислены. И в итоговый текст (структуру данных) должно войти вычисленное значение. 
Использовать **Interpreter** с применением функциональных интерфейсов.
- Для записи логов использовать Log4J2.
- Созданное приложение должно позволять реализовывать группу задач по работе над текстом (задачи приведены ниже) 
без “переписывания” существующего кода.
- Код должен быть покрыт тестами.
- Класс с методом main в задании должен отсутствовать. Запуск только с применением тестов.

**Функциональные возможности (все реализованы):**

1. Отсортировать абзацы по количеству предложений.

2. Отсортировать слова в предложении по длине.

3. Отсортировать предложения в абзаце по количеству слов.

4*. Отсортировать лексемы в предложении по убыванию количества вхождений заданного символа, 
а в случае равенства - по алфавиту.