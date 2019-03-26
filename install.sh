#!/usr/bin/env bash

if [ "$1" = "" ] || [ "$2" = "" ]; then
	echo "USAGE: ./install.sh <domain> <port>"
	echo "e.g. ./install.sh example.com 8080"
	exit 1
fi

domain=$1
application_port=$2

if [ "$application_port" = "80" ] || [ "$application_port" = "443" ]; then
	echo "ERROR: Your application cannot use the port 80 or the port 443"
	echo "Nginx will listen on both 80 and 443 of those ports and redirect requests to the port your application uses"
	exit 1
fi

# Install the latest JDK
apt install -y default-jdk

# TESTING vvv
pkill -f "java"
wget https://github.com/TwinProduction/spring-as-backend/releases/download/v0.0.3/spring-as-backend.jar
java -jar spring-as-backend.jar > /dev/nul &
# TESTING ^^^

apt update


####################################################
# SSL/TLS termination using Nginx as reverse proxy #
####################################################

apt install -y nginx

# Nginx needs to be stopped for certbot's http challenge
service nginx stop
pkill -f "nginx"

# Make sure that port 80 and 443 aren't blocked by the firewall
ufw allow 80/tcp
ufw allow 443/tcp

# Install certbot to generate a certificate with LetsEncrypt
add-apt-repository -y ppa:certbot/certbot
apt update
apt install -y certbot python-certbot-nginx
certbot --nginx certonly --preferred-challenges http -d ${domain} --register-unsafely-without-email --agree-tos --redirect

nginx_config="
server {
    server_name $domain;
    location / {
        proxy_pass http://localhost:$application_port;
        proxy_read_timeout 90s;
    }
    listen [::]:443 ssl ipv6only=on;
    listen 443 ssl;
    ssl_certificate /etc/letsencrypt/live/$domain/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/$domain/privkey.pem;
    include /etc/letsencrypt/options-ssl-nginx.conf;
    ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem;
}

server {
    server_name $domain;
    listen 80;
    listen [::]:80;
    return 301 https://\$host\$request_uri;
}
"

echo "$nginx_config" | tee /etc/nginx/sites-available/default > /dev/null

pkill -f "nginx"
service nginx start