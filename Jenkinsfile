pipeline {
    agent any
    environment {
        MAVEN_HOME = tool 'Maven'
    }
    stages {
        stage('Checkout') {
            steps {
                script {
                    catchError(buildResult: 'FAILURE', message: 'Checkout failed') {
                        git branch: 'main',
                            url: 'https://github.com/Hchaymae/gestion-bibliotheque.git',
                            credentialsId: 'Github-PAT'
                    }
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    catchError(buildResult: 'FAILURE', message: 'Build failed') {
                        sh '${MAVEN_HOME}/bin/mvn clean compile'
                    }
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    catchError(buildResult: 'FAILURE', message: 'Test failed') {
                        sh '${MAVEN_HOME}/bin/mvn test'
                    }
                }
            }
        }
        stage('Quality Analysis') {
            steps {
                script {
                    catchError(buildResult: 'FAILURE', message: 'SonarQube analysis failed') {
                        withSonarQubeEnv('SonarQube') {
                            sh '${MAVEN_HOME}/bin/mvn sonar:sonar'
                        }
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    catchError(buildResult: 'FAILURE', message: 'Deploy failed') {
                        echo 'Déploiement simulé réussi'
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
