pipeline {
  agent any
  stages {
    stage('检出') {
      steps {
        echo "NEW_PROJECT_JAR is ${NEW_PROJECT_JAR}"
        checkout([$class: 'GitSCM',
        branches: [[name: GIT_BUILD_REF]],
        userRemoteConfigs: [[
          url: GIT_REPO_URL,
          credentialsId: CREDENTIALS_ID
        ]]])
      }
    }
    stage('编译') {
      steps {
        sh 'mvn clean'
      }
    }
    stage('修改版本号') {
      steps {
        sh "mvn versions:set -DnewVersion=${CODING_MAVEN_VERSION}"
      }
    }
    stage('推送到 CODING Maven 制品库') {
      steps {
        echo '''发布中...'''
        withCredentials([
          usernamePassword(
            credentialsId: "${CODING_ARTIFACTS_CREDENTIALS_ID}",
            usernameVariable: 'CODING_MAVEN_REG_USERNAME',
            passwordVariable: 'CODING_MAVEN_REG_PASSWORD'
          )
        ]) {
          sh '''mvn install
           cd ./target
           cp front-3.6.6-SNAPSHOT.jar ${NEW_PROJECT_JAR}'''
        }

        echo '发布完成.'
      }
    }
  }
  environment {
    CODING_MAVEN_REPO_ID = "${CCI_CURRENT_TEAM}-${PROJECT_NAME}-${MAVEN_REPO_NAME}"
    CODING_MAVEN_REPO_URL = "${CCI_CURRENT_WEB_PROTOCOL}://${CCI_CURRENT_TEAM}-maven.pkg.${CCI_CURRENT_DOMAIN}/repository/${PROJECT_NAME}/${MAVEN_REPO_NAME}/"
    DATE = sh(returnStdout: true, script: '%Y%m%d%H%M%S').trim()
    NEW_PROJECT_JAR = "front-3.6.6-SNAPSHOT-${DATE}.jar"
  }
}