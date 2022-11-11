
## Template for projects

A [Giter8][g8] template for some sbt projects

### Including dependencies:
* sbt
* zio 1
* quill
* mysql jdbc
* tapir
* circe
* zio-http
* zio-test
* testcontainers  

### Structure of project:  
```
$name$ of project
├── src  
│   └── main  
│   │    └── resources
│   │    │   └── db
│   │    │       └── migration
│   │    └── scala  
│   │        └── $organization$
│   └── test 
│        └── resources
│        └── scala
│            └── $organization$
pipeline
│
project
├── build.properties
build.sbt

```

### Setting up the project:  
```
sbt new SwiftInvention/scala-zio.g8
# then follow interactive process to choose project name and other parameters
```  


[g8]: http://www.foundweekends.org/giter8/