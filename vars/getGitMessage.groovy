/**
 * Get the subject and body of the commit
 * message at HEAD, separated by a newline.
 */
def call() {
   sh(returnStdout: true, script: "git log --no-merges --format=\"%s%n%b\" -n 1").trim()
}