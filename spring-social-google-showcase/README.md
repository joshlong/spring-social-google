Spring Social Google Quickstart
========================
This sample is designed to get you up and running quickly.

To run, simply import the project into your IDE and deploy to a Servlet 3 (or better) container such as Tomcat 7. You can also deploy from the command line with:

```$ mvn tomcat:run```

Access the project at `http://localhost:8080/spring-social-quickstart`. Discuss at [the Spring Social Forum](forum.springsource.org) and collaborate with the development team on [the project JIRA](jira.springframework.org/browse/SOCIAL).

For more on possible Google APIs, see:
* To explore the Google APIs, see the [explorer API](https://developers.google.com/apis-explorer/).
* To see possible OAuth scopes, consult this [https://developers.google.com/oauthplayground/](OAuth playground).

In order to run this sample application, create a properties file at ```src/main/resources/application.properies``` and then use the property values below as a basis for your own configuration. You'll need to ensure that this datasource is configured on your own machine and that you've set up a Google client application ID and secret.


```
# google oauth application settings.

google.clientId=YOUR_GOOGLE_CLIENT_ID
google.clientSecret=YOUR_GOOGLE_CLIENT_SECRET

# the two .sql files in the /resources folder should
# be run on the database defined here

dataSource.url= jdbc:postgresql://localhost/spring_google
dataSource.driverClass=org.postgresql.Driver
dataSource.dialect=org.hibernate.dialect.PostgreSQLDialect
dataSource.user=spring_google
dataSource.password=spring_google
```

