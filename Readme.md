
# Knime Coding Challenges

## Requirements
* java-version: 17
* maven-version: 18

## Command
### Tests
``` bash
mvn test
```

### Build
``` bash
mvn install
```

### Run
``` bash
 sudo java -cp ./target/knime-coding-challenge-1.0-SNAPSHOT.jar org.pedrocarlos.Main --input /home/mpedroc90/Documents/personal/knime-coding-challenge/src/main/resources/example-file.txt --inputtype int --operations neg,reverse --threads 1 --output src/main/resources/example-output-file.txt
```

### Help
``` bash
 sudo java -cp ./target/knime-coding-challenge-1.0-SNAPSHOT.jar org.pedrocarlos.Main --help
```

## What is missing?
 - [ ] Third tasks from the challenge.
 - [ ] Improve Readme.md file.
 - [ ] Annotate with @NotNull functions parameters when it requires.