package com.learning.webatm.enums;

public enum BnkNamePattern {
        EURO ("EURO_%", Currency.CURRENCY_EURO),
        RON("RON_%", Currency.CURRENCY_RON),
        ;
        private final String pattern;
        private final Currency currency;

        private BnkNamePattern (String pattern, Currency currency) {
            this.pattern = pattern;
            this.currency = currency;
        }

        public String getPattern() {
            return pattern;
        }

        public Currency getCurrency() {
            return currency;
        }
}

