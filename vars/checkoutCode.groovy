/**
 * Check out a repository by branch name
 *
 *
 * @param opts - status options
 * @param opts.repo - the repository to be checked out
 * @param opts.branch - the branch of the repository to be checked out
 */
def call(opts) {
  checkout([$class                           : 'GitSCM',
            poll                             : true,
            branches                         : [[name: "*/${opts.branchName}"]],
            doGenerateSubmoduleConfigurations: false,
            extensions                       : [
                [$class: 'CheckoutOption'],
                [$class: 'CloneOption', noTags: true, reference: '', shallow: true, depth: 1],
                [$class: 'LocalBranch', localBranch: ${opts.branchName}]
            ],
            submoduleCfg                     : [],
            userRemoteConfigs                : [[url: ${opts.repo}, credentialsId: 'jenkins-git']]
  ])
}