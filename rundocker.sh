docker stop maiserver
docker rm maiserver
docker build .
docker run -it -d --restart=always -p 8085:80 --name=maiserver $(docker build -q .)