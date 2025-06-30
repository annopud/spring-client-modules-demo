#!/bin/bash

eval $(minikube docker-env)
kubectl delete -f '/Users/tttmb/test/kubernates/nextjs-ingress-demo/springk8s/spring-clients-deployment.yaml'
docker rmi -f spring-clients:1.0.0
docker build -t spring-clients:1.0.0 .
kubectl apply -f '/Users/tttmb/test/kubernates/nextjs-ingress-demo/springk8s/spring-clients-deployment.yaml'