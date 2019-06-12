pipeline {
  agent { 
    docker {
      image 'maven:3.3.3'
      args '-v /root/.m2:/root/.m2'
    }  
  }
  stages {
    stage('check') {
      steps {
        sh 'pwd'
        sh 'mvn --version'
      }
    }
    stage('build') {
      steps {
        sh 'mvn clean package -DskipTests=true  -Dspring-boot.repackage.skip=false -U'
      }
    }
    stage('build image') {
      steps {
        sh '''current=`date "+%Y%m%d%H%M%S"`
version=demo-ci-$current-${BUILD_NUMBER}
cat >> ~/.bash_profile <<EOF
export version=demo-ci-$current-${BUILD_NUMBER}
EOF

cp target/spring-boot-hello-1.0.jar src/docker/app.jar
docker login -u ${DOCKER_USER} -p ${DOCKER_PWD} ${DOCKER_REGISTRY}
docker build --pull -t ${DOCKER_REGISTRY}/${NAME_SPACE}/${PROJECT_NAME}:${version} ${1:-"src/docker"}
docker push ${DOCKER_REGISTRY}/${NAME_SPACE}/${PROJECT_NAME}:${version}
'''
      }
    }
    stage('clean image') {
      steps {
        sh '''source ~/.bash_profile
  docker rmi ${DOCKER_REGISTRY}/${NAME_SPACE}/${PROJECT_NAME}:${version}'''
      }
    }
  }
  environment {
    NAME_SPACE = 'koudaidemon'
    PROJECT_NAME = 'jenkins-demo'
    DOCKER_USER = 'koudaidemon'
    DOCKER_PWD = 'caiwei1213..00'
    DOCKER_REGISTRY = 'docker.io'
  }
}