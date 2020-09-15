This is Micro Service for translation word.

## The following command should be run under the project directory

# To build this project:

mkdir -p target/dependency
cd target/dependency
jar -xf ../*.jar

# Build Docker 
docker build -t jason-birthday .

# Run Service in Docker
docker run -p 10010:10010 -d  --rm --name=Jason-Birthday jason-birthday:latest

# The default page will show all the services supported