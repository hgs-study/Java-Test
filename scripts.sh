## test db
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=test1234 -d -p 3307:3307 mysql:latest