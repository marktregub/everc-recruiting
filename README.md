# EverCompliant pipeline test

### Implementation notes

 - The code implementation is with Java 8, build tool is maven
 - In order to run the code you need mockserver is up and running on localhost:1080
 - Main class is PipelinePOCMain to try the code on the existing in the project input
 - See pipeline execution tests


### Product compliance pipeline example

EverCompliant are in the business of compliance, and we validate that  
 products sold on e-commerce platforms are legitimate.  
 In order to perform the compliance validation we stream the products  
 submitted to various platforms to our validation process.


The following test requires you:
- [ ] Load file source_data.csv to objects in an in-memory queue
- [ ] Send each object in the queue to an external API for enrichment: http://localhost:1080/test_api (see mockserver setup)
- [ ] Create a united model of both source object and result from external api and transmit to the response endpoint: http://localhost:1080/item (POST method)
- [ ] Once all items in batch (see first column in the csv) have been completed, transmit batch id to batch endpoint: http://localhost:1080/batch (POST method)


### Mockserver setup

Mockserver is a java based mocking server (<https://www.mock-server.com/>),
 installation guide can be found: <https://www.mock-server.com/mock_server/running_mock_server.html>.

- 1. Install the docker:
    docker run -d --rm -p 1080:1080 mockserver/mockserver

- 2. run attached mockserver definitions, creates the mock endpoints:
    bash ./mockserver_init.sh
    The script expects the mockserver on localhost and port 1080, if changed, change the script accordingly.


Feel free to contact me:
<danielm@evercompliant.com>
