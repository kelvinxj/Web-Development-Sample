from ubuntu:latest
copy ./jdk-8u162-linux-x64.tar.gz /usr/local
run tar zxvf /usr/local/jdk-8u162-linux-x64.tar.gz -C /usr/local
run rm /usr/local/jdk-8u162-linux-x64.tar.gz
copy ./start.sh /
#update apt source, using China sources
copy ./sources.list.aliyun /etc/apt/sources.list
run chmod +x /start.sh
run apt-get update
run apt-get install -y openssh-server
run apt-get install -y vim
#cmd /start1.sh
#run shell script. /start.sh should exist in docker
run /bin/bash /start.sh
copy ./sshd_config /etc/ssh
cmd service ssh start && /bin/bash