FROM openjdk:17-oracle

ARG LAYERS_DIR=build/layers

COPY ${LAYERS_DIR}/BOOT-INF/lib /springdataredis/lib
COPY ${LAYERS_DIR}/BOOT-INF/classes /springdataredis/classes
COPY ${LAYERS_DIR}/META-INF /springdataredis/META-INF

WORKDIR /springdataredis

EXPOSE  8080

ENTRYPOINT ["java","-cp","./classes:./lib/*","com.endyary.springdataredis.SpringDataRedisApplication"]