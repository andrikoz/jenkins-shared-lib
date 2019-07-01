/**
 * Execute a gradle task within a JAVA aware shell
 *
 * @param task - the task/tasks to execute
 * @param version [java 8.0.212-zulu] - the SDKman compatible Java version
 */
def call(task, version="java 8.0.212-zulu") {
  sh """#!/bin/bash -i
    set +x
    sdk use ${version}
    set -x
    ./gradlew ${task}
  """
}