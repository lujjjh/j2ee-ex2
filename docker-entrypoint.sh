#!/bin/bash

: ${DB_HOST:=$MYSQL_PORT_3306_TCP_ADDR}
: ${DB_PORT:=$MYSQL_PORT_3306_TCP_PORT}
: ${DB_USER:=${MYSQL_ENV_MYSQL_USER:-root}}
if [ "$DB_USER" = 'root' ]; then
    : ${DB_PASSWORD:=$MYSQL_ENV_MYSQL_ROOT_PASSWORD}
fi
: ${DB_PASSWORD:=$MYSQL_ENV_MYSQL_PASSWORD}
: ${DB_NAME:=$MYSQL_ENV_MYSQL_DATABASE}

export DB_HOST=$DB_HOST
export DB_PORT=$DB_PORT
export DB_USER=$DB_USER
export DB_PASSWORD=$DB_PASSWORD
export DB_NAME=$DB_NAME

java -jar /migrate.jar

exec "$@"
