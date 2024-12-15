# Etapa de build: Use uma imagem Maven para compilar
FROM maven:3.9.5-eclipse-temurin-17 as build

WORKDIR /app-imoveis
COPY pom.xml /app-imoveis
RUN mvn dependency:go-offline
COPY . /app-imoveis
RUN mvn clean package -DskipTests

# Etapa final: Use uma imagem JRE mais leve
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app-imoveis
COPY --from=build /app-imoveis/target/app-imoveis.jar app-imoveis.jar

# Execute a aplicação
ENTRYPOINT ["java", "-jar", "app-imoveis.jar"]