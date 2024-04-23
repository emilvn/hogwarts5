# Hogwarts 5

## Test af prefect funktioner

#### POST /prefects - modtager en student (eller et student-id) og udnævner vedkommende til prefect
Kan testes ved at sende et POST request til `/prefects` med et student JSON objekt med minimum `id`. Studenten med det givne id skal være 5. års eller højere.
```json
{
    "id": 2
}
```

### DELETE /prefects/:id - fratager den pågældende student rollen som prefect.
Kan testes ved at sende et DELETE request til `/prefects/{id}`. Studenten med det givne id skal være en prefect.

### GET /prefects - returnerer en liste over alle prefects i alle houses
Kan testes ved at sende et GET request til ´/prefects´.

### GET /prefects/:id - returnerer en prefect (ud fra student-id) hvis den pågældende student er prefect
Kan testes ved at sende et GET request til ´/prefects/{id}´. Id skal være id'et på en student der er prefect.

### GET /prefects/house/{house} - returnerer en liste over alle prefects i det house
Kan testes ved at sende et GET request til ´/prefects/house/{house}´. House skal være navnet på et af de 4 houses.

### /students skal ligeledes have en PATCH request for at tilføje/fjerne prefect udnævnelsen - men bruge samme regler som /prefects
Kan testes ved at sende et PATCH request til `/students/{id}` med et student JSON objekt med minimum `id` og `isPrefect`. Studenten med det givne id skal være 5. års eller højere.
```json
{
    "id": 2,
    "isPrefect": true
}
```
