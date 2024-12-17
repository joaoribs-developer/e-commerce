#!/bin/bash
set -xe

  # Maven is used to build  and create a war file.
  mvn -Dspring-boot.run.profiles=remote -Dmaven.test.skip=true clean install

