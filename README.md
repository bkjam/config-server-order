# Exploring Loading Precedence for Properties files with Spring Boot, Spring Profiles, and Spring Cloud Config Server

This is my exploration to play around and understand how properties files are loaded in Spring Boot when using with Spring 
Profiles and Spring Cloud Config. For more information, refer to the blog post I have on medium.

## Usage

### 1) Spring Cloud Config Server

There are 3 ways you can start the Spring Cloud Config Server

- **[Default]** Start Spring Cloud Config Server with Remote Git Repository
    
   ```bash
   ./gradlew config-server:bootrun -Pargs=--spring.profiles.active=git
   ```

- Start Spring Cloud Config Server with Local File Repository
    
   ```bash
   ./gradlew config-server:bootrun -Pargs=--spring.profiles.active=native
   ```

- **[Not recommended]** Start Spring Cloud Config Server with Local Git Repository (will git reset when git tree is not clean)

   ```bash
   ./gradlew config-server:bootrun -Pargs=--spring.profiles.active="git,local"
   ```

### 2) Spring Boot Application (Config Client)

- Start the Config Client

   ```bash
   ./gradlew config-client:bootrun
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
