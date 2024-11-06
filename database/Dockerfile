FROM mysql
LABEL authors="Ethan"

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY ./database/TruemenData.sql /docker-entrypoint-initdb.d/