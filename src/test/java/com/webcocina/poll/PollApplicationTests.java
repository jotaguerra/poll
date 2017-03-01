package com.webcocina.poll;

import com.webcocina.poll.controller.PollRestController;
import com.webcocina.poll.domain.PollImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PollApplicationTests {

	private static final String DATASET_XML="<?xml version='1.0' encoding='UTF-8'?>\n" +
			"<dataset>\n" +
			"    <record>\n" +
			"        <id>1</id>\n" +
			"        <first_name>Dennis</first_name>\n" +
			"        <last_name>Fisher</last_name>\n" +
			"        <email>dfisher0@un.org</email>\n" +
			"        <gender>Male</gender>\n" +
			"        <ip_address>8.90.247.135</ip_address>\n" +
			"    </record>\n" +
			"    <record>\n" +
			"        <id>2</id>\n" +
			"        <first_name>Andrew</first_name>\n" +
			"        <last_name>Carpenter</last_name>\n" +
			"        <email>acarpenter1@disqus.com</email>\n" +
			"        <gender>Male</gender>\n" +
			"        <ip_address>57.75.166.219</ip_address>\n" +
			"    </record>";

	private TestRestTemplate restTemplate = new TestRestTemplate();


	@Test
	public void contextLoads() {

	}

	@Test
	public void testCreatePollMissingEveryParameterNOK() {
		PollImpl request = new PollImpl();
		ResponseEntity<PollImpl> responseEntity = this.restTemplate.postForEntity("http://localhost:8080/v1/polls",  request, PollImpl.class);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testCreatePollMissingSiteNOK() {
		PollImpl request = new PollImpl(DATASET_XML, "", "TestSite1_Name" );
		ResponseEntity<PollImpl> responseEntity = this.restTemplate.postForEntity("http://localhost:8080/v1/polls",  request, PollImpl.class);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testCreatePollMissingNameNOK() {
		PollImpl request = new PollImpl(DATASET_XML, "TestSite1", "" );
		ResponseEntity<PollImpl> responseEntity = this.restTemplate.postForEntity("http://localhost:8080/v1/polls",  request, PollImpl.class);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testCreatePollMissingXMLNOK() {
		PollImpl request = new PollImpl("", "TestSite1", "TestSite1_Name" );
		ResponseEntity<PollImpl> responseEntity = this.restTemplate.postForEntity("http://localhost:8080/v1/polls",  request, PollImpl.class);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testFindPollNotCreatedNOK() {
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("site", "TestSite1");
		urlVariables.put("name", "TestSite1_Name");
		ResponseEntity<PollImpl> responseEntity = this.restTemplate.getForEntity("http://localhost:8080/v1/polls",  PollImpl.class, urlVariables);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	@Test
	public void testCreatePollOK() {

		PollImpl request = new PollImpl(DATASET_XML, "TestSite1", "TestSiteName1" );
		ResponseEntity<PollImpl> responseEntity = this.restTemplate.postForEntity("http://localhost:8080/v1/polls",  request, PollImpl.class);

		assertEquals(responseEntity.getBody().getName(), request.getName());
		assertEquals(responseEntity.getBody().getSite(), request.getSite());
		assertEquals(responseEntity.getBody().getXmldata(), request.getXmldata());
		assertNotNull(responseEntity.getBody().getId());
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testFetchPollByIdPreviosulyCreatedByIdOK() {
		ResponseEntity<PollImpl> responseEntity = this.restTemplate.getForEntity("http://localhost:8080/v1/polls/1",  PollImpl.class);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertEquals(responseEntity.getBody().getId(), Integer.valueOf(1));
	}

	@Test
	public void testFetchPollMissingSiteParamNOK() {
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("site", "TestSite1");
		urlVariables.put("name", "");
		ResponseEntity<PollImpl> responseEntity = this.restTemplate.getForEntity("http://localhost:8080/v1/polls",  PollImpl.class, urlVariables);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testFetchPollMissingNameParamNOK() {
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("site", "");
		urlVariables.put("name", "TestSiteName1");
		ResponseEntity<PollImpl> responseEntity = this.restTemplate.getForEntity("http://localhost:8080/v1/polls",  PollImpl.class, urlVariables);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void fetchPollCreatedBySiteAndNameOK() {
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("site", "TestSite1");
		urlVariables.put("name", "TestSiteName1");
		ResponseEntity<PollImpl> responseEntity = this.restTemplate.getForEntity("http://localhost:8080/v1/polls",  PollImpl.class, urlVariables);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
	}


	@Test
	public void testCreatePollRepeatedUniqueIndexNOK() {
		PollImpl request = new PollImpl(DATASET_XML, "TestSite1", "TestSiteName1" );
		ResponseEntity<PollImpl> responseEntity = this.restTemplate.postForEntity("http://localhost:8080/v1/polls/",  request, PollImpl.class);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
