package com.tuanmhoang.study.tdd;

/**
 * The type Client.
 */
public class Client {
    private String addresses;

    public Client() {

    }

    public Client(String addresses) {
        this.addresses = addresses;
    }

    /**
     * Gets addresses.
     *
     * @return the addresses
     */
    public String getAddresses() {
        return addresses;
    }

    /**
     * Sets addresses.
     *
     * @param addresses the addresses
     */
    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }
}
