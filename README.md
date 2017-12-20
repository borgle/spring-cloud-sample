# spring-cloud-sample
It's easy setup a cloud service if you use spring-cloud. This is a simple example, you can create a microservice base on it.

## ready
You need jdk8 or later.

## clone
```bash
git clone https://github.com/yoker/spring-cloud-sample.git --depth=1
```

## build
```bash
./mvnw install
```

## run
```bash
java -jar ./ts-registry/target/registry-server.jar
java -jar ./ts-config/target/config-server.jar
java -jar ./ts-gateway/target/api-gateway.jar
java -jar ./ts-client-feign/target/client-feign.jar
# java -jar ./ts-client-ribbon/target/client-ribbon.jar
java -jar ./ts-business-a/target/business-a.jar
java -Dspring.profiles.active=another -jar ./ts-business-a/target/business-a.jar
```

## check
```bash
curl http://localhost:8080/feign/test
curl http://localhost:8080/feign/timeout
ps -ef | grep business-a | awk '{print $2}'|xargs kill
sleep 30
curl http://localhost:8080/feign/test
```

## other
Best wishes! Thanks.
