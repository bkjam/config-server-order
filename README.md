# Exploring Loading Precedence for Properties files with Spring Boot, Spring Profiles, and Spring Cloud Config Server

This is my exploration to play around and understand how properties files are loaded in Spring Boot when using with Spring 
Profiles and Spring Cloud Config. For more information, refer to the blog post I have on medium below.

| Exploration | Article |
| --- | --- |
| Testing Loading Precedence of Properties Files | [5 Observations on Spring Boot's Loading Precedence for Properties Files with Spring Cloud Config](https://betterprogramming.pub/5-observations-on-spring-boots-loading-precedence-for-properties-files-with-spring-cloud-config-331d1af9052e) |
| Testing Local Development<br>Testing Refresh Properties | [3 Useful Tips for Developers when using Spring Cloud Config](https://medium.com/@kbryan1008/3-useful-tips-for-developers-when-using-spring-cloud-config-de7b874f9911) | 

In this repository, I have configured some Spring Boot applications that loads configurations from the Spring Cloud Config Server:

| Spring Boot Application | Description |
| --- | --- |
| config-client | sample app used for understanding order of precedence for properties files with Spring Cloud Config |
| config-client-bootstrap | sample app used for understanding spring cloud config with bootstrap |
| config-client-local | sample app used for understanding localhost development with Spring Cloud Config |
| config-client-refresh  | sample app used for understanding properties refresh |

Additionally, the folders are for the following purpose:

| Folder | Description |
| --- | --- |
| config-repo | stores the remote properties files |
| config-server | Spring Cloud Config Server project |

## Usage

### Exploration: Testing Loading Precedence of Properties Files

1. Start the Spring Cloud Config Server

   ```bash
   ./gradlew config-server:bootrun -Pargs=--spring.profiles.active=git
   ```

2. Start the Config Client

   ```bash
   ./gradlew config-client:bootrun
   ```

3. Check the order of precedence

   ```bash
   curl localhost:8080/actuator/env
   ```

### Exploration: Testing Localhost Development

#### 1) Using Property "spring.cloud.config.allow-override"

1. Start the Spring Cloud Config Server

   ```bash
   ./gradlew config-server:bootrun -Pargs=--spring.profiles.active=git
   ```

2. Start the Config Client

   ```bash
   ./gradlew config-client-local:bootrun
   ```

#### 2) Using "local" Spring Profiles

1. Start the Spring Cloud Config Server

   ```bash
   ./gradlew config-server:bootrun -Pargs=--spring.profiles.active=git
   ```

2. Start the Config Client with local Profiles

   ```bash
   ./gradlew config-client-local:bootrun -Pargs=--spring.profiles.active=dev,local
   ```

#### 3) Using localhost Spring Cloud Config Server

1. Start the Spring Cloud Config Server with Local File Repository

   ```bash
   ./gradlew config-server:bootrun -Pargs=--spring.profiles.active=native
   ```

2. Start the Config Client

   ```bash
   ./gradlew config-client-local:bootrun
   ```

### Exploration: Testing Refresh Properties (Manual Refresh)

1. Start the Spring Cloud Config Server

   ```bash
   ./gradlew config-server:bootrun -Pargs=--spring.profiles.active=git
   ```

2. Start the Config Client

   ```bash
   ./gradlew config-client-refresh:bootrun
   ``` 

3. Print the Custom Property by calling the endpoint

   ```bash
   curl localhost:8080/api/print
   ``` 

4. Update the remote properties files (config-client-refresh-dev.yaml)

   ```bash
   # Update config-client-refresh-dev.yaml
   
   # Commit and push changes to remote branch
   git add config-repo
   git commit -m "update configurations"
   git push
   ``` 

5. Refresh the config-client
    
   ```bash
   curl -X POST localhost:8080/actuator/refresh
   ```

6. Print the Custom Property by calling the endpoint. You should notice that the property have been updated.

   ```bash
   curl localhost:8080/api/print
   ``` 

## Other useful commands

```bash
# Dockerize Config Client
./gradlew config-client:jibDockerBuild

# Start Dockerize Config Client
docker run -d --name config-client --network=host --env SPRING_CONFIG_ADDITIONAL_LOCATION='/config/' --volume <path_to_config_folder>:/config config-client
```

## References / Credits

- Externalized Configurations
    - [https://docs.spring.io/spring-boot/docs/1.2.0.M1/reference/html/boot-features-external-config.html](https://docs.spring.io/spring-boot/docs/1.2.0.M1/reference/html/boot-features-external-config.html)
    - [https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config)
    - [https://spring.io/blog/2020/08/14/config-file-processing-in-spring-boot-2-4](https://spring.io/blog/2020/08/14/config-file-processing-in-spring-boot-2-4)
    - [https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-Config-Data-Migration-Guide#profile-specific-external-configuration](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-Config-Data-Migration-Guide#profile-specific-external-configuration)
    - [https://github.com/spring-cloud/spring-cloud-config/issues/1795](https://github.com/spring-cloud/spring-cloud-config/issues/1795)
    - [https://github.com/spring-projects/spring-boot/issues/26858](https://github.com/spring-projects/spring-boot/issues/26858)
    - [https://github.com/spring-cloud/spring-cloud-config/issues/1895](https://github.com/spring-cloud/spring-cloud-config/issues/1895)
- Testing
    - [https://www.baeldung.com/spring-boot-testing](https://www.baeldung.com/spring-boot-testing)
    - [https://stackoverflow.com/questions/48273638/which-application-yml-thatre-in-src-main-resources-and-src-test-resources-direc](https://stackoverflow.com/questions/48273638/which-application-yml-thatre-in-src-main-resources-and-src-test-resources-direc)
    - [https://www.baeldung.com/spring-tests-override-properties](https://www.baeldung.com/spring-tests-override-properties)
    - [https://stackoverflow.com/questions/9632710/adding-src-main-resources-to-classpath-when-running-spring-junit-tests-from-ecl](https://stackoverflow.com/questions/9632710/adding-src-main-resources-to-classpath-when-running-spring-junit-tests-from-ecl)
    - [https://stackoverflow.com/questions/15284465/maven3-main-resources-vs-test-resources#:~:text=Normally%20the%20src%2Fmain%2Fresources,this%20during%20the%20unit%20testing.](https://stackoverflow.com/questions/15284465/maven3-main-resources-vs-test-resources#:~:text=Normally%20the%20src%2Fmain%2Fresources,this%20during%20the%20unit%20testing.)
