/**
 * Publish the given files using the HTML publish plugin.
 *
 * @param directoryPattern - the directory pattern as string
 * @param files - the report files
 * @param reportName - the report name
 */
def call(directoryPattern, files, reportName) {
  publishHTML(target: [
      allowMissing         : true,
      alwaysLinkToLastBuild: false,
      keepAll              : true,
      reportDir            : directoryPattern,
      reportFiles          : files,
      reportName           : reportName
  ])
}