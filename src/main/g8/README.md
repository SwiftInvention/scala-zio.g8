# $name;format="Camel"$ project

## Prerequisites

- `sbt`
- Docker, `docker-compose`

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

### How to use scalafmt

- With [VS Code][vscode]
- With [Intellij IDEA][intellij]

[vscode]: https://scalameta.org/metals/docs/editors/vscode/
[intellij]: https://scalameta.org/scalafmt/docs/installation.html#intellij

### How to use scalafix

- `sbt scalafix` – to execute linter
- `scalafix RemoveUnused` – to delete unused variables
- `scalafixAll <args>` – invoke scalafix across all configurations where scalafix is enabled
