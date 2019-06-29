/**
 * Send a rich formatted message to Slack
 *
 * https://api.slack.com/reference/messaging/attachments
 *
 * @param opts - status options
 * @param opts.color - the color of the bar on the left of the message
 * @param opts.text - the text at the top of the message
 * @param opts.channel - the slack channel to send the message to
 */
def slackSendRichMessage(opts) {
  def attachments = [[
                         color: opts.color,
                         pretext: opts.text,
                         author_name: env.GIT_COMMIT_AUTHOR,
                         fallback: "${currentBuild.projectName}: ${opts.text}",
                         footer: 'Jenkins',
                         footer_icon: 'https://jenkins.theblueground.net/static/7258d72e/images/headshot.png',
                         mrkdwn_in: ['text', 'pretext', 'title'],
                         text: env.GIT_COMMIT_MSG,
                         title: env.JOB_NAME,
                         title_link: env.JOB_URL,
                         ts: new Date().getTime() / 1000,
                         fields: [
                             [
                                 title: 'Project',
                                 value: opts.projectName ?: currentBuild.projectName,
                                 short: true
                             ],
                             [
                                 title: 'Result',
                                 value: currentBuild.result,
                                 short: true
                             ],
                             [
                                 title: 'Branch',
                                 value: env.BRANCH_NAME,
                                 short: true
                             ],
                             [
                                 title: 'Commit',
                                 value: env.GIT_COMMIT_HASH,
                                 short: true
                             ]
                         ],
                         actions: [
                             [
                                 type: 'button',
                                 text: 'View Build',
                                 url: env.RUN_DISPLAY_URL ?: env.BUILD_URL
                             ],
                             [
                                 type: 'button',
                                 text: 'View on Github',
                                 url: env.GIT_URL
                             ]
                         ]
                     ]]
  slackSend channel: opts.channel, attachments: attachments
}