docker stop maiserver
docker rm maiserver
chmod +x gradlew
docker run -it -d -v $(pwd):/opt/app --restart=always -p 8085:80 --name=maiserver $(docker build -q .)