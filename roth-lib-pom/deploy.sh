#!/bin/bash

mvn -N install;
mvn -N -f ../roth-lib-api-pom/pom.xml install;
mvn -N -f ../roth-lib-all/pom.xml install;
mvn -T 4 deploy;
