# Calorie Tracker API

Это REST API для отслеживания дневной нормы калорий и учета съеденных блюд.
Он позволяет добавлять пользователейЮ блюда и прием пищи для указанного пользователя.

## Эндпоинты

### 1. Получение пользователя по ID

**Метод:** `GET`  
**URL:** `/api/v1/users/{id}`

#### Пример запроса

```http
GET /api/v1/users/1 HTTP/1.1
```

### 2. Создание нового пользователя

**Метод:** `POST`  
**URL:** `/api/v1/users`

#### Пример запроса

```http
POST /api/v1/users HTTP/1.1

{
	 "name": "name2",
    "email": "email2@mail.ru",
    "age": 20,
    "weight": 51,
    "height": 162,
    "goal": "WEIGHT_GAIN" ИЛИ "MAINTENANCE" ИЛИ "WEIGHT_GAIN"
    "gender": "FEMALE" ИЛИ "MALE"
}
```

### 3. Получение блюда по ID

**Метод:** `GET`  
**URL:** `/api/v1/dishes/{id}`

#### Пример запроса

```http
GET /api/v1/dishes/1 HTTP/1.1
```

### 4. Создание нового блюда

**Метод:** `POST`  
**URL:** `/api/v1/dishes`

#### Пример запроса

```http
POST /api/v1/dishes HTTP/1.1

{
    "name": "dish2",
    "caloriesPerServing": 20,
    "protein": 63,
    "fat": 78,
    "carbohydrates": 44
}
```

### 5. Получение приема пищи по ID

**Метод:** `GET`  
**URL:** `/api/v1/meals/{id}`

#### Пример запроса

```http
GET /api/v1/meals/1 HTTP/1.1
```

### 6. Создание нового приема пищи для указанного пользователя

**Метод:** `POST`  
**URL:** `/api/v1/meals/user/{id}`

#### Пример запроса

```http
POST /api/v1/meals/user/1 HTTP/1.1

  {
      "localDate":  "2024-03-15",
      "dishes": [
          {
            "name": "dish1",
             "caloriesPerServing": 15,
             "protein": 15,
             "fat": 25,
             "carbohydrates": 555
          },
          {
             "name": "dish2",
             "caloriesPerServing": 20,
             "protein": 63,
             "fat": 78,
             "carbohydrates": 44
          }
      ]
  }
```

### 7. Получение отчета за день с суммой всех калорий и приемов пищи

**Метод:** `GET`  
**URL:** `/api/v1/daily/{userId}/{date}`

#### Пример запроса

```http
GET /api/v1/reports/daily/1/2024-03-15/1 HTTP/1.1
```

### 8. Gроверка, уложился ли пользователь в свою дневную норму калорий

**Метод:** `GET`  
**URL:** `/api/v1/calorie-check/{userId}/{date}}`

#### Пример запроса

```http
GET /api/v1/reports/calorie-check/1/2024-03-15 HTTP/1.1
```

### 9. История питания по дням.

**Метод:** `GET`  
**URL:** `/api/v1/nutrition-history/{userId}`

#### Пример запроса

```http
GET /api/v1/reports/nutrition-history/1 HTTP/1.1
```

## Установка

1. Клонируйте репозиторий:
   ```
   git clone https://github.com/Ionina34/CalorieTracking.git
   docker-compose -f docker/docker-compose.yml up --build
   ```
