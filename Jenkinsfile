node {
    if (!'master'.equalsIgnoreCase(env.BRANCH_NAME)) {
        currentBuild.result = 'SUCCESS'
        return
    }

    String artifactId = 'buyme'
    String version = '1.0.1'

    //noinspection GroovyAssignabilityCheck
    properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', numToKeepStr: '10']]])

    checkout scm

    boolean success = false

    success = dockerStage(artifactId, version, '.')

    if (!success) {
        currentBuild.result = 'FAILURE'
        return
    }

    success = marathonJsonStage(artifactId, version)

    if (!success) {
        currentBuild.result = 'FAILURE'
        return
    }

    currentBuild.result = 'SUCCESS'
}

private boolean dockerStage(String artifactId, String version, String dockerfilePath) {
    boolean isSuccess = false

    stage('Docker') {
        String baseRegistry = 'https://hub.docker.com/r/codegenesis/codegenesisdocker:10104'
        String fullImageName = "${baseRegistry}/${artifactId}:${version}"

        // Build and tag.
        int buildAndTagResult = sh script: "docker build -t ${fullImageName} ${dockerfilePath}", returnStatus: true
        boolean buildAndTag = (buildAndTagResult == 0)

        if (!buildAndTag) {
            log.error 'There was an error tagging the image.'
            return
        }

        // Push the image.
        int pushResult = sh script: "docker push ${fullImageName}", returnStatus: true
        boolean push = (pushResult == 0)

        if (!push) {
            log.error 'There was an error pushing the image.'
            return
        }

        // Remove Jenkins' Docker daemon.
        sh script: "docker rmi ${fullImageName}", returnStatus: true

        isSuccess = true
    }

    return isSuccess
}

private boolean marathonJsonStage(String artifactId, String version) {
    String marathonJsonFilename = "${artifactId}-${version}-marathon.json"

    String nexusRepository = 'https://dev1.mesos.com/nexus/repository/marathon-app-json'
    //String authorization = 'Authorization: Basic bWlkZGxld2FyZTptaWRkbGV3YXJl'

    // Upload the Marathon JSON.
    int status = sh script: "curl --request PUT '${nexusRepository}/${marathonJsonFilename}' --upload-file marathon.json --header '${authorization}'", returnStatus: true

    boolean upload = (status == 0)

    if (!upload) {
        return false
    }

    return true
}
