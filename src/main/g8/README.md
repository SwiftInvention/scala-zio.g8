# $name;format="Camel"$ project

## Usage

- Compile

  ```sh
  sbt compile
  ```
- Run

  ```sh
  sbt $name;format="camel"$/run
  ```

### How to use scalafmt in:

- [VS Code][vscode]
- [Intellij IDEA][intellij]

[vscode]: https://scalameta.org/metals/docs/editors/vscode/
[intellij]: https://scalameta.org/scalafmt/docs/installation.html#intellij

### How to use scalafix in:

- `sbt scalafix ` – to execute linter
- `scalafix RemoveUnused` – to delete unused variables
- `scalafixAll <args>` – invoke scalafix across all configurations where scalafix is enabled
