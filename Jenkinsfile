pipeline {
    agent any
    environment {
        MAVEN_HOME = tool 'Maven'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Hchaymae/gestion-bibliotheque.git',
                    credentialsId: 'Github-PAT'
            }
        }
        stage('Build') {
            steps {
                sh '${MAVEN_HOME}/bin/mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                sh '${MAVEN_HOME}/bin/mvn test'
            }
        }
        stage('Quality Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '${MAVEN_HOME}/bin/mvn sonar:sonar'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Déploiement simulé réussi'
            }
        }
    }
    post {
        success {
            // Envoi de l'email en cas de succès
            mail to: 'hamdounechaymae@gmail.com',
                 subject: "Succès de l'exécution du pipeline",
                 body: "Le pipeline Jenkins a été exécuté avec succès."

            // Envoi du message Slack en cas de succès
            slackSend (channel: '#tous-gestion-bibliotheque', 
                       message: "Le pipeline Jenkins a été exécuté avec succès.",
                       tokenCredentialId: 'slack-webhook')
        }
        failure {
            // Envoi de l'email en cas d'échec
            mail to: 'hamdounechaymae@gmail.com',
                 subject: "Échec de l'exécution du pipeline",
                 body: "Le pipeline Jenkins a échoué. Veuillez vérifier les logs."

            // Envoi du message Slack en cas d'échec
            slackSend (channel: '#tous-gestion-bibliotheque', 
                       message: "Le pipeline Jenkins a échoué. Veuillez vérifier les logs.", 
                       tokenCredentialId: 'slack-webhook')
        }
    }
}
