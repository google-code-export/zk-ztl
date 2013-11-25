copy /Y selenium-api.js selenium-server\core\scripts
copy /Y selenium-browserbot.js selenium-server\core\scripts
copy /Y htmlutils.js selenium-server\core\scripts
copy /Y zselenium.js selenium-server\core\scripts
copy /Y RemoteRunner.html selenium-server\core
jar cfM selenium-server.jar -C selenium-server/ .
