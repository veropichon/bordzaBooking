APP_REPO_NAME="/home/laetitia/bordza_pictures/client_images/"

if [ ! -d "$APP_REPO_NAME" ]; then
  mkdir -p "$APP_REPO_NAME"
fi

docker run -dit --name bordza-apache -p 80:80 -v /home/laetitia/bordza_pictures:/usr/local/apache2/htdocs/ httpd:2.4