# syntax=docker/dockerfile:1.3.1

FROM postgres:11 as build

#ADD dump.tar.gz /docker-entrypoint-initdb.d/
COPY postgresql.conf /etc/postgresql/postgresql.conf
