# Overview of webapp "mijnwoonplaats"

This app is under construction and requires the following steps to finish:

- Setting up a GitHub Actions pipeline. Upon push, app will be tested, packaged and copied to the webserver.
- Setting up domains on my server. Need to buy a domain name.
- Install and configure Nginx on my webserver to create subdomains and correct internal routing.
- Create a Dockerized database on my server so that the app can connect to it. Required for integration tests. 

I will rewrite this readme once the mentioned tasks have been completed. For the record, the app has the following description:

- The webpage has a search field in which you can enter the name of a Dutch town.
- The backend periodically inquires the public api's of PDOK and CBS and stores its data in a PostgreSQL database.
- Upon entering a town, you'll be getting some demographic info.

### Challenges in this project:

Before this project I had worked with plain Java, Maven, JavaScript and css. This project learned me the following:

- Ins and outs of Spring Boot
- Getting familiar with JPA, Object Relational Mapping, Jackson, Thymeleaf
- Using scheduled tasks (@Scheduled) and @EventListener
- Learning integration testing (JUnit 5, AssertJ, @SpringBootTest)
- Learning unit testing (Mockito)
- Figuring out the specifics of public api's
- Making the app robust enough to not suffer if these api's break
- Create a simple logging system
- Learning PostgreSQL, understanding structure, roles and access permissions
- Becoming comfortable with Linux Ubuntu (filesytem, users/groups, processes, systemd unit files)
- Learn to work with Docker, connecting containers within a network
- Configuring Nginx
- Using Git and GitHub
- Automating workflows with GitHub Actions
