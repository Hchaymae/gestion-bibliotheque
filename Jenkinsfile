pipeline {
    agent any
    environment {
        MAVEN_HOME = tool 'Maven'
    }
    stages {
        stage('Checkout') {
            steps {
                script {
                    try {
                        git branch: 'main',
                            url: 'https://github.com/Hchaymae/gestion-bibliotheque.git',
                            credentialsId: 'Github-PAT'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    try {
                        sh '${MAVEN_HOME}/bin/mvn clean compile'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    try {
                        sh '${MAVEN_HOME}/bin/mvn test'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
        }
        stage('Quality Analysis') {
            steps {
                script {
                    try {
                        withSonarQubeEnv('SonarQube') {
                            sh '${MAVEN_HOME}/bin/mvn sonar:sonar'
                        }
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    try {
                        echo 'Déploiement simulé réussi'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
        }
    }
    post {
        success {
            mail to: 'hamdounechaymae@gmail.com',
                 subject: "Pipeline Jenkins - Succès",
                 body: """
                 Bonjour,

                 Le pipeline Jenkins a été exécuté avec succès.

                 Détails du build :
                 - Projet : Gestion Bibliothèque
                 - Statut : Succès

                 Cordialement,
                 Jenkins
             """

            slackSend (channel: '#tous-gestion-bibliotheque',
                       message: "Le pipeline Jenkins a été exécuté avec succès.",
                       tokenCredentialId: 'slack-token')
        }
        failure {
            mail to: 'hamdounechaymae@gmail.com',
                 subject: "Pipeline Jenkins - Échec",
                 body: """
                     Bonjour,

                     Le pipeline Jenkins a échoué. Veuillez vérifier les logs ci-dessous pour plus de détails.

                     Détails du build :
                     - Projet : Gestion Bibliothèque
                     - Statut : Échec

                     Logs du build :
                     ${currentBuild.getLog(50).join("\n")}

                     Cordialement,
                     Jenkins
                 """

            slackSend (channel: '#tous-gestion-bibliotheque',
                       message: "Le pipeline Jenkins a échoué. Veuillez vérifier les logs.",
                       tokenCredentialId: 'slack-token')
        }
    }
}
