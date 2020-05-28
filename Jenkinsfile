pipeline {
    agent any 
    stages {
        stage('Clean and Compile') { 
            steps {
                sh "mvn clean"
                sh "mvn compile"
            }
        }
        stage('Test') { 
            steps {
                sh "mvn test"
            }
        }
        stage('Package in JAR') { 
            steps {
                sh "mvn package"
            }
        }
        stage('Build Backend Image') {
      		steps {
        		script {
          			dockerImage = docker.build "milanviradia/backend" + ":latest"
        		}
      		}
    	}
    	stage('Push Image') {
      		steps {
        		script {
          			    docker.withRegistry( '', 'dockerhubCredentials' ) {
            			dockerImage.push()
          			}
        		}
      		}
    	}
    	
    	stage('Build Frontend Image') {
        steps {
            dir("frontend")
            {
                script {
                dockerImage = docker.build "milanviradia/frontend" + ":latest"
                docker.withRegistry( '', 'dockerhubCredentials' ) {
            	dockerImage.push()}
                }
            }
        }
    }
    
    stage('Execute Rundeck job') {
        steps {
          script {
              step([$class: "RundeckNotifier",
                    includeRundeckLogs: true,
                    jobId: "6978b6ab-2772-4dc9-a361-3e1eb237317b",
                    rundeckInstance: "rundeck",
                    shouldFailTheBuild: true,
                    shouldWaitForRundeckJob: true,
                    tailLog: true])
              }
           echo "Rundeck here"
        }
    }
    }
}