# beerstore
Springboot + Docker + AWS

Linha de comando banco docker 
- docker exec -it beerdb bash

Criar banco postgres:
- docker run -p 5432:5432 --name beerdb -e POSTGRES_USER=beerstore -e POSTGRES_PASSWORD=beerstore -e POSTGRES_DB=beerstore -d postgres:10.5-alpine