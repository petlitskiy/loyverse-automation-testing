mvn := docker run --rm \
	-v ${HOME}/.m2:/root/.m2:rw,z \
	-v ${PWD}:/src \
	-w /src \
	ghcr.io/base2services/maven-jdk11:latest

.PHONY:	all
all:	test build

.PHONY:	build
build:
	${mvn} /bin/sh -c 'mvn install --no-transfer-progress'

.PHONY:	test
test:
	env | grep "TEST_ENV.*" >> .env || true
	env | grep "BROWSERSTACK.*" >> .env || true
	${mvn} /bin/bash -c '\
	set -a && \
	. .env && \
	set +a && \
	export BROWSERSTACKP="-javaagent:$$(find $$HOME/.m2 -type f -iname browserstack-java-sdk-*.jar)" && \
	mvn test -P remote --no-transfer-progress' || true
