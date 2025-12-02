# Overview of webapp "mijnwoonplaats"

This app is a pet project that can be found [here](https://mijnwoonplaats.gjk101.com). I created to learn about the following topics:

- Spring Boot
- PostgreSQL
- Dealing with public API's
- Maven
- testing
- VPS management, including bash scripting
- Docker and Docker Hub
- GitHub Actions

The easiest part was the Java/Spring code itself. The hardest parts had to do with managing external dependencies (the api's of CBS and PDOK) and the creation of a CI/CD pipeline. 

The language in the app is ambiguous, dutch and english are mixed. This ambiguity resulted from the topic and you will see it in the names I used for identifiers in the code as well. I hope it is not too inconvenient.

The descriptions below are brief but they give an idea of the things I learned and the problems I solved.

## Spring Boot

Based on my knowledge from the VM Spring Certified Professional 2024 course I created more or less standard beans plus some @entity annotated domain objects. I organized the code in packages, of which 'apiclient' and 'textcreation' do the heavy lifting.

### Package 'apiclient'

The apiclient package is responsible for periodically accessing the public api's of [CBS](https://opendata.cbs.nl/#/CBS/nl/) and [PDOK](https://api.pdok.nl/bzk/locatieserver/search/v3_1/ui/). Data is transferred to my own Postgres database. These api's provide statistical (CBS) and geographical data (PDOK). During development the CBS api broke (json fields were renamed/renumbered), which learned me how cumbersome it is to rely on dependencies you do not control.

To get the apiclient working, I needed a way to schedule tasks. I used the Spring @Scheduled annotation with a cron attribute. It can be found in file ApiScheduler.java, package 'apiclient'.

To correctly initialize the database, I added a 'StartupListener' class (package 'mijnwoonplaats'). It checks if the connected database is already filled. If not, it contacts CBS and/or PDOK and retrieves their data.

### Package 'textcreation'

This package provides well formatted text on dutch towns. The TextService class is wired in the ControllerClass class and its method 'getText' is being called to provide three short alineas that will be displayed when the user selects a town. 

Internally Class DataAssessment creates interpretations of data values that are retrieved by its dependency DataAggregator. The assessment is stored as an Assessment type record. This Assessment record is a dependency of the WoonplaatsData object, in which all information required for a complete text comes together. The WoonplaatsData object in turn is used as argument for a method in TextCreator that returns the text as a custom TextObject object.

## PostgreSQL / data layer

When application.properties contains the right database credentials Spring Boot creates the database tables itself, based on the @Entity annotated domain objects. To access the database, corresponding repository objects need to be created. For both I created a distinct package.

Because the database is filled with records coming from CBS and PDOK, and because this external data did not map one-on-one on my table structure, I created intermediate data transfer classes that act as intermediaries. They can be found in package 'datatransferobjects'.

The problem I encountered most with the database had to do with access. It regularly happens that I make mistakes setting the database credentials correctly either in my application.properties files or in my environment variables. Furthermore I needed to learn more about using 'roles' in postgres. I wrote about it on my [blog](https://geertjan-kuip.github.io/2025/09/11/postgresql-on-linux.html).

What I learned is that you can create complex indexes in the Spring code itself. I needed multi-column index in the table that maps postal codes to town codes and was able to do that by creating a special class for this index, with a '@Embeddable' annotation.

To peek into the database I started using DBeaver, which has a comfortable GUI, but I learned to access the database via the Linux cli as well using psql.

## Dealing with public api's

The api's I use to extract data from are both run by Dutch government and they are probably very stable, but during development the CBS api broke. Extra fields were added to certain json nodes, and the names of the fields, more specifically their numerical postfixes, were changed. It took me a while to find the problem.

What I also noticed was the rather limited documentation that was available. Figuring out how to create the correct url's took way too much time. ChatGPT normally solves any configuration issue I encounter but here was simply too little information, which meant I needed to find out by trial and error.

## Maven

Using Spring means using Maven, given that you have a lot of dependencies for which the pom.xml file does miracles. I learned to use Maven via the command line but use it as well via the Maven tab in Intellij. What I noticed is that ChatGPT or any other AI tool is extremely convenient to tell you what dependencies/modules you need for what.

## Testing

I learned Spring Boot integration testing, AssertJ, JUnit 5 and Mockito. With respect to the latter, I have the idea that unit testing is super useful during development, but that the deployment tests that are part of the CI/CD cannot do without integration tests.

Anyway, I created two test files, one for integration tests and one for unit tests. In the future I hope to find ways to shorten the duration of tests, it takes way too long for my liking now.

## VPS management, including bash scripting

This project learned me to love Linux. Among others I learned to work with [SSH keys](https://geertjan-kuip.github.io/2025/09/06/ssh-and-linux.html), I explored the Filesystem  Hierarchy Standard, became familiar with [permissions and ownership](https://geertjan-kuip.github.io/2025/09/08/linux-in-depth-1.html), learned a lot of clil [commands](https://geertjan-kuip.github.io/2025/09/10/linux-commands-cheat-sheet.html), explored [systemd and unit files](https://geertjan-kuip.github.io/2025/09/10/systemd-units.html), learned bash scripting ([here](https://geertjan-kuip.github.io/2025/10/10/bash-scripting.html) and [here](https://geertjan-kuip.github.io/2025/11/07/bash-scripting-part-2.html)), found out more about the [find](https://geertjan-kuip.github.io/2025/11/11/linux-find-in-detail-test-expressions.html) command and figured out how Linux works with files [internally](https://geertjan-kuip.github.io/2025/11/13/linux-inode-hard-links-and-symbolic-links.html).

A reason I do not mind spending much time on Linux is that I see it, like Java itself, as the most stable part of my toolkit. I can choose from multiple databases, ci/cd tools or containerization platforms, but plain Java and Linux will probably always part of my project. Understanding them a bit better pays of.

## Docker and Docker Hub

Working with Docker feels convenient, as it allows me to discard any installed application easily. In this project I have a setup with three containers, one for the app, one for the production database and one for a test database. The latter has an open port so it can be used when the app is tested on the GitHub Actions Ubuntu runner.  

The two production containers are part of a custom bridge network, which allows them to communicate without having to expose the database port.

Docker Hub is being used to streamline the deployment process. In my GitHub Actions workflow I create an image on the Ubuntu runner, upload it to my Docker Hub account, and subsequently download and run it on my production server. This is more convenient and it allows me to revert to a previous version of the image if somehow a new version doesn't work well.

## GitHub Actions

I created a GitHub Actions workflow that runs every time I push new code to my GitHub repository. The workflow packages the app and tests it on a GitHub Actions node using Maven. If the test is successful, the packaged app becomes part of a Docker image that is uploaded to Docker Hub. Then the image is downloaded on my server, the container running the previous version of the app is stopped and removed, and a new container based on the new image is run.

To keep things safe, I learned to work with GitHub Actions secrets, both for database access credentials and the ssh key for my production server. One thing I needed to learn was that when you run commands on your Linux machine via the GitHub Actions server, you cannot rely on environment variables on your server. They were not accessible, so I needed to create file on my server to which I refer in the --env-file flag in the Docker run command.

## Summarizing

Given the simple functionality that the webapp provides, the implementation is very heavy. It has 36 Java files plus resource files. This 'heavyness,' that also included the Spring- and other modules, was new for me. 

What I would improve next time would mainly have to do with the apiclient and textcreation packages. Given the volatility of the external endpoints I would make the api package more robust and I would give more thought to the internal structure of the textcreation api.

But for now this is what it is, there probably will only be slight changes in the future based on urgent needs. I learned a lot and will start with the next project.
















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
