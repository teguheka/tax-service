# Tax Calculator
API for Shopee Calculate Tax

[![Code Style](https://img.shields.io/badge/taxCode%20style-standard-green.svg)](https://github.com/feross/standard)

[Deployment Setup](#deployment-setup) |
[License](#license)

## Deployment Setup
Follow below instructions.

### Prerequisites
##### Using Docker
- Install Docker [https://download.docker.com/](https://download.docker.com/)
- Type in terminal
```
    $ git clone git@github.com:teguh/tax-service.git
    $ cd tax-service
    $ docker-compose up
```
- Check in browser http://localhost:8084

##### Using Manual
- Java
- Maven
- MySQL

- Type in terminal
```
    $ git clone git@github.com/teguheka/tax-service.git
    $ cd tax-service
    $ mvn spring-boot:run
```

### License
----

© [Teguh Eka Putra] 


### Docker cheat sheet
- `docker images` Show all images
- `docker container ls` Show all container
- `docker logs <container_name>` Logs container
- `docker container rm <container_name>` Delete container
- `docker image rm <image_name>` Delete image
- `docker-compose down` Stop and delete app container
