rm -r ./src/main/resources/static/app
rm -r ./src/main/resources/static/img

cd ../web
git pull origin master
cp -r ./dist ../ecity/src/main/resources/static/app

cd ../ecity/src/main/resources/static/
mv app/img img
