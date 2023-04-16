build-discovery-jar:
	cd discovery && \
		mvn package

build-library-jar:
	cd library && \
		mvn package

build-gateway-jar:
	cd gateway && \
		mvn package

build-jars: build-discovery-jar build-library-jar build-gateway-jar

dev:
	docker-compose up -d --remove-orphans

stop:
	docker-compose stop

down:
	docker-compose down

destroy:
	docker-compose down -v
