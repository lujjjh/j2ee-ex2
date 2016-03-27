FROM lujjjh/docker-maven-tomcat

ENV DB_NAME ex2

RUN mkdir -p /build
ADD . /build
RUN cd /build && mvn compile war:war && \
    rm -rf /usr/local/tomcat/webapps/ROOT && \
    cp /build/target/*.war /usr/local/tomcat/webapps/ROOT.war && \
    rm -rf /build

COPY ./docker-entrypoint.sh /
ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["catalina.sh", "run"]
