package com.marketplace.deliveryservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.deliveryservice.api.dto.GetDeliveryRequest;
import com.marketplace.deliveryservice.entity.Client;
import com.marketplace.deliveryservice.entity.Courier;
import com.marketplace.deliveryservice.entity.Order;
import com.marketplace.deliveryservice.repository.ClientRepository;
import com.marketplace.deliveryservice.repository.CourierRepository;
import com.marketplace.deliveryservice.repository.OrderRepository;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.UUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тест контроллера, реализующий выбор заказа курьером
 */
@SpringBootTest
@AutoConfigureMockMvc
public class GetDeliveryTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CourierRepository courierRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MockMvc mvc;
    private RestHighLevelClient client = null;



    @Test
    @Transactional
    public void getDeliveryBeNoCorrect() throws Exception {
        UUID courierId = UUID.fromString("440e8400-e29b-41d4-a716-446655442980");
        UUID orderId = UUID.fromString("440e8400-e29b-41d4-a716-446655443900"); //заказа с данным id нет в БД
        GetDeliveryRequest getDeliveryRequest = new GetDeliveryRequest(courierId, orderId);
        mvc.perform(put("/delivery/get-delivery")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(getDeliveryRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void getDeliveryBeCorrect() throws Exception {
        UUID courierIdt = UUID.fromString("440e8400-e29b-41d4-a716-446655442987");
        Courier courier = new Courier(courierIdt, new String("Misha")
                , new String("+79998887777"), new String("Moscow"), (long) 0);
        courierRepository.save(courier);

        UUID clientIdt = UUID.fromString("440e8400-e29b-41d4-a716-446655441987");
        Client client = new Client(clientIdt, new String("Sasha"), new String("+79995554413"));
        clientRepository.save(client);

        UUID orderIdt = UUID.fromString("440e8400-e29b-41d4-a716-446655443987");
        UUID deliveryAddressId = UUID.fromString("440e8400-e29b-41d4-a716-446655446987");
        OffsetDateTime dateTime = OffsetDateTime.now();
        Order ordert = new Order(orderIdt, new String("NEW"), client, deliveryAddressId
                , 1, dateTime, null);
        orderRepository.save(ordert);
        GetDeliveryRequest getDeliveryRequest = new GetDeliveryRequest(courierIdt, orderIdt);
        mvc.perform(put("/delivery/get-delivery")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(getDeliveryRequest)))
                .andExpect(status().isOk());
    }

//    @Test
//    public void givenJsonString_whenJavaObject_thenIndexDocument() throws IOException {
//        String jsonObject = "{\"age\":10,\"dateOfBirth\":1471466076564,"
//                +"\"fullName\":\"John Doe\"}";
//        IndexRequest request = new IndexRequest("people");
//        request.source(jsonObject, XContentType.JSON);
//
//        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
//        String index = response.getIndex();
//        long version = response.getVersion();
//
//        assertEquals(DocWriteResponse.Result.CREATED, response.getResult());
//        assertEquals(1, version);
//        assertEquals("people", index);
//    }

    public static String mapToJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
