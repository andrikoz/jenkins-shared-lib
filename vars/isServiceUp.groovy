/**
 * Polls specific url until gets a valid response
 *
 *
 * @param opts - status options
 * @param opts.maxRetries - max retries until timeout
 * @param opts.interval - time to sleep between polling
 */
boolean call(opts) {
  def response = null

  while (opts.maxRetries >= 0) {

    // Query Service service health
    try {
      response = httpRequest consoleLogResponseBody: true, quiet: true, timeout: 5, url: opts.url, validResponseCodes: '100:505'
    } catch (Exception e) {
    } finally {
      if (response && response.status == 200) {
        echo "Service is UP"
        return true
      }
      status = "Not Available"
      if (response) {
        status = response.status
      }
      echo "Service ${opts.url} is DOWN. HTTP status ${status}. ${opts.maxRetries} retries remaining. Check again in ${opts.interval} seconds..."
    }

    // Wait a bit
    sleep(opts.interval)

    // Decrease available retries
    opts.maxRetries--
  }

  // The  service failed to start within the given time
  echo "Service failed to start in ${opts.maxRetries * opts.interval} seconds"
  return false
}