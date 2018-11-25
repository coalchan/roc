#!/usr/bin/env bash

ROC_HOME=D:\other-code\roc

java \
-Droc.home=${ROC_HOME} \
-Dlogback.configurationFile=${ROC_HOME}\conf\logback.xml \
-Dlog.file.name=write-to-stream \
-jar roc.jar \
-job ${ROC_HOME}\roc-test\tests\write-to-stream.json \
-plugin ${ROC_HOME}\plugins