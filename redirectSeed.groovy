pipelineJob("${REDIRECT_JOB_NAME}") {
  	 parameters {
        stringParam("GIT_PR_ID", "", "")
        stringParam("GIT_PR_LINK", "", "")
        stringParam("GIT_PR_TITLE", "", "")
        stringParam("GIT_PR_SOURCE_BRANCH", "", "")
        stringParam("GIT_PR_TARGET_BRANCH", "", "")
        stringParam("GIT_PR_ACTUAL_COMMIT", "", "")
        stringParam("GIT_PR_MERGE_COMMIT", "", "")
        stringParam("GIT_PR_REPO_URL", "", "")
        stringParam("GIT_PR_REPO_FULL_NAME", "", "")
        stringParam("GIT_PR_AUTHOR_EMAIL", "", "")
        stringParam("GIT_PR_AUTHOR_LOGIN", "", "")
        stringParam("PR_SANITY_FLAGS", "", "")
        stringParam("UPGRADE_FROM", "", "")
        stringParam("PR_SANITY_RETRY", "", "")
        stringParam("GH_PR_STATUS", "", "")
        booleanParam("SKIP_INSATLL", false, "")
        booleanParam("IS_TEST", false, "")
        stringParam("CONFIG_BRANCH", "master", "Branch to checkout from.")
        stringParam("REPORT_PR_ID", "", "")
        stringParam("REPORT_REPO_FULL_NAME", "", "")
        stringParam("REPORT_COMMIT", "", "")
    } 
     definition {
        cpsScm {
             scm {
                git {
                  remote {
                    url('git@eos2git.cec.lab.emc.com:Devops/tridevlab.git')
                      }
                        branch('${CONFIG_BRANCH}')
                    }
                 scriptPath('pipelines/PR_Sanity_Redirect.groovy')
              }
        }
 
        configure {
            it / 'properties' / 'jenkins.model.BuildDiscarderProperty' {
                strategy {
                    'daysToKeep'('15')
                    'numToKeep'('300')
                    'artifactDaysToKeep'('10')
                    'artifactNumToKeep'('-1')
                }
            }
        }
    }
}