# $name;format="Camel"$ project

## Prerequisites

- `sbt`
- Docker, `docker-compose`
- OpenJDK 11 or above

## Setting up

### Scalafmt Editor support

- [VS Code][vscode]
- [Intellij IDEA][intellij]

[vscode]: https://scalameta.org/metals/docs/editors/vscode/
[intellij]: https://scalameta.org/scalafmt/docs/installation.html#intellij

## Usage

- Start docker containers

  Note: flags and options mean:

  - use detached mode
  - wait for all containers to be healthy
  - remove volumes on exit

  ```sh
  docker-compose up -Vd --wait
  ```

- Run

  ```sh
  sbt $name;format="camel"$/run
  ```
- Run in live reload mode

  ```sh
  sbt ~$name;format="camel"$/reStart
  ```

- When done, remove docker containers, networks and volumes

  ```sh
  docker-compose down -v
  ```

- Clean build artifacts and recompile (just in case)

  ```sh
  sbt clean compile
  ```

- Check for dependency updates (just in case)

  - Uncomment `sbt-dependency-updates` in [`project/plugins.sbt`](project/plugins.sbt)
  - ```sh
    sbt dependencyUpdates
    ```
### Swagger-UI

- Swagger documentation: http://localhost:8080/docs

### Scalafix

- `sbt scalafix` – run linter, check all files, fail on warnings

### Additional resources

[Setting up debugger in Intellij IDEA](/docs/intellij-idea-setup.md)