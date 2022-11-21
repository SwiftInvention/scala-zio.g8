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

- Look to the stdout after you run the App, there will be link to documentation page like:
 `Go to http://localhost:\${serverStart.port}/docs`

### Metrics by Prometheus

- A default endpoint has a default Prometheus configuration to collect metrics (prometheusMetrics, metricsEndpoint)
- After you run the App you can take a look to the stdout where some metrics will be shown. For example:

```
Request: GET /docs/docs.yaml, handled by: GET /docs/docs.yaml, took: 1ms; response: 200
```

### Scalafix

- `sbt scalafix` – run linter, check all files, fail on warnings
