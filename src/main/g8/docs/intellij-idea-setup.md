### Setting up debugger in Intellij IDEA

* Ensure that debugging is enabled for sbt
  ![sbt config](images/idea-sbt-settings.png)
* Create a run configuration from the "sbt task" template
  ![debug configuration template](images/idea-debug-configuration-template.png)
* Set the task to be `$name;format="camel"$/run`
  ![debug configuration](images/idea-debug-configuration.png)
    