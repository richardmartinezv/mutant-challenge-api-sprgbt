# CHALLENGE MERCADO LIBRE


## TESTS

The definition of load testing generally refers to testing as a subset of the software performance testing process, which typically also includes various other types of testing, such as stress tests, soak tests, peak tests, stress tests , volume tests and scalability tests, among other types of tests.

Next I will explain step by step the analysis of load tests carried out on the component through the use of the POSTMAN tool.

First, we are going to know a little about the EndPoints associated with this API:

- POST: "/ mutant"

This EndPoint is in charge of receiving all requests from users in order to detect if the DNA sequence received corresponds to that of a mutant or a human.

Example:  

```
curl -H "Content-Type: application/json" -X POST -w "\n%{http_code}\n" -d '{"dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}' http://{url_ip}/v1/mutant
200 OK
```


         
- GET: "/ stats"

This EndPoint is responsible for obtaining the statistics related to the ratio between detected mutants and humans

Example:
    
```
curl --location --request GET http://{url_ip}/v1/stats

{"countMutantDna":1,"countHumanDna":1,"ratio":1.0}

200 OK
```

Using the POSTMAN tool we will proceed to build our load TEST step by step:

### Step 1

We build on POSTMAN the request to the EndPoint "/ mutant" and we launch a test to see the result of the response, as shown below:

![Alt Text](/docs/img/Img-7._Mutant_Request_Test.png)

In the previous image we can see a correct response from our service, indicating that indeed the DNA sequence sent in our request body corresponds to a mutant, since its response corresponded to a 200 code.


### Step 2

Below I attach the image associated with the script that I create in Node.js so that it generates different DNA sequences in the body of the request for each request I make from POSTMAN.

![Alt Text](/docs/img/Img-8._Script_generator_of_difference_sequences.png)


### Step 3

We will proceed to carry out our load test generating a quantity of 1000 requests through the Run Test Collection that the POSTMAN tool provides us, this in order to evaluate the performance of our component.

Below I attach the image related to the Run Test Collection created in POSTMAN.



![Alt Text](/docs/img/Img-10.Run_test_Collection_End.png)


## CONCLUSIONS

From the result of the load test carried out through the Run Test Collection, we can see that the component is taking around 200 ms to 400 ms to respond, which is an acceptable time for a load of 1000 requests at a time, which would allow us assume that the component would withstand the different traffic fluctuations that could be generated in its day to day.

In addition, we must also take into account that our API is deployed in the GCP environment under a container environment with Docker managed and administered by Kubernetes, which provides us with an ease when we want to scale our API in order to provide and guarantee a better service , and that at the moment we have only 2 instances or replicas deployed, which could be increased through Kubernetes in order to give our component a better performance and therefore being able to respond in that way to many more requests per second and analyze in turn DNA sequences of larger sizes.


Then you will find attached in the following Link the report obtained from the [Run test Collection granted by POSTMAN](/docs/TestCollection.postman_test_run.json)


Also attached you can also find the [collection created in POSTMAN for testing](/docs/TestCollection.postman_collection.json)

Note that if you want to import the collection into postman for testing, please be sure to check that the IP corresponds to the one indicated on the main page associated with this documentation.
