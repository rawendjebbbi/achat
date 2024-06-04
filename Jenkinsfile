pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('dockerhub-credentials')
        DOCKER_IMAGE = 'raouendjebbi/achat'
        DOCKER_TAG = '1.0.0'
    }

    stages {
        stage('Git'){
            steps{
                echo 'pulling from git ....';
                git branch :'main', url:'https://github.com/rawendjebbbi/achat.git';
            }
        }
        stage('Clean'){
            steps{
                echo 'cleaning....';
                sh 'mvn clean';
            }
        }
        stage('Compile'){
            steps{
                echo 'compiling....';
                sh 'mvn compile';
            }
        }
        stage('Sonar'){
            steps{
                echo 'scanning with sonar ....';
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin1';
            }
        }
        stage('mockito Junit'){
            steps{
                echo 'running unit test ....';
                sh 'mvn test';
            }
        }
        stage('Docker Build') {
            steps {
                echo 'Building Docker image ....'
                sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
            }
        }
        stage('Docker Push') {
            steps {
                echo 'Pushing Docker image to Docker Hub ....'
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'DOCKER_HUB_CREDENTIALS') {
                        sh "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                    }
                }
            }
        }
        stage('Docker compose') {
            steps {
                echo 'creating docker compose ....'
                script {
                        sh 'docker compose up -d'
                    }
                }

        }
    }
}
