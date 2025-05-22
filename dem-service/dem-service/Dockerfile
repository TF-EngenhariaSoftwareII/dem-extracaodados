# Use imagem Java
FROM openjdk:21-jdk-slim

# Define diretório de trabalho
WORKDIR /app

# Copia o .jar
COPY target/*.jar app.jar

# Porta padrão do Spring Boot
EXPOSE 8081

# Comando para rodar
ENTRYPOINT ["java","-jar","/app/app.jar"]
