/************************************************************************
 *                                                                      *
 *  DDDD     SSSS    AAA        Daten- und Systemtechnik Aachen GmbH    *
 *  D   D   SS      A   A       Pascalstrasse 28                        *
 *  D   D    SSS    AAAAA       52076 Aachen-Oberforstbach, Germany     *
 *  D   D      SS   A   A       Telefon: +49 (0)2408 / 9492-0           *
 *  DDDD    SSSS    A   A       Telefax: +49 (0)2408 / 9492-92          *
 *                                                                      *
 *                                                                      *
 *  (c) Copyright by DSA - all rights reserved                          *
 *                                                                      *
 ************************************************************************
 *
 * Author : Riyaardh Adam
 * Created: 2023/02/16 07:44
 *
 ***********************************************************************/
package com.example.demo2.controller;

import com.example.demo2.model.Licence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("message")
@RestController
@EnableBinding(Source.class)
public class MessageController {

	Source source;

	/**
	 * Constructor for the Message Controller which creates a single instance of the Source Service .
	 * @param source instance to the source service to connect to the spring stream cloud methods.
	 */
	@Autowired
	public MessageController(Source source){
		this.source = source;
	}

	/**
	 * Post method that will accept a licence in json format from and send it to the RabbitMQ queue.
	 * @param licence class with licence information
	 * @return confirmation licence was successfully added to the queue
	 */
	@PostMapping()
	public String sendMessage(@RequestBody Licence licence){

			source.output().send(MessageBuilder.withPayload(licence).build());
			return " Added";
	}
}
