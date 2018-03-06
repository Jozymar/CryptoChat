sudo docker stop app
sudo docker stop postgres
sudo docker stop postgreschave
sudo docker rm app
sudo docker rm postgres
sudo docker rm postgreschave
sudo docker rmi -f cryptochat/app
sudo docker rmi -f cryptochat/postgres
sudo docker rmi -f cryptochat/postgreschave
