This is Micro Service for translation word.

# To build this project:

mkdir -p target/dependency
cd target/dependency
jar -xf ../*.jar
docker build -t jason-dictionary-translation .
docker run -p 10001:10001 --mount type=bind,source=/Users/jasonzhang/Desktop/Jason-Files/dictionary/,target=/Dictionary jason-dictionary-translation:latest


# Services supported

There are 2 types supported:

* getTranslation?word={word}

* count