This is Micro Service for translation word.

## The following command should be run under the project directory

# To build this project:

mkdir -p target/dependency
cd target/dependency
jar -xf ../*.jar

# Build Docker 
docker build -t jason-dictionary-translation .

# start selenium in composer if it is not started yet
docker-compose up -f docker-compose.yml -d -p selenium-hub

# Run Service in Docker
docker run -p 10001:10001 --rm --mount type=bind,source=/Users/jasonzhang/Desktop/Jason-Files/dictionary/,target=/Dictionary --name=Jason-Dictionary-Translation jason-dictionary-translation:latest

# The default page will show all the services supported