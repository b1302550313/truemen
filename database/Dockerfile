FROM mysql
LABEL authors="Ethan"

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY ./database/init.sql /docker-entrypoint-initdb.d/