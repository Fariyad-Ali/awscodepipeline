package com.example.aws.QueueDemo1.controller;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.example.aws.QueueDemo1.lamda.Book;
import com.example.aws.QueueDemo1.lamda.BookDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    Logger logger= LoggerFactory.getLogger(MessageController.class);
    @Autowired
    private QueueMessagingTemplate template;

    @Autowired
    AmazonSNSClient amazonSNSClient;

    @Autowired
    private BookDao bookDao;

    @Value("${cloud.aws.sqs.endpoint}")
    private String endpoint;

    @Value("${cloud.aws.sns.topic}")
    private  String snsTopic;

    @GetMapping("/send/{message}")
    public String sendMessage(@PathVariable String message)
    {
        template.send(endpoint,MessageBuilder.withPayload(message).build());
        return "sent";
    }


    @SqsListener("myqueue")
    public void loadMessageFromSQS(String message)  {
        logger.info("message from SQS Queue {}",message);
    }

    @GetMapping("/subscribe/{email}")
    public String subscribeTopic(@PathVariable String email){
        SubscribeRequest subscribeRequest = new SubscribeRequest(snsTopic,"email",email);
        amazonSNSClient.subscribe(subscribeRequest);
        return "Your request has been submitted, please check your email";
    }
    @GetMapping("/publish/{message}")
    public String publish(@PathVariable String message){
        PublishRequest publishRequest = new PublishRequest(snsTopic,message,"Notification from AWS SNS");
        amazonSNSClient.publish(publishRequest);
        return "message has been sent";
    }

    @GetMapping("/books")
    public List<Book> getBooks(){
        return this.bookDao.buildBooks();
    }
}
