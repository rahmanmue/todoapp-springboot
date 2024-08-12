# Ambil image maven yang menggunakan open jdk 17
FROM maven:3.8.8 AS build

# Atur label
MAINTAINER enigmacamp.com


# Definisikan argument build untuk mengganti properties di aplikasi spring boot
ARG APP_PORT=8081
ARG HOST_DB=localhost
ARG PORT_DB=5432
ARG DB_NAME=postgres
ARG USERNAME_DB=postgres
ARG PASSWORD_DB=postgres123

# Atur dir kerja yang di maven
WORKDIR /app

# Buat tag
LABEL version="0.0.1-SNAPSHOT"


# Salin file pom.xml ke direktori kerja
COPY pom.xml .

# Salin source ke direktori kerja
COPY src ./src

# Build Aplikasi dengan maven dan tambahkan argumen untuk mengganti properties
RUN mvn clean package -DskipTests \
    -DAPP_PORT=${APP_PORT} \
    -DHOST_DB=${HOST_DB} \
    -DPORT_DB=${PORT_DB} \
    -DDB_NAME=${DB_NAME} \
    -DUSERNAME_DB=${USERNAME_DB} \
    -DPASSWORD_DB=${PASSWORD_DB}

# Gunakan image openjdk 11
FROM openjdk:17-jdk-alpine

# Atur dir kerja
WORKDIR /app

# salin file jar aplikasi dari image buikd sebelumnya kedalam image runtime
# COPY target/todoapp-0.0.1-SNAPSHOT.jar /app/todoapp-0.0.1-SNAPSHOT.jar
COPY --from=build /app/target/todoapp-0.0.1-SNAPSHOT.jar ./todoapp-0.0.1-SNAPSHOT.jar

# Eksekusi Aplikasi
CMD ["java", "-jar", "./todoapp-0.0.1-SNAPSHOT.jar"]

# Port yang digunakan
EXPOSE 8081