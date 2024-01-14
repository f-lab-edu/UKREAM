package com.ukream.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukream.dto.ProductDTO;
import com.ukream.error.exception.UnKnownCommandException;
import com.ukream.es.domain.model.Product;
import com.ukream.es.domain.repository.ProductSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class KafkaProductListener implements KafkaBaseListener<Product>{
    private final ProductSearchRepository productSearchRepository;
//
//    @KafkaListener(topics = "product", groupId =  "${spring.kafka.group-id}")
//    public void listenProduct(ConsumerRecord<String, Object> record){
//        Product product = (Product) record.value();
//        log.info(product.toString());
//    }
    @KafkaListener(topics = "product", groupId =  "${spring.kafka.group-id}")
    public void listenProduct(ConsumerRecord<String, Object> record){
        Product product = (Product) record.value();
        String command = product.getCommand();


        switch (command) {
            case "create":
                createAction(product);
                break;
            case "update":
                updateAction(product);
                break;
            case "delete":
//                deleteAction(product);
                break;
            default:
                throw new UnKnownCommandException();
        }
    }

    @Override
    public void createAction(Product product) {
        productSearchRepository.save(product);
    }

    @Override
    public void updateAction(Product product) {

    }

    @Override
    public void deleteAction(Product product) {

    }
}
