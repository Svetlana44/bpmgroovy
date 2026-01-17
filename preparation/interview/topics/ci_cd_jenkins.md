# Jenkins CI/CD

## Вопросы и ответы

**1. Что такое Jenkins pipeline?**  
Описанный в Jenkinsfile процесс сборки, тестирования и деплоя.

**2. Declarative vs Scripted?**  
Declarative проще и стандартизирован, Scripted гибче.

**3. Как хранить Jenkinsfile?**  
В репозитории рядом с кодом.

**4. Как публиковать отчёты?**  
Артефакты + плагины Allure/JUnit.

**5. Что важно для Senior‑уровня?**  
Безопасность секретов, параллелизация, отказоустойчивость.

**Пример Jenkinsfile (Declarative):**
```groovy
pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        sh './gradlew clean test'
      }
      post {
        always {
          archiveArtifacts artifacts: 'build/allure-results/**', fingerprint: true
        }
      }
    }
  }
}
```
