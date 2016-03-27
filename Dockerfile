FROM lujjjh/docker-maven-tomcat

ENV DB_NAME ex2

RUN mkdir -p /build
ADD . /build
RUN cd /build && mvn package && \
    rm -rf /usr/local/tomcat/webapps/ROOT && \
    cp /build/ex2-database-migration/target/*-with-dependencies.jar /migrate.jar && \
    cp /build/ex2-web/target/*.war /usr/local/tomcat/webapps/ROOT.war && \
    rm -rf /build

COPY ./docker-entrypoint.sh /
ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["catalina.sh", "run"]
