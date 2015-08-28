#!/bin/bash

mvn versions:set;
mvn -N install;
mvn -N -f ../roth-lib-framework/pom.xml install;
