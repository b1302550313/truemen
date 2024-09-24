FROM mysql
LABEL authors="Ethan"

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY ./database/init1.sql /docker-entrypoint-initdb.d/
COPY ./database/init2.sql /docker-entrypoint-initdb.d/
COPY ./database/init3.sql /docker-entrypoint-initdb.d/