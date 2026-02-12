# Databases and Shit

## H2

H2 is a in-memory database. We will play around with it. When you include the H2 starter project, Spring does its plug 
and play magic. If you run this application with H2 starter project, it will automatically create the DB. You can view
the DB details in the start-up logs when you run this application with H2 enabled.

You'll see something that looks like:

`Added connection conn0: url=jdbc:h2:mem:eae25c24-5ec2-439e-bc2c-61bae6a81a6b`

That "URL" is the string we can use to connect to this in-mem database. We can enable the h2 console by addding the 
following to our application.properties: `spring.h2.console.enabled=true`

You can then run the application and go over to `localhost:8080/h2-console` and you get a web UI to acces the in-mem DB:

![img.png](img.png)

notice JDBC URL is set to `jdbc:h2:~/test` this is the default. We need to change this from that to what we get in the 
logs. Remember that should look something like `url=jdbc:h2:mem:eae25c24-5ec2-439e-bc2c-61bae6a81a6b`

Default username is `sa` with no password.

#### static url

You can also just set the url to anything you want. That will be set in the application.properties. you add the following:

`spring.datasource.url=jdbc:h2:mem:testdbname`

Now we can log in and do shit :)

### Working with the DB



