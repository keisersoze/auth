it uses the embedded tomcat container of SpringBoot
As an added bonus, this repo can also be deployed directly as a docker container using the WildFly S2I builder image on OpenShift 3 with the following command:

	oc new-app wildfly:10.0~https://github.com/gshipley/bootwildfly.git


What, you don't have OpenShift 3 yet? Fix that immediately: www.openshift.org/vm


       Username: test
       Password: test
  Database Name: sampledb
 Connection URL: mongodb://test:test@mongodb/sampledb