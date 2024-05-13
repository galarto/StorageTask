# Четвертое задание

Добавление фильтрации и сортировки

## Product Endpoints

* GET /api/products/{id}    ... Возвращает продукт по параметру id из базы данных
* GET /api/products         ... Возвращает список продуктов, хранящихся в базе данных
* GET /api/products/searcht/ ... Возвращает список продуктов отфильтрованный по названию через параметр, 
при наличии параметра sorted сортирует список по убыванию (по умолчанию по возрастанию)
* GET /api/products/searchp/ ... В зависимости от указанного параметра возращает список продуктов, отфильтрованный по цене,
большей либо равной или меньшей либо равной параметру. При указании параметра sorted сортирует список по убыванию цены(по умолчанию по возрастанию)
* GET /api/products/searchlp/ ... Возвращает список продуктов, отфильтрованный по параметру наличия.
* POST /api/products/search  ... Возвращает список продуктов по фильтрам указанным в request body.
* POST /api/products/       ... Добавляет продукт в базу данных через json параметр
* PUT /api/products/{id}    ... Обновляет информацию о продукте, хранящемся в базе данных по параметру id
* DELETE /api/products/{id} ... Удаляет продукт из базы данных по параметру id

