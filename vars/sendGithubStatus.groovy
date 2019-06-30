/**
 * Send status to Github via the Status API
 *
 * @param opts - status options
 * @param opts.context - A string label to differentiate this status from the status of other systems
 * @param opts.state - The state of the status. Can be one of `error`, `failure`, `pending`, or `success`.
 * @param opts.message - A short description of the status.
 */
void call(Map opts) {
  step([
      $class: "GitHubCommitStatusSetter",
      reposSource: [$class: "ManuallyEnteredRepositorySource", url: "https://github.com/bluegroundltd/atlas.git"],
      contextSource: [$class: "ManuallyEnteredCommitContextSource", context: opts.context],
      //errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
      statusResultSource: [ $class: "ConditionalStatusResultSource", results: [[$class: "AnyBuildResult", message: opts.message, state: opts.state]] ]
  ])
}