pipeline {
    agent any 
    stages {
        stage('Clean and Compile') { 
            steps {
                sh "mvn clean"
                sh "mvn compile"
            }
        }
        stage('Junit Testing') {
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
    	stage('Push Backend Image') {
      		steps {
        		script {
          			    docker.withRegistry( '', 'dockerhubCredentials' ) {
            			dockerImage.push()
          			}
        		}
      		}
    	}
    	
    	stage('Build & Push Frontend Image') {
        steps {
            dir("frontend")
            {
                script {
                dockerImage = docker.build "milanviradia/frontend" + ":latest"
                docker.withRegistry( '', 'dockerhubCredentials' ) {
            	dockerImage.push()
                    }
                }
            }
        }
    }
    
    stage('Trigger Rundeck'){
    		steps {
    			build 'mini_project_job'
    		}
        }
    }
}
