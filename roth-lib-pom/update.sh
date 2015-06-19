#!/bin/bash

mvn versions:set;
mvn -N install;
mvn -N -f ../roth-lib-api-pom/pom.xml install;
mvn -N -f ../roth-lib-all/pom.xml install;
