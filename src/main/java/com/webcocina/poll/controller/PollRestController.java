package com.webcocina.poll.controller;

import com.webcocina.poll.domain.PollImpl;
import com.webcocina.poll.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by joaquinguerra on 01/03/2017.
 */
@RestController
@RequestMapping(value = "/v1/polls")
public class PollRestController {


	@Autowired
	private PollRepository pollRepository;


	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<PollImpl> getPollById(@PathVariable(name = "id") String pollID){

		PollImpl pollImpl = pollRepository.findOne(Integer.valueOf(pollID));

		if(pollImpl != null){
			return new ResponseEntity<PollImpl>(pollImpl, HttpStatus.OK);
		} else {
			return new ResponseEntity<PollImpl>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<PollImpl> getPollBySiteAndName(
			@Valid @RequestParam(name = "name") String name,
			@Valid @RequestParam(name = "site") String site) {

		Example<PollImpl> pollExample =
				Example.of(
						new PollImpl(null, site, name),
						ExampleMatcher.matching().withIgnorePaths("xmldata"));

		PollImpl pollImpl = pollRepository.findOne(pollExample);

		if (pollImpl != null) {
			return new ResponseEntity<PollImpl>(pollImpl, HttpStatus.OK);
		} else {
			return new ResponseEntity<PollImpl>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PollImpl> createPoll(@RequestBody PollImpl pollImplRequest){

		if (!validateAllParams(pollImplRequest)) return new ResponseEntity<PollImpl>(HttpStatus.BAD_REQUEST);
		PollImpl pollSaved = pollRepository.save(pollImplRequest);
		return new ResponseEntity<PollImpl>(pollSaved, HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<PollImpl> updatePollById(@PathVariable(name = "id") String pollID, @RequestBody PollImpl pollImplRequest){

		if (!validateAllParams(pollImplRequest)) return new ResponseEntity<PollImpl>(HttpStatus.BAD_REQUEST);
		PollImpl pollSaved = updatePoll(pollImplRequest, pollRepository.findOne(Integer.valueOf(pollID)));
		return new ResponseEntity<PollImpl>(pollSaved, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<PollImpl> updatePollByNameAndSite(
			@Valid @RequestParam(name = "name") String name, @Valid @RequestParam(name = "site") String site,
			@RequestBody PollImpl pollImplRequest){

		if (!validateAllParams(pollImplRequest)) return new ResponseEntity<PollImpl>(HttpStatus.BAD_REQUEST);

		Example<PollImpl> pollExample =
				Example.of(
						new PollImpl(null, site, name ),
						ExampleMatcher.matching().withIgnorePaths("xmldata"));

		PollImpl pollSaved = updatePoll(pollImplRequest, pollRepository.findOne(pollExample));
		return new ResponseEntity<PollImpl>(pollSaved, HttpStatus.OK);
	}

	private boolean validateAllParams(@RequestBody PollImpl pollImplRequest) {
		if((pollImplRequest.getName() == null || pollImplRequest.getName().isEmpty())
				&& (pollImplRequest.getSite() == null || pollImplRequest.getSite().isEmpty())
				&& (pollImplRequest.getXmldata() == null || pollImplRequest.getXmldata().isEmpty())){
			return false;
		}
		return true;
	}

	private PollImpl updatePoll(@RequestBody PollImpl pollImplRequest, PollImpl pollImpl) {
		pollImpl.setId(pollImplRequest.getId());
		pollImpl.setName(pollImplRequest.getName());
		pollImpl.setSite(pollImplRequest.getSite());
		pollImpl.setXmldata(pollImplRequest.getXmldata());

		return pollRepository.save(pollImpl);
	}
}
