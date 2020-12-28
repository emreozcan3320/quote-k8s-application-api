
![alt text](https://cdn-images-1.medium.com/max/1200/1*1BF_eIkV6wkuqsziZ_hf7Q.png)

# Stateful Kubernetes CRUD Application Backend


This Spring Boot application is a part of a Medium Post named  **### How to Deploy a Stateful CRUD Web Application to Minikube(Local Cluster) and Google Cloud Kubernetes Engine(GKE)**. It is about creating a Stateful application and deploying it both Minikube(Local Cluster) and Google Cloud Kubernetes Engine(GKE).

### How to run it?
This is the only Backend part of the application so you need the Front-end too. The master branch connects to Google Cloud SQL so if you have a Google Cloud SQL instance update the `application. properties` file to connect your database. But if you don't have a Google Cloud SQL instance, you have to use the local MySql Database, to do so you need to switch to the `minikube-mysql-k8s-deployment` branch. It uses a local MySql database.

Default database credentials are below. But you can change them or pass a new credential as an environment variable while using Docker or Kubernetes.
- Host: *localhost*
- Database: *kubernetes_application*
- User: *root*
- Password: *password*
---
#### Kubernetes Deployment
There is another Github repository that contains all deployment files. But, I also adding the once that related to the Backend.
```YML
apiVersion: v1  
kind: Service  
metadata:  
  labels:  
    app: application-api-service  
  name: application-api-service  
spec:  
  ports:  
  - name: application-api-service  
    port: 8080  
    protocol: TCP  
    targetPort: 8080  
  selector:  
    app: application-api-pod  
  type: ClusterIP  
  
---  
  
apiVersion: apps/v1
kind: Deployment
metadata:
  labels:
    app: application-api-deployment
  name: application-api-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: application-api-pod
  strategy: {}
  template:
    metadata:
      labels:
        app: application-api-pod
    spec:
      containers:
      - image: gcr.io/bionic-trilogy-298608/quote-application-api
        name: application-api-container
        ports:
            - containerPort: 8080
        env:
            - name: MYSQL_HOST
              value: mysql
        resources: {}
status: {}
 
```
