package com.learning.webatm;

import com.learning.webatm.enums.Banknotes;
import com.learning.webatm.enums.Currency;
import com.learning.webatm.exception.NotEnoughMoney;
import com.learning.webatm.exception.NotEnoughPennies;
import com.learning.webatm.exception.RunOutOfMoney;
import com.learning.webatm.model.Drawer;
import com.learning.webatm.model.Money;
import com.learning.webatm.model.Notes;
import com.learning.webatm.model.Stacks;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionsTest {

    ArrayList<Notes> notes;
    Money money;
    ArrayList<Stacks> moneys;

    @Mock
    Drawer drawer;

    @BeforeEach
    public void setup(){
//        when(drawer.addMoney(any(), any())).thenReturn(myList);

        notes = new ArrayList<>();
        Random rnd = new Random();
        Arrays.stream(Banknotes.values())
                .filter(n->n.name().startsWith("RON"))
                .forEach(n->notes.add(new Notes(n, Integer.parseInt(n.name().split("_")[1].toString()))));
        moneys = new ArrayList<>();
        notes.forEach(n->moneys.add(new Stacks(n, 100)));
        money = new Money();
        drawer = new Drawer();
        money.setStacks(moneys);
        money.setCurrency(Currency.CURRENCY_RON);
        drawer.setMoneys(Arrays.asList(money));

    }

    @Test
    public void testAddMoney() throws RunOutOfMoney, NotEnoughMoney, NotEnoughPennies {
        //System.out.println(drawer.getMoneyListByType(Currency.CURRENCY_RON));
        System.out.println(drawer);
        // Assert.assertEquals(drawer.withdraw(Currency.CURRENCY_RON, 100).getTotalAmount().longValue(),100L);
    }

    @Test
    public void testSimpleWithdraw_100() throws RunOutOfMoney, NotEnoughMoney, NotEnoughPennies {

        int expectedSum = 100;
        int actualSum = drawer.withdraw(Currency.CURRENCY_RON, 100).getTotalAmount();
        Assert.assertEquals(expectedSum, actualSum);
    }

    @Test
    public void testSimpleWithdraw_154() throws RunOutOfMoney, NotEnoughMoney, NotEnoughPennies {

        int expectedSum = 154;
        int actualSum = drawer.withdraw(Currency.CURRENCY_RON, 154).getTotalAmount();
        Assert.assertEquals(expectedSum, actualSum);
    }

    @Test
    public void testSimpleWithdraw_455() throws RunOutOfMoney, NotEnoughMoney, NotEnoughPennies {

        int expectedSum = 455;
        int actualSum = drawer.withdraw(Currency.CURRENCY_RON, 455).getTotalAmount();
        Assert.assertEquals(expectedSum, actualSum);
    }

    @Test
    public void testSimpleWithdraw_923() throws RunOutOfMoney, NotEnoughMoney, NotEnoughPennies {

        int expectedSum = 923;
        int actualSum = drawer.withdraw(Currency.CURRENCY_RON, 923).getTotalAmount();
        Assert.assertEquals(expectedSum, actualSum);

    }
    @Test
    public void testSimpleWithdraw_EntireDrawer() throws RunOutOfMoney, NotEnoughMoney, NotEnoughPennies {

        int expectedSum = 86600;
        int actualSum = drawer.withdraw(Currency.CURRENCY_RON, 86600).getTotalAmount() ;

        Assert.assertEquals(expectedSum, actualSum);
    }


    @Test
    public void testSimpleWithdraw_With_Stacks() throws RunOutOfMoney, NotEnoughMoney, NotEnoughPennies {

        int expectedSum = 923;
        List<Stacks> expectedStacks = new ArrayList<>();
        expectedStacks.add(new Stacks(new Notes(Banknotes.RON_500, 500), 1));
        expectedStacks.add(new Stacks(new Notes(Banknotes.RON_200, 200), 2));
        expectedStacks.add(new Stacks(new Notes(Banknotes.RON_10, 10), 2));
        expectedStacks.add(new Stacks(new Notes(Banknotes.RON_1, 1), 3));

        List<Stacks> actualStacks = drawer.withdraw(Currency.CURRENCY_RON, 923).getStacks();

        Assert.assertArrayEquals(actualStacks.toArray(), expectedStacks.toArray());
    }

    @Test
    public void testSimpleWithdraw_With_All_Stacks() throws RunOutOfMoney, NotEnoughMoney, NotEnoughPennies {

        int expectedSum = 86600;
        List<Stacks> expectedStacks = new ArrayList<>();
        notes.forEach(n->expectedStacks.add(new Stacks(n, 100)));
        Collections.sort(expectedStacks);


        List<Stacks> actualStacks = drawer.withdraw(Currency.CURRENCY_RON, 86600).getStacks();

        Assert.assertArrayEquals(expectedStacks.toArray(), actualStacks.toArray());
    }

    @Test
    public void testRefill(){

        List<Stacks> stacks = new ArrayList<>();
        Money moneys;
        int expectedSum = 86600*2;
        int actualSum;

        notes.forEach(n->stacks.add(new Stacks(n, 100)));

        moneys = new Money(Currency.CURRENCY_RON, stacks);

        drawer.addMoney(moneys.getCurrency(), moneys.getStacks());
        actualSum = drawer.getMoneyListByType(Currency.CURRENCY_RON).getTotalAmount();

        Assert.assertEquals(expectedSum, actualSum);
    }

    @Test
    public void testRefillWithStacks(){

        List<Stacks> addStacks = new ArrayList<>();
        List<Stacks> expectedStacks = new ArrayList<>();
        List<Stacks> actualStacks;

        notes.forEach(n->expectedStacks.add(new Stacks(n, 300)));
        notes.forEach(n->addStacks.add(new Stacks(n, 200)));

        actualStacks = drawer.addMoney(Currency.CURRENCY_RON, addStacks).getStacks();



        System.out.println(expectedStacks);
        System.out.println(drawer.getMoneyListByType(Currency.CURRENCY_RON).getStacks());
       // actualStacks = drawer.getMoneyListByType(Currency.CURRENCY_RON).getStacks();

        Assert.assertArrayEquals(expectedStacks.toArray(), actualStacks.toArray());



    }

}

