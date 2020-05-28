pipeline {
    agent any 
    stages {
        stage('SCM Checkout'){
            steps {
                git url: 'https://github.com/milanviradia/BloodDonationPortal'
            }
        }
        
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
    
    stage('Trigger Rundeck'){
    		steps {
    			build 'miniproject_job'
    		}
        }
    }
}
