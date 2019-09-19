HTDOCS=~/bordza_pictures
PICS_DIR=$HTDOCS/client_images/
if [ ! -d $PICS_DIR ]; then
 mkdir -p $PICS_DIR
fi
docker run -dit --name bordza-apache -p 80:80 -v $HTDOCS:/usr/local/apache2/htdocs/ httpd:2.4
