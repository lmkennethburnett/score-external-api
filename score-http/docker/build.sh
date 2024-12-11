#!/bin/sh

if ! [[ -x "$(command -v docker)" ]]; then
  echo "Error: docker is not installed. See https://docs.docker.com/install/ for details.'" >&2
  exit 1
fi

echo "Packaging project..."
cd ..
./mvnw clean package -DskipTests=true

echo "Preparing files..."
cp score-http/target/score-http-3.4.0.war docker
cp ~/.m2/repository/org/mariadb/jdbc/mariadb-java-client/3.5.0/mariadb-java-client-3.5.0.jar docker

echo "Building docker image..."
cd docker
docker build --no-cache -f Dockerfile -t oagi1docker/srt-http-gateway:3.4.0 .

echo "Scanning vulnerabilities..."
docker scout cves oagi1docker/srt-http-gateway:3.4.0

echo "Cleaning up..."
rm -f *.jar *.war

echo "Done."
