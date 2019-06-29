/**
 * Get the shortened commit pointed by HEAD
 */
def call() {
  return sh(returnStdout: true, script: "git log --no-merges --format=\"%h\" -n 1").trim()
}