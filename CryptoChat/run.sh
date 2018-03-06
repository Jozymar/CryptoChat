sudo docker build -t cryptochat/postgres ./postgres
sudo docker build -t cryptochat/postgreschave ./postgreschave
sudo docker build -t cryptochat/app .
sudo docker run -d --name postgres cryptochat/postgres
sudo docker run -d --name postgreschave cryptochat/postgreschave
sudo docker run -p 8081:8181 -d --name app --link postgres:host-postgres --link postgreschave:host-postgreschave cryptochat/app
