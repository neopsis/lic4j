/*
 * @(#)NvAbstractLicense.java   06.10.2017
 *
 * Copyright (c) 2007 Neopsis GmbH
 *
 *
 */



package com.neopsis.license;

import com.alibaba.fastjson.annotation.JSONField;

import java.security.GeneralSecurityException;

/**
 * Subclasses should add license specific fields.
 * All licenses have a signature, it's here.
 *
 */
public abstract class AbstractLicense {

    ///////////////////////////////////////////////////////////////////////////////////////////
    // Fields
    ///////////////////////////////////////////////////////////////////////////////////////////
    @JSONField(name = "signature")
    protected String signature;

    ///////////////////////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////////////////////
    public AbstractLicense() {}

    public AbstractLicense(String signature) {
        this.signature = signature;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // Getters/Setters
    ///////////////////////////////////////////////////////////////////////////////////////////
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * Sign the license with the PRIVATE_KEY. This is a shortcut for LicenseUtils.sign(...)
     *
     * @param PRIVATE_KEY     private key as a hex string
     * @throws GeneralSecurityException
     */
    public void sign(final String PRIVATE_KEY) throws GeneralSecurityException {
        LicenseUtils.sign(this, PRIVATE_KEY);
    }

    /**
     * Sign the license with the PRIVATE_KEY and algorithm. This is a shortcut for LicenseUtils.sign(...)
     *
     * @param PRIVATE_KEY    private key as a hex string
     * @param sigAlgorithm   algorithm for the signature (SHA1withDSA, ...)
     * @param provider       algorithm provider (SUN, ...)
     * @throws GeneralSecurityException
     */
    public void sign(final String PRIVATE_KEY, String sigAlgorithm, String provider) throws GeneralSecurityException {
        LicenseUtils.sign(this, PRIVATE_KEY, sigAlgorithm, provider);
    }

    /**
     * Verify the license signature using a public key
     *
     * @param PUBLIC_KEY public key as string
     * @throws GeneralSecurityException security exception with error message
     */
    public boolean verify(final String PUBLIC_KEY) throws GeneralSecurityException {
        return LicenseUtils.verify(this, PUBLIC_KEY);
    }

    /**
     * Verify the license signature using a PUBLIC_KEY and a algorithm
     *
     * @param PUBLIC_KEY     public key as string
     * @param sigAlgorithm   algorithm for the signature (SHA1withDSA, ...)
     * @param provider       algorithm provider (SUN, ...)
     * @throws GeneralSecurityException security exception with error message
     */
    public boolean verify(final String PUBLIC_KEY, String sigAlgorithm, String provider) throws GeneralSecurityException {
        return LicenseUtils.verify(this, PUBLIC_KEY, sigAlgorithm, provider);
    }

    /**
     * Parsing callback called after the license object was created and before
     * the object will be filled with parsed data. Default - do nothing
     */
    public void preDeserialize() {

        //
    }

    /**
     * Parsing callback called after parsing was finished. Default - do nothing
     */
    public void afterDeserialize() {

        //
    }

    /**
     * Convert the license to the JSON string
     *
     * @return license as JSON string
     */
    public String toJsonString() {
        return toString();
    }

    @Override
    public String toString() {
        return LicenseUtils.toJson(this);
    }
}
