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
            mail to: 'hamdounechaymae@gmail.com',
                 subject: "Pipeline Jenkins - Succès",
                 body: """
                 <html>
                 <body>
                     <h2 style="color: green;">Bonjour,</h2>
                     <p style="font-size: 16px;">Le pipeline Jenkins a été exécuté avec succès.</p>
                     <p style="font-size: 16px;"><strong>Details du build :</strong></p>
                     <ul>
                         <li><strong>Projet :</strong> Gestion Bibliothèque</li>
                         <li><strong>Statut :</strong> <span style="color: green;">Succès</span></li>
                     </ul>
                     <p style="font-size: 16px;">Cordialement,</p>
                     <p><strong><span style="color: blue;">Jenkins</span></strong></p>
                 </body>
                 </html>
                 """
            // Envoi du message Slack en cas de succès
            slackSend (channel: '#tous-gestion-bibliotheque', 
                       message: "Le pipeline Jenkins a été exécuté avec succès.",
                       tokenCredentialId: 'slack-token')
        }
        failure {
            mail to: 'hamdounechaymae@gmail.com',
                 subject: "Pipeline Jenkins - Échec",
                 body: """
                 <html>
                 <body>
                     <h2 style="color: red;">Bonjour,</h2>
                     <p style="font-size: 16px;">Le pipeline Jenkins a échoué. Veuillez vérifier les logs ci-dessous pour plus de détails.</p>
                     <p style="font-size: 16px;"><strong>Détails du build :</strong></p>
                     <ul>
                         <li><strong>Projet :</strong> Gestion Bibliothèque</li>
                         <li><strong>Statut :</strong> <span style="color: red;">Échec</span></li>
                     </ul>
                     <p style="font-size: 16px;"><strong>Logs du build :</strong></p>
                     <pre style="background-color: #f5f5f5; padding: 10px;">${currentBuild.rawBuild.getLog(50).join("\n")}</pre>
                     <p style="font-size: 16px;">Cordialement,</p>
                     <p><strong><span style="color: blue;">Jenkins</span></strong></p>
                 </body>
                 </html>
                 """
            // Envoi du message Slack en cas d'échec
            slackSend (channel: '#tous-gestion-bibliotheque', 
                       message: "Le pipeline Jenkins a échoué. Veuillez vérifier les logs.", 
                       tokenCredentialId: 'slack-token')
        }
    }
}
