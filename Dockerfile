FROM java:8
EXPOSE 9000

VOLUME /tmp
ADD ./target/vpn500-0.0.1-SNAPSHOT.jar /app.jar
RUN bash -c 'touch /app.jar'
RUN /sbin/ip route|awk '/default/ { print  $3,"\tdockerhost" }' >> /etc/hosts
ENTRYPOINT ["java","-jar","/app.jar"]
