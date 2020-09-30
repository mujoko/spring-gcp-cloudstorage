#Sample code to upload file to GCP Cloud Storage
##Steps

* To Build  
** mvn clean install
* Ensure bucket name change in app.properties as per bucket in your GCP account
* Ensure your laptop has access to GCP (test out from command)
* To Run
** java -jar target/spring-gcp-cloudstorage-<xxxx>.jar

* Accces the app from [http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config# ]
* Upload the file from Swagger