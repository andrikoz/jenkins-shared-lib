/**
 * Get the author of the commit pointed by HEAD
 */
def call() {
  return sh(returnStdout: true, script: "git log --no-merges --format=\"%an\" -n 1").trim()
}
