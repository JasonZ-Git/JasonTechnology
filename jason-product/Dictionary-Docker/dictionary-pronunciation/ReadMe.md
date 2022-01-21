This is Micro Service for word pronunciation.

## The following command should be run under the project directory

# To build this project:

mkdir -p target/dependency
cd target/dependency
jar -xf ../*.jar

# Build Docker 
docker build -t jason-dictionary-pronunciation .

# Run Service in Docker
docker run -itd --rm -p 10001:10001 --mount type=bind,source=/Users/jasonzhang/Desktop/Jason-Files/dictionary/,target=/Dictionary --name=Jason-Dictionary-Pronunciation jason-dictionary-pronunciation:latest

# The default page will show all the services supported