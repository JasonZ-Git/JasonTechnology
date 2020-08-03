This is Micro Service for translation word.

# To build this project:

mkdir -p target/dependency
cd target/dependency
jar -xf ../*.jar

# Build Docker 
docker build -t jason-dictionary-translation .

# Run Service in Docker
docker run -p 10001:10001 --mount type=bind,source=/Users/jasonzhang/Desktop/Jason-Files/dictionary/,target=/Dictionary --name=Jason-Dictionary-Translation  jason-dictionary-translation:latest

# The default page will show all the services supported