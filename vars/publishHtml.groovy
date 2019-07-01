/**
 * Publish the given files using the HTML publish plugin.
 *
 * @param directoryPattern - the directory pattern as string
 * @param files - the report files
 * @param reportName - the report name
 */
def call(opts) {
  publishHTML(target: [
      allowMissing         : true,
      alwaysLinkToLastBuild: false,
      keepAll              : true,
      reportDir            : opts.directoryPattern,
      reportFiles          : opts.files,
      reportName           : opts.reportName
  ])
}