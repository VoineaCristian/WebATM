package com.learning.webatm;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.learning.webatm.container.MoneyContainer;
import com.learning.webatm.dto.MoneyDTO;
import com.learning.webatm.enums.Banknotes;
import com.learning.webatm.enums.Currency;
import com.learning.webatm.model.Money;
import com.learning.webatm.model.Notes;
import com.learning.webatm.model.Stacks;
import com.learning.webatm.service.MoneyServiceTest;
import org.assertj.core.util.Arrays;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.Assert.notNull;

@SpringBootTest
public class IntegrationATMTests {

//    @InjectMocks
//    MoneyServiceTestImpls moneyServiceTest;
    ModelMapper modelMapper;
    List<Notes> notesRON;
    List<Stacks> defaultRONStacks;
//    @Mock
    Money defaultRONMoney;

//    @Value("${}")


    @BeforeEach
    public void setup(){
        modelMapper = new ModelMapper();
        notesRON = new ArrayList<>();
        defaultRONStacks = new ArrayList<>();

        java.util.Arrays.stream(Banknotes.values())
                .filter(n->n.name().startsWith("RON"))
                .forEach(n->notesRON.add(new Notes(n, Integer.parseInt(n.name().split("_")[1].toString()))));
        notesRON.forEach(n->defaultRONStacks.add(new Stacks(n, 100)));
        defaultRONMoney = new Money(Currency.CURRENCY_RON, defaultRONStacks);


    }


    @Test
    public void testLogATM() {
        RestAssured.given().log().all()
                .when().get("http://localhost:8080/atm/balance?username=mirel")
                .then().statusCode(200);
    }
    @Test
    public void testInitialATMState(){
        MoneyContainer container = RestAssured.get("http://localhost:8080/atm/balance?username=mirel")
                                              .as(MoneyContainer.class);

        Assert.assertNotNull(container);
        List<Money> moneyList = container.getMoneys().stream()
                                                     .map(n->modelMapper.map(n, Money.class))
                                                     .collect(Collectors.toList());
        moneyList.forEach(money-> Assert.assertTrue(Arrays.isNullOrEmpty(money.getStacks().toArray())));

    }

    @Test
    public void testRefill() throws JsonProcessingException {
        JSONObject jsonObj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        MoneyDTO sendBody;
        sendBody = modelMapper.map(defaultRONMoney, MoneyDTO.class);
        String send = mapper.writeValueAsString(sendBody);
        System.out.println(send);
        MoneyDTO responseDTO = RestAssured.given()
                   .body(sendBody)
                   .contentType("application/json;")
                   .post("http://localhost:8080/atm/refill?username=mirel")
                   .then().statusCode(200)
                   .extract().as(MoneyDTO.class);



        Money actual = modelMapper.map(responseDTO, Money.class);
        Money expected = modelMapper.map(sendBody, Money.class);
        Assert.assertEquals(actual, expected);
//        Assertions.assertAll(() -> asserEq(), () -> );


    }
    @Test
    public void testBalanceAfterRefill() throws JsonProcessingException {


        MoneyContainer container = RestAssured.get("http://localhost:8080/atm/balance?username=mirel")
                                              .as(MoneyContainer.class);

        Assert.assertNotNull(container);
        List<Money> moneyList = container.getMoneys().stream()
                .map(n->modelMapper.map(n, Money.class))
                .collect(Collectors.toList());
        Money money = moneyList.stream().filter(m->m.getCurrency() == Currency.CURRENCY_RON).findFirst().get();
        Long actual = money.getTotalAmount().longValue();
        Long expected = 86600L;
        Assert.assertEquals(expected, actual);

    }
}
