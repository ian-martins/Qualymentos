# Usa uma imagem base leve com Java
FROM openjdk:25-jdk-slim

# Diretório dentro do container
WORKDIR /app

# Copia o .jar do build do Spring Boot para dentro do container
COPY target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar o app
ENTRYPOINT ["java", "-jar", "app.jar"]