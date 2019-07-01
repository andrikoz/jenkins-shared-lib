/**
 * Execute a script within a JAVA aware shell
 *
 * @param script - the script to execute
 * @param version [java 8.0.212-zulu] - the SDKman compatible Java version
 */
def call(script, version="java 8.0.212-zulu") {
  sh """#!/bin/bash -i
    set +x
    sdk use ${version}
    set -x
    ${script}
  """
}