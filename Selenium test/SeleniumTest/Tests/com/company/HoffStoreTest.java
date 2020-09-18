package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class HoffStoreTest {
    private HoffStore hs;

    @Before
    public void setupTest() {
        hs = new HoffStore();
        hs.openBrowser();
    }

    @Test
    public void pressEnter_FiveApplePurchase_Confirmed() {
        hs.selectApples();
        hs.enterAmount("5");
        hs.pressEnterInAmountBox();
        assertEquals("Pressing enter does not confirm purchase", "You bought 5 x Apple for a total of 75", hs.getPurchaseConfirmation());
    }

    @Test
    public void pressBuyButton_OneApplePurchase_Confirmed() {
        hs.selectApples();
        hs.enterAmount("1");
        hs.clickBuyButton();

        assertEquals("Pressing enter does not confirm", "You bought 1 x Apple for a total of 15", hs.getPurchaseConfirmation());
    }

    @Test
    public void buy_FiveApplesPurchase_Confirmed() {
        hs.selectApples();
        hs.enterAmount("5");
        hs.clickBuyButton();

        assertEquals("Pressing enter does not confirm", "You bought 5 x Apple for a total of 75", hs.getPurchaseConfirmation());
    }

    @Test
    public void buy_NaNAmount_ErrorMessage() {
        hs.selectApples();
        hs.enterAmount("abc");
        hs.clickBuyButton();

        assertEquals("Entering NaN does not generate error", "Please enter a correct number", hs.getPurchaseConfirmation());
    }

    @Test
    public void buy_MixedNaNAmount_ErrorMessage() {
        hs.selectApples();
        hs.enterAmount("123abc");
        hs.clickBuyButton();

        assertEquals("Entering NaN does not generate error", "Please enter a correct number", hs.getPurchaseConfirmation());
    }

    @Test
    public void buy_Apples_SumOverLimit_ErrorMessage() {
        hs.selectApples();
        hs.enterAmount("10000000000");
        hs.clickBuyButton();

        assertEquals("Trying to buy for more than limit generates error", "Insufficient funds!", hs.getPurchaseConfirmation());
    }

    @Test
    public void buy_DifferentThings_SumOverLimit_ErrorMessage() {
        hs.selectTV();
        hs.enterAmount("1");
        hs.clickBuyButton();

        hs.selectApples();
        hs.enterAmount("100");
        hs.clickBuyButton();

        assertEquals("Trying to buy for more than limit generates error", "Insufficient funds!", hs.getPurchaseConfirmation());
    }

    @Test
    public void buy_FiveApples_MoneyCorrect() {
        hs.selectApples();
        hs.enterAmount("5");
        hs.clickBuyButton();

        assertEquals("Buying does not calculate remaining money correctly", "9925", hs.getMoney());
    }

    @Test
    public void buy_OneAppleOneTV_MoneyCorrect() {
        hs.selectApples();
        hs.enterAmount("2");
        hs.clickBuyButton();

        hs.selectTV();
        hs.enterAmount("1");
        hs.clickBuyButton();

        assertEquals("Buying does not calculate remaining money correctly", "470", hs.getMoney());
    }

    @Test
    public void buy_OneApple_ReceiptShowProduct() {
        hs.selectApples();
        hs.enterAmount("1");
        hs.clickBuyButton();

        assertEquals("Receipt does not show one item bought", true, hs.isProductFound(hs.getReceiptList(), "Apple"));
    }

    @Test
    public void buy_OneAppleOneTV_ReceiptShowProducts() {
        hs.selectApples();
        hs.enterAmount("1");
        hs.clickBuyButton();

        hs.selectTV();
        hs.enterAmount("1");
        hs.clickBuyButton();

        assertEquals("Receipt does not show items bought", true, hs.isProductFound(hs.getReceiptList(), "Apple"));
        assertEquals("Receipt does not show items bought", true, hs.isProductFound(hs.getReceiptList(), "TV"));
    }

    @Test
    public void buy_OneAppleOneTV_TotalCorrect() {
        hs.selectApples();
        hs.enterAmount("10");
        hs.clickBuyButton();

        hs.selectTV();
        hs.enterAmount("1");
        hs.clickBuyButton();

        assertEquals("Buying does not calculate total cost correctly", "9650", hs.getReceiptTotal());
    }

    @Test
    public void buy_TenApples_VATCorrect() {
        hs.selectApples();
        hs.enterAmount("10");
        hs.clickBuyButton();

        assertEquals("VAT is not correctly calculated", "37.5", hs.getVATSum());
    }

    @Test
    public void buy_TenApplesOneTV_TotalCorrect() {
        hs.selectApples();
        hs.enterAmount("10");
        hs.clickBuyButton();

        hs.selectTV();
        hs.enterAmount("1");
        hs.clickBuyButton();

        assertEquals("Buying does not calculate total cost correctly", "9650", hs.getReceiptTotal());
    }

    @Test
    public void buy_TenApplesOneTV_VATCorrect() {
        hs.selectApples();
        hs.enterAmount("10");
        hs.clickBuyButton();

        hs.selectTV();
        hs.enterAmount("1");
        hs.clickBuyButton();

        assertEquals("VAT is not correctly calculated", "2412.5", hs.getVATSum());
    }

    @Test
    public void buySell_OneApple_ReceiptTotalZero() {
        hs.selectApples();
        hs.enterAmount("1");
        hs.clickBuyButton();

        hs.clickFirstSellButton();

        assertEquals("Buying an apple and then sell does not leave total at 0", "0", hs.getReceiptTotal());
    }

    @Test
    public void buySell_OneApple_VATZero() {
        hs.selectApples();
        hs.enterAmount("1");
        hs.clickBuyButton();

        hs.clickFirstSellButton();

        assertEquals("Buying an apple and then sell does not leave VAT at 0", "0", hs.getVATSum());
    }

    @Test
    public void buySell_OneApple_MoneyCorrect() {
        hs.selectApples();
        hs.enterAmount("1");
        hs.clickBuyButton();

        hs.clickFirstSellButton();

        assertEquals("Buying several items then selling one does not leave remaining money with correct sum", "10000", hs.getMoney());
    }

    @Test
    public void buySellFirst_OneAppleOneTV_MoneyCorrect() {
        hs.selectApples();
        hs.enterAmount("1");
        hs.clickBuyButton();

        hs.selectTV();
        hs.enterAmount("1");
        hs.clickBuyButton();

        hs.clickFirstSellButton();

        assertEquals("Buying several items then selling one does not leave remaining money with correct sum", "500", hs.getMoney());
    }

    @Test
    public void buySellSecond_OneAppleOneTV_MoneyCorrect() {
        hs.selectApples();
        hs.enterAmount("1");
        hs.clickBuyButton();

        hs.selectTV();
        hs.enterAmount("1");
        hs.clickBuyButton();

        hs.clickSecondSellButton();

        assertEquals("Buying several items then selling one does not leave remaining money with correct sum", "9985", hs.getMoney());
    }

    @Test
    public void buySellAll_OneAppleOneTV_MoneyCorrect() {
        hs.selectApples();
        hs.enterAmount("1");
        hs.clickBuyButton();

        hs.selectTV();
        hs.enterAmount("1");
        hs.clickBuyButton();

        hs.clickFirstSellButton();
        hs.clickSecondSellButton();

        assertEquals("Buying several items then selling one does not leave remaining money with correct sum", "10000", hs.getMoney());
    }

    @Test
    public void buySellTen_OneApple_MoneyCorrect() {
        for (int i = 0; i < 10; i++) {
            hs.selectApples();
            hs.enterAmount("1");
            hs.clickBuyButton();

            hs.clickFirstSellButton();
        }
        assertEquals("Money should remain at 10.000", "10000", hs.getMoney());
    }

    @Test
    public void buySell_OneTV_MoneyCorrect() {
        hs.selectTV();
        hs.enterAmount("1");
        hs.clickBuyButton();

        hs.clickFirstSellButton();
        assertEquals("Money should remain at 10.000", "10000", hs.getMoney());
    }

    @Test
    public void buySellTen_OneTV_MoneyCorrect() {
        for (int i = 0; i < 10; i++) {
            hs.selectTV();
            hs.enterAmount("1");
            hs.clickBuyButton();

            hs.clickFirstSellButton();
        }
        assertEquals("Money should remain at 10.000", "10000", hs.getMoney());
    }

    @After
    public void cleanUpTest() {
        hs.closeBrowser();
    }
}
