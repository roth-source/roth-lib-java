#!/bin/bash

mvn versions:set;
mvn -N install;
mvn -N -f ../roth-lib-java-framework/pom.xml install;
