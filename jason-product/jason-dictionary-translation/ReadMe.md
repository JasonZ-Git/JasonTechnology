This is Micro Service for translation word.

# To build this project:

mkdir -p target/dependency
cd target/dependency
jar -xf ../*.jar
docker build -t {name} .
docker run -p 8080:8080 {name}


# Services supported

There are 2 types supported:

* getTranslation?word={word}

* count