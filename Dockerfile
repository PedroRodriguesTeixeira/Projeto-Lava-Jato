FROM mysql:8.0
ENV MYSQL_ROOT_PASSWORD=root
COPY crud_lavajato.sql /docker-entrypoint-initdb.d/
