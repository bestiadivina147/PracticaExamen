# Examen

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/Examen-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

Crea un repo en github e compárteo en privado comigo. Realiza un commit coma mínimo ao pasar cada caso test proposto ou non correxiré o teu exame. Realiza un push ao repo remoto en GitHub só cando remates o proxecto.

Crea un proxecto REST Quarkus con Maven. Instala as dependencias do proxecto segundo as necesites.

Situa os arquivos proporcionados: schema.sql y application.properties nos seus directorios correspondentes do proyecto. Non modifiques o contido destes dous arquivos. A base de datos ten que ser H2 executándose en memoria.

Comeza implementando os casos test do arquivo RepoTest.java. Non modifiques o seu código. Implementa as entidades e a capa correspondente ao repositorio ou acceso a datos co patrón que prefiras (Active Record o DAO).

Os casos test do repositorio involucran os contidos mínimos do módulo precisos para superar o módulo.

Unha vez codificado o repositorio, continua cos casos test do arquivo ResourceTest.java para implementar o servicio e o controlador da app. Todas as peticións ao controlador deben pasar polo servizo antes de dirixilas ao método correspondente do repositorio.

Diego Perez Clavero